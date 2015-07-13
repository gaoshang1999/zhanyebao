package com.heverage.zhanyebao.client.add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Phone;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.util.OptionsDialog;

public class NewPhoneActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {
		private Context mContext = null;


		private Button backBtn;
		private Button saveBtn;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.client_new_phone_layout_main, container, false);
			mContext = this.getActivity();


			backBtn = (Button) rootView.findViewById(R.id.button1);
			backBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			saveBtn = (Button) rootView.findViewById(R.id.button2);
			
			final EditText phone = (EditText)rootView.findViewById(R.id.phone_num);
			
			phone.addTextChangedListener(new WatchToEditViewSwitchButton(saveBtn));
			
			saveBtn.setEnabled(false);
			saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Phone p = new Phone();
					p.setPhoneType(phoneType);
					p.setPhoneNum(phone.getText().toString());
					
					Intent i = new Intent();
					i.putExtra("phone", p);
					setResult(RESULT_OK, i);
					
					finish();
				}
			});

			initPhoneTypeView(inflater, rootView);
			return rootView;
		}

		private int phoneType = 1;
		
		public void initPhoneTypeView(LayoutInflater inflater, View rootView){

		    LinearLayout line = (LinearLayout)rootView.findViewById(R.id.phone_type_line);			
		    final TextView renderText = (TextView)rootView.findViewById(R.id.phone_type);
			
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					
					OptionCallbackListener oc = new OptionCallbackListener(){
						@Override
						public int getKey() {
							// TODO Auto-generated method stub
							return phoneType;
						}

						@Override
						public void callback(int key, String value) {
							// TODO Auto-generated method stub
							phoneType = key;
							renderText.setText(value);
						}						
					};
					
					OptionsDialog.createOptionDialog(mContext, R.array.phoneType, oc);
				}        	
	        });			
		}
		
	}
	
	class WatchToEditViewSwitchButton implements TextWatcher  
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
	    	  if(s.length() != 11){
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

}
