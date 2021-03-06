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
import com.heverage.zhanyebao.client.model.Email;
import com.heverage.zhanyebao.client.model.Phone;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.util.OptionsDialog;
import com.heverage.zhanyebao.view.OptionsView;

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
			
			phone = (EditText)rootView.findViewById(R.id.phone_num);
			
			phone.addTextChangedListener(new WatchToEditViewSwitchButton(saveBtn));
			
			saveBtn.setEnabled(false);
			saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(validate()){
						mPhone.setPhone_number(phone.getText().toString());
						
						Intent i = new Intent();
						i.putExtra("phone", mPhone);
						setResult(RESULT_OK, i);
						
						finish();
					}
				}
			});

			initPhoneTypeView(inflater, rootView);
			return rootView;
		}
		
		private Phone mPhone = new Phone();
		private EditText phone;
		private OptionsView phone_type_line;
		
		public void initPhoneTypeView(LayoutInflater inflater, View rootView){
			
		    phone_type_line = (OptionsView)rootView.findViewById(R.id.client_phone_type_line);			

			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mPhone.getPhone_type_value();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mPhone.setPhone_type_value(key);

				}						
			};			
			phone_type_line.setOptionCallbackListener(oc);	
		}
		
		public boolean validate(){
			if(!phone_type_line.isClicked()){
				return false;
			}
			
			if(!phone.getText().toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")){
				phone.setError("手机号码格式非法！");
	    		return false;
	    	}
			
			return true;
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
	    }  

	    @Override  
	    public void afterTextChanged(Editable s)  
	    {  
	        // TODO Auto-generated method stub  
	    	if(s.toString().matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$") && phone_type_line.isClicked()){
	    		this.ok.setEnabled(true);
	    	}
	    }  
	}
	
	}

}
