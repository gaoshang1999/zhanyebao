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
import com.heverage.zhanyebao.client.model.Address;
import com.heverage.zhanyebao.client.model.Email;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.util.OptionsDialog;
import com.heverage.zhanyebao.view.OptionsView;

public class NewAddressActivity extends ActionBarActivity {


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
					R.layout.client_new_address_layout_main, container, false);
			mContext = this.getActivity();


			backBtn = (Button) rootView.findViewById(R.id.button1);
			backBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			saveBtn = (Button) rootView.findViewById(R.id.button2);
			
			address = (EditText)rootView.findViewById(R.id.address);
			
			address.addTextChangedListener(new WatchToEditViewSwitchButton(saveBtn));
			
			saveBtn.setEnabled(false);
			saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					mAddress.setDetail_address(address.getText().toString());
					
					Intent i = new Intent();
					i.putExtra("address", mAddress);
					setResult(RESULT_OK, i);
					
					finish();
				}
			});

			initAddressTypeView(inflater, rootView);
			initRegionView(inflater, rootView);
			return rootView;
		}

		
		private Address mAddress = new Address();
		private EditText address;
		private OptionsView address_type_line;
		public void initAddressTypeView(LayoutInflater inflater, View rootView){			
			address_type_line = (OptionsView)rootView.findViewById(R.id.address_type_line);			

			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mAddress.getAddress_type_value();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mAddress.setAddress_type_value(key);

				}						
			};			
			address_type_line.setOptionCallbackListener(oc);	
		}
		
		
		TextView regionRenderText = null;		
		public void initRegionView(LayoutInflater inflater, View rootView){

		    LinearLayout line = (LinearLayout)rootView.findViewById(R.id.client_region_line);			
		    final TextView renderText = (TextView)rootView.findViewById(R.id.client_region);
			this.regionRenderText = renderText;
			
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					Intent intent = new Intent(mContext, RegionActivity.class);
					startActivityForResult(intent, REGION_requestCode);					
				}        	
	        });			
		}
		
		public boolean isRegionClicked(){
			if(regionRenderText.getText().toString().isEmpty()){
				return false;
			}
			return true;
		}
		
		private final int REGION_requestCode = 100;
			
		public void onActivityResult(int requestCode, int resultCode,   Intent data) {
			if (requestCode == REGION_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String text = data.getStringExtra("city");
	            	 this.regionRenderText.setText(text);
	            	 mAddress.setRegion(text);
	             }
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
		    	if(!s.toString().isEmpty() && isRegionClicked() && address_type_line.isClicked()){
		    		this.ok.setEnabled(true);
		    	}
		    }  
		}
	}
}
