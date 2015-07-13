package com.heverage.zhanyebao.client.care;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.care.AddContactActivity.PlaceholderFragment;
import com.heverage.zhanyebao.client.model.Client;
  
public class AddContactAdapter extends ArrayAdapter<Client>  
{  
  
    public List<Client> mObject;    

    private View view = null;  
      
    private TextView tView = null;  
      
    private final Context mContext ;
 
    private List<Client> mCheckedList = new ArrayList<Client>() ;
  
    
    private PlaceholderFragment mFragment;
    public AddContactAdapter(Context context, int textViewResourceId,  
            List<Client> objects, List<Client> checkedList, PlaceholderFragment fragment)  
    {  
        super(context, textViewResourceId, objects);  
        // TODO Auto-generated constructor stub  
        mContext = context;
        mObject = objects;  
        mFragment = fragment;
        mCheckedList = checkedList;
    }  
  
    @Override  
    /** 
     * 添加item时判断，如果读取到的数据可以在arrayList中找到（即大写的单独字母），则添加为标题，否则是内容 
     *  
     */  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        // TODO Auto-generated method stub
    	final Client client = getItem(position);
    	final String name = getItem(position).getName();
  
    	 if (!getItem(position).isRealPerson())  
        {  
            view = LayoutInflater.from(getContext()).inflate(  
                    R.layout.client_list_item_tag, null);  
        }  
        else  
        {  
            view = LayoutInflater.from(getContext()).inflate(  
                    R.layout.client_addcontact_list_item, null);   
			
			final CheckBox ckb = (CheckBox)view.findViewById(R.id.checkBox1);			 
			
			if(mCheckedList.contains(client) ){
				ckb.setChecked(true);
			} 
			
			ckb.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					 if(ckb.isChecked()){
						 mCheckedList.add(client);
					 }else{
						 mCheckedList.remove(client);
					 }
					 mFragment.changeSureButtonText(mCheckedList.size());
				}        	
	        });
			
				 
        }  
  
        tView = (TextView) view  
                .findViewById(R.id.client_list_item_text_name);  
        tView.setText(name);  
         
  
        return view;  
    }  
  
  
  
    public int getCheckedListSize(){
    	return mCheckedList.size();
    }

}  
