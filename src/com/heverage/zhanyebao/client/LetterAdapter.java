package com.heverage.zhanyebao.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
  
public class LetterAdapter extends ArrayAdapter<String>  
{  
  
    public LetterAdapter(Context context, String[] english)  
    {  
    	
        super(context, android.R.layout.simple_list_item_1, english);  
        // TODO Auto-generated constructor stub  

    }  
  
    @Override  
    /** 
     * 添加item时判断，如果读取到的数据可以在arrayList中找到（即大写的单独字母），则添加为标题，否则是内容 
     *  
     */  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        // TODO Auto-generated method stub  
    	View view = convertView;
    	ViewHolder viewHolder = null;
        if(convertView == null) {
        	viewHolder = new ViewHolder();
	        view = LayoutInflater.from(getContext()).inflate(  
	                    R.layout.client_list_item_letter, null);  
	        viewHolder.letterHolder = (TextView) view  
	                .findViewById(R.id.group_list_item_text);  
	        view.setTag(viewHolder);
        }else{
        	viewHolder = (ViewHolder) view.getTag();
        }
        
        viewHolder.letterHolder.setText(getItem(position));  
  
        return view;  
    }      
    
    private class ViewHolder {
    	public  TextView letterHolder;
    }
  
}  
