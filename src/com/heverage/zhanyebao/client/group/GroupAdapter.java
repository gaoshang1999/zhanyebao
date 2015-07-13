package com.heverage.zhanyebao.client.group;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Group;
  
public class GroupAdapter extends ArrayAdapter<Group>  
{  
  
    public List<Group> mObject;    

    private View view = null;  
      
    private TextView tView = null;  
      
    private final Context mContext ;
  
    public GroupAdapter(Context context, int textViewResourceId,  
            List<Group> objects)  
    {  
        super(context, textViewResourceId, objects);  
        // TODO Auto-generated constructor stub  
        mContext = context;
        mObject = objects;  

    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent)  
    {  
        // TODO Auto-generated method stub
    	final Group group = getItem(position);
    	final String name = getItem(position).getName();
  
    	   
        view = LayoutInflater.from(getContext()).inflate(  
                R.layout.client_group_list_item, null);   
        
        tView = (TextView) view.findViewById(R.id.group_name);  
        tView.setText(name);  
        		
		final Button rename = (Button)view.findViewById(R.id.button1);			 
		final Button delete = (Button)view.findViewById(R.id.button2);		

		
		rename.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {					 

			}        	
        });
		
		delete.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {					 

			}        	
        });	          
  
        return view;  
    }  
  
}  
