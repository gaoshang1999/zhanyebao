package com.heverage.zhanyebao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;

public class OptionsDialog {

	public static void createOptionDialog(Context mContext,  int resId, final OptionCallbackListener oc){
		createOptionDialog(mContext, resId, oc, null);
	}

	public static void createOptionDialog(Context mContext,  int resId, final OptionCallbackListener oc,final TextView renderText){
		
		LinearLayout dialogView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.util_options_dialog, null);  
	      
	    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
	           .setView(dialogView)  
	           .create();  
	    
	    Resources res = mContext.getResources();
	    final String[] constants =res.getStringArray(resId);
	    
	    TextView title = (TextView)dialogView.findViewById(R.id.title);
	    title.setText(constants[0]);
	    
	    final Button[] buttonArray = new Button[constants.length];
	    final LinearLayout[] layoutArray = new LinearLayout[constants.length];
	    for(int i=1; i< constants.length; i++){
	    	LinearLayout itemView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.util_options_item_dialog, null);  
	    	
	    	LinearLayout option = (LinearLayout)itemView.findViewById(R.id.option1);
	    	layoutArray[i] = option;
	    	
	    	TextView constants1 = (TextView)itemView.findViewById(R.id.constants1);
	    	constants1.setText(constants[i]);
	    	
	    	final Button buttonConstants1 = (Button)itemView.findViewById(R.id.buttonConstants1);	
	    	if(i != oc.getKey()){
	    		buttonConstants1.setVisibility(View.GONE);
	    	}
	    	buttonArray[i] = buttonConstants1;
	    	
	    	dialogView.addView(itemView);
	    }
	    
	    for(int i=1; i< constants.length; i++){
	    	final LinearLayout option = layoutArray[i] ;
	    	final Button clickedButton = buttonArray[i];	
	    	final int key = i;
	    	final String value = constants[i];
	    	
	    	option.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
//				    final Button oldButton = buttonArray[oc.getKey()];	
//				    oldButton.setVisibility(View.GONE);
//					
//					clickedButton.setVisibility(View.VISIBLE);
					dialog.dismiss();
					oc.callback(key, value);
					if(renderText!=null){
						renderText.setText(value);
					}
				}        	
	        });
	    }	 
	    
	    dialog.show();
	}
}
