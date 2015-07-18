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
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.util.OptionsDialog;
import com.heverage.zhanyebao.view.OptionsView;

public class NewEmailActivity extends ActionBarActivity {


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
					R.layout.client_new_email_layout_main, container, false);
			mContext = this.getActivity();


			backBtn = (Button) rootView.findViewById(R.id.button1);
			backBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			saveBtn = (Button) rootView.findViewById(R.id.button2);
			
			email = (EditText)rootView.findViewById(R.id.email);
			
			email.addTextChangedListener(new WatchToEditViewSwitchButton(email, saveBtn));
			
			
			saveBtn.setEnabled(false);
			saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {					
					if(validate()){
						mEmail.setEmail(email.getText().toString());
						
						Intent i = new Intent();
						i.putExtra("email", mEmail);
						setResult(RESULT_OK, i);
						
						finish();
					}
				}
			});

			initEmaiTypeView(rootView);
			return rootView;
		}


	
	private Email mEmail = new Email();
	private EditText email;
    private OptionsView email_type_line;
	
	public void initEmaiTypeView(View rootView){	
		email_type_line = (OptionsView)rootView.findViewById(R.id.client_email_type_line);			

		OptionCallbackListener oc = new OptionCallbackListener(){
			@Override
			public int getKey() {
				// TODO Auto-generated method stub
				return mEmail.getEmail_type_value();
			}

			@Override
			public void callback(int key, String value) {
				// TODO Auto-generated method stub
				mEmail.setEmail_type_value(key);

			}						
		};			
		email_type_line.setOptionCallbackListener(oc);	
	}
	
	public boolean validate(){
		if(!email_type_line.isClicked()){
			return false;
		}
		
		if(!email.getText().toString().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")){
    		email.setError("邮箱格式非法！");
    		return false;
    	}
		
		return true;
	}
	
	class WatchToEditViewSwitchButton implements TextWatcher  
	{  
		EditText email;
		Button ok;

		public WatchToEditViewSwitchButton(EditText email, Button ok) {
			super();
			this.email = email;
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
	    	if(s.toString().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+") && email_type_line.isClicked()){
	    		this.ok.setEnabled(true);
	    	}
	    }  
	}
	
	}
}
