package com.heverage.zhanyebao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.heverage.zhanyebao.R;
  
public class UpdateManager {  
  
    private Context mContext;  
      
    //提示语  
    private String updateMsg = "检测到最新的软件包，是否下载？";  
      
    //返回的安装包url  
    private String apkUrl = "http://www.heverage.com/download/zhanyebao.apk";  
    private String versionUrl ="http://www.heverage.com/download/zhanyebao.version";   
      
    private Dialog noticeDialog;  
      
    private Dialog downloadDialog;  
     /* 下载包安装路径 */  
    private static final String savePath = "/sdcard/com.heverage.zhanyebao/";  
      
    private static final String saveFileName = savePath + "zhanyebao.apk";  
  
    /* 进度条与通知ui刷新的handler和msg常量 */  
    private ProgressBar mProgress;  
  
      
    private static final int DOWN_UPDATE = 1;  
      
    private static final int DOWN_OVER = 2;  
      
    private int progress;  
      
    private Thread downLoadThread;  
      
    private boolean interceptFlag = false;  
      
    private Handler mHandler = new Handler(){  
        public void handleMessage(Message msg) {  
            switch (msg.what) {  
            case DOWN_UPDATE:  
                mProgress.setProgress(progress);  
                break;  
            case DOWN_OVER:  
                  
                installApk();  
                break;  
            default:  
                break;  
            }  
        };  
    };  
      
    public UpdateManager(Context context) {  
        this.mContext = context;  
    }  
      
    //外部接口让主Activity调用  
    public void checkUpdateInfo(){
    	if(NetworkUtil.getConnectivityStatus(mContext) != NetworkUtil.TYPE_WIFI){
    		return;
    	}
    	
    	getCurVersion();
    	new Thread(mGetVersionRunnable).start();      	

    }  
    
    private String curVersionName;
    private String newVersionName;
    private int curVersionCode;
    private int newVersionCode;
    
    private void getCurVersion() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(
            		mContext.getPackageName(), 0);
            curVersionName = pInfo.versionName;
            curVersionCode = pInfo.versionCode;
        } catch (NameNotFoundException e) {
        	curVersionName = "1.0";
            curVersionCode = 1;
        }
    }
      
    private void showNoticeDialog(){  
		LayoutInflater factory = LayoutInflater.from(mContext);  
        View view = factory.inflate(R.layout.util_update_version_dialog, null);  
        
        AlertDialog.Builder builder = new Builder(mContext);  
        
        builder.setView(view);
    
	    Button cancelBtn = (Button)view.findViewById(R.id.button1);
	    cancelBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				noticeDialog.dismiss();

			}
		});
	    
	    Button okBtn = (Button)view.findViewById(R.id.button2);
	    okBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				noticeDialog.dismiss();  
                showDownloadDialog();  
			}
		});
	    
        
        Looper.prepare();
        noticeDialog = builder.create();  
        noticeDialog.show();  
        Looper.loop(); 
    }  
      
    private void showDownloadDialog(){  
        LayoutInflater factory = LayoutInflater.from(mContext);  
        View view = factory.inflate(R.layout.util_update_version_download_dialog, null);  
        
        AlertDialog.Builder builder = new Builder(mContext);  

        mProgress = (ProgressBar)view.findViewById(R.id.progressBar1);  
          
        builder.setView(view);         

        downloadDialog = builder.create();  
        downloadDialog.show();    
    
	    Button cancelBtn = (Button)view.findViewById(R.id.button1);
	    cancelBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				downloadDialog.dismiss();
				interceptFlag = true; 
			}
		});
        
        downloadApk();  
    }  
    
    private Runnable mGetVersionRunnable = new Runnable() {      
        @Override  
        public void run() {  
            try {  
                String url = versionUrl;  
        		HttpGet httpget = new HttpGet(url);

        		httpget.addHeader("Accept", "appliction/json");
        		HttpClient httpclient = new DefaultHttpClient();

        		HttpResponse response = httpclient.execute(httpget);
                  
                StringBuffer sb = new StringBuffer();
        		BufferedReader br = null;
        		try {
        			HttpEntity entity = response.getEntity();
        			br = new BufferedReader(new InputStreamReader(entity.getContent()));
        			String readLine;
        			while (((readLine = br.readLine()) != null)) {
        				sb.append(readLine);
        			}
        		} catch (Exception e) {
        			System.err.println(e);
        			Log.e("my", e.getMessage());
        		} finally {
        			if (br != null)
        				try {
        					br.close();
        				} catch (Exception fe) {
        				}
        		}
        		
        		JSONObject j = new JSONObject(sb.toString());	            
        		newVersionCode = j.getInt("code");
        		newVersionName = j.getString("name");
        		
                if(newVersionCode > curVersionCode || newVersionName.compareTo(curVersionName) > 0){
                	showNoticeDialog();  
                }
	        } catch(Exception e){  
	            e.printStackTrace();  
	        }
        }  
    };  
      
    private Runnable mdownApkRunnable = new Runnable() {      
        @Override  
        public void run() {  
            try {  
                URL url = new URL(apkUrl);  
              
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                conn.connect();  
                int length = conn.getContentLength();  
                InputStream is = conn.getInputStream();  
                  
                File file = new File(savePath);  
                if(!file.exists()){  
                    file.mkdir();  
                }  
                String apkFile = saveFileName;  
                File ApkFile = new File(apkFile);  
                FileOutputStream fos = new FileOutputStream(ApkFile);  
                  
                int count = 0;  
                byte buf[] = new byte[1024];  
                  
                do{                   
                    int numread = is.read(buf);  
                    count += numread;  
                    progress =(int)(((float)count / length) * 100);  
                    //更新进度  
                    mHandler.sendEmptyMessage(DOWN_UPDATE);  
                    if(numread <= 0){             
                        break;  
                    }  
                    fos.write(buf,0,numread);
                    fos.flush();
                }while(!interceptFlag);//点击取消就停止下载.  
                fos.flush();  
                fos.close();  
                is.close();  
               //下载完成通知安装  
                mHandler.sendEmptyMessage(DOWN_OVER);  
            } catch (MalformedURLException e) {  
                e.printStackTrace();  
            } catch(IOException e){  
                e.printStackTrace();  
            }  
              
        }  
    };  
      
     /** 
     * 下载apk 
     * @param url 
     */  
      
    private void downloadApk(){  
        downLoadThread = new Thread(mdownApkRunnable);  
        downLoadThread.start();  
    }  
     /** 
     * 安装apk 
     * @param url 
     */  
    private void installApk(){  
        File apkfile = new File(saveFileName);  
        if (!apkfile.exists()) {  
            return;  
        }      
        Intent i = new Intent(Intent.ACTION_VIEW);  
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");   
        mContext.startActivity(i);  
      
    }  
}  
