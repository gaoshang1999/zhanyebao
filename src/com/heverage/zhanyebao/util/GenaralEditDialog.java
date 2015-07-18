package com.heverage.zhanyebao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;


public class GenaralEditDialog {


	public void createEditDialog(Context mContext,  final GenaralEditDialogCallBack cb){
		
		LinearLayout dialogView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.util_general_dialog_edit, null);  
	      
	    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
	           .setView(dialogView)  
	           .create();  
	    
 
	    
	    TextView title = (TextView)dialogView.findViewById(R.id.textView1);
	    title.setText(cb.getTitle());
	    
	    Button cancel = (Button)dialogView.findViewById(R.id.button1);
	    cancel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	    
	    final EditText et_in_dialog = (EditText)dialogView.findViewById(R.id.editText1);	
	    final TextView count_down_hint = (TextView)dialogView.findViewById(R.id.count_down_hint);	
	    et_in_dialog.setText(cb.getText());
	    count_down_hint.setText(String.valueOf(30- cb.getText().length())) ;
	    et_in_dialog.addTextChangedListener(new MyWatchToSearch(count_down_hint));  
	    
	    Button ok = (Button)dialogView.findViewById(R.id.button2);
	    ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				cb.setText(et_in_dialog.getText().toString());
				dialog.dismiss();
			}
		});
	    
	    dialog.show();
	}
	
	public interface GenaralEditDialogCallBack{
		public String getTitle();
		
		public String getText();
		
		public void setText(String value);
	}
	
	public class MyWatchToSearch implements TextWatcher  
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
