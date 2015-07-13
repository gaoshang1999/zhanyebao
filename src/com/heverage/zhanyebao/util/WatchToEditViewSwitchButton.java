package com.heverage.zhanyebao.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

public class WatchToEditViewSwitchButton implements TextWatcher  
{  
	Button ok;

	public WatchToEditViewSwitchButton(Button ok) {
		super();
		this.ok = ok;
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
    	  if(s.length() == 0){
    		  ok.setEnabled(false);
    	  }else{
    		  ok.setEnabled(true);
    	  }
    }  

    @Override  
    public void afterTextChanged(Editable s)  
    {  
        // TODO Auto-generated method stub  
    }  
}


