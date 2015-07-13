package com.heverage.zhanyebao.client;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.add.ClientInfoActivity;
import com.heverage.zhanyebao.client.model.Client;
  
public class ClientAdapter extends ArrayAdapter<Client>  
{   
 
    public List<Client> mObject;   
  
    private View view = null;  
      
    private TextView tView = null;  
      
    private final Context mContext ;

  
    public ClientAdapter(Context context, int textViewResourceId,  
            List<Client> objects)  
    {  
        super(context, textViewResourceId, objects);  
        // TODO Auto-generated constructor stub  
        mContext = context;
        mObject = objects;  
    }  
  
    @Override  
    /** 
     * 添加item时判断，如果读取到的数据可以在arrayList中找到（即大写的单独字母），则添加为标题，否则是内容 
     *  
     */  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        // TODO Auto-generated method stub  
    	final String name = getItem(position).getName();
  
        if (!getItem(position).isRealPerson())  
        {  
            view = LayoutInflater.from(getContext()).inflate(  
                    R.layout.client_list_item_tag, null);  
        }  
        else  
        {  
            view = LayoutInflater.from(getContext()).inflate(  
                    R.layout.client_list_item, null);  
            
            ImageView image = (ImageView)view.findViewById(R.id.client_image);
            LinearLayout nameAndPhone = (LinearLayout)view.findViewById(R.id.client_name_and_phone);
            
            
            OnClickListener l =  new OnClickListener(){
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
					Intent intent = new Intent(mContext, ClientInfoActivity.class);
					mContext.startActivity(intent);
    			}
    				
    		};
    		image.setOnClickListener(l);
    		nameAndPhone.setOnClickListener(l);
            
            
            TextView already_care_text = (TextView)view.findViewById(R.id.already_care_text);	            
            
            Button careBtn = (Button)view.findViewById(R.id.button1);	 				
			
			careBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast t = Toast.makeText(mContext, name + " care",
						Toast.LENGTH_SHORT);			
				t.show();
			}
				
			});
			
			if(position%2==0){
				already_care_text.setVisibility(View.VISIBLE);
				careBtn.setVisibility(View.GONE);
			}
			
			Button smsBtn = (Button)view.findViewById(R.id.button2);	 				
			
			smsBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("smsto://"+"15010207216");
				Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
				intent.putExtra("sms_body", "");
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				mContext.startActivity(intent);
			}
				
			});
			
			Button phoneBtn = (Button)view.findViewById(R.id.button3);	 				
			
			phoneBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
								
				Intent intent = new Intent();
			    //动作
			    intent.setAction(Intent.ACTION_CALL);
			    //数据      Uri  解析成电话号码格式
			    intent.setData(Uri.parse("tel:"+"15010207216"));
			    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			    //执行意图  激活组件
			    mContext.startActivity(intent);
			}
				
			});
        }  
  
        tView = (TextView) view  
                .findViewById(R.id.client_list_item_text_name);  
        tView.setText(name);  
         
  
        return view;  
    }  
  
    /** 
     * 根据字母导航所点击的字母，锁定名单中对应标题的位置 
     * @param str  所点击的字母 
     * @return     返回字母在数据源中的位置。 
     */  
    public int checkSection(String str)  
    {  
  
    	Client[] array = mObject.toArray(new Client[mObject.size()]);
    	
    	Client client = new Client();
    	client.setName(str);    	
    	return Arrays.binarySearch(array, client);
    	
//        for (int i = 0; i < mObject.size(); i++)  
//        {  
//            if (mObject.get(i).getName().equals(str))  
//                return i;  
//        }  
//        return -1;  
    }  
 
  
}  
