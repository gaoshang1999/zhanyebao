package com.heverage.zhanyebao.client;

import java.util.ArrayList;  
import java.util.HashMap;  

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.R.id;
import com.heverage.zhanyebao.R.layout;
  
import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ArrayAdapter;  
import android.widget.TextView;  
  
public class LetterAdapter extends ArrayAdapter<String>  
{  
  
   
  
    private View view = null;  
      
    private TextView tView = null;  
      
  
  
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
  
         
        view = LayoutInflater.from(getContext()).inflate(  
                    R.layout.client_list_item_letter, null);  
         
  
        tView = (TextView) view  
                .findViewById(R.id.group_list_item_text);  
        tView.setText(getItem(position));  
  
        return view;  
    }      
  
}  
