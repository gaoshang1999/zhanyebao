package com.heverage.zhanyebao.client.care;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Client;
  
public class CareAdapter extends ArrayAdapter<Client>  
{  
    public List<Client> mObject;  
    
    private View view = null;  
      
    private TextView tView = null;  
      
    private final Context mContext ;

    
    public CareAdapter(Context context, int textViewResourceId,  
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
                    R.layout.client_care_list_item, null);  
            
			
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
			
			final Button hintBtn = (Button)view.findViewById(R.id.btnHint);	 	
			final TextView hintTv = (TextView)view.findViewById(R.id.client_list_item_text_hint);	
			
			if(position % 2== 1){
				hintBtn.setVisibility(View.GONE);
				hintTv.setVisibility(View.VISIBLE);
			}else{
				hintTv.setVisibility(View.GONE);
				hintBtn.setVisibility(View.VISIBLE);
			}
			
			
			hintBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {

					LayoutInflater factory = LayoutInflater.from(mContext);  
				    //获得自定义对话框  
				    View view = factory.inflate(R.layout.client_care_dialog_edit, null);  
				      
				    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
				           .setView(view)  
				           .create();  
				    dialog.show();  
				    
				    Button cancel = (Button)view.findViewById(R.id.button1);
				    cancel.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
				    
				    final EditText et_in_dialog = (EditText)view.findViewById(R.id.editText1);	
				    final TextView count_down_hint = (TextView)view.findViewById(R.id.count_down_hint);	
				    et_in_dialog.setText(hintTv.getText());
				    count_down_hint.setText(String.valueOf(30- hintTv.getText().length())) ;
				    et_in_dialog.addTextChangedListener(new MyWatchToSearch(count_down_hint));  
				    
				    Button ok = (Button)view.findViewById(R.id.button2);
				    ok.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v) {
							hintBtn.setVisibility(View.GONE);
							hintTv.setVisibility(View.VISIBLE);
							hintTv.setText(et_in_dialog.getText());
							dialog.dismiss();
						}
					});
						
				}
			});
			
			
			hintTv.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {

					LayoutInflater factory = LayoutInflater.from(mContext);  
				    //获得自定义对话框  
				    View view = factory.inflate(R.layout.client_care_dialog_view, null);  
				      
				    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
				           .setView(view)  
				           .create();  
				    dialog.show();  
				    
				    Button ok = (Button)view.findViewById(R.id.button1);
				    ok.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v) {							 
							dialog.dismiss();
							hintBtn.callOnClick();
						}
					});
				    
				    Button cancel = (Button)view.findViewById(R.id.button2);
				    cancel.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
				}
	        });
				 
        }  
        
        
		 
 
  
        tView = (TextView) view  
                .findViewById(R.id.client_list_item_text_name);  
        tView.setText(name);  
         
  
        return view;  
    }  
  
  
	class MyWatchToSearch implements TextWatcher  
    {  
		TextView hint;

		public MyWatchToSearch(TextView hint) {
			super();
			this.hint = hint;
		}

		@Override  
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after)  
        {  
            // TODO Auto-generated method stub   
        }  
  
        @Override  
        public void onTextChanged(CharSequence s, int start, int before,  
                int count)  
        {  
            // TODO Auto-generated method stub      	
        	hint.setText(String.valueOf(30 -  s.length()  ));
        }  
  
        @Override  
        public void afterTextChanged(Editable s)  
        {  
            // TODO Auto-generated method stub  
        }  
    }
}  
