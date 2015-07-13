package com.heverage.zhanyebao.client.care;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Client;

public class CareActivity extends ActionBarActivity {

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
	    
	    private CareAdapter mAdapter = null;  
	    
	    private ListView mListView = null;  
	    private LinearLayout header = null;
	    
	    private Client mClient;
	    private List<Client> mDataList;  	    
	  
	    private Button addBtn;
	    private Button minusBtn;
	    
	    private Button backBtn;
	    
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_care_layout_main, container,
					false);
			mContext = this.getActivity().getApplicationContext();  
			
	        mListView = (ListView) rootView.findViewById(R.id.listView1);  
	        

	        mClient = new Client();          
	        mDataList = mClient.buildGroupedClients();
	        
	        mAdapter = new CareAdapter(CareActivity.this, android.R.layout.simple_list_item_1,  
	                mDataList);  
	        
	        mListView.setAdapter(mAdapter);
	        
	        header = (LinearLayout)inflater.inflate(  
	                R.layout.client_care_list_header, null); 
	        
	        mListView.addHeaderView(header);  
	        
	         
	        addBtn = (Button) header.findViewById(R.id.button1); 
	        minusBtn = (Button) header.findViewById(R.id.button2);
	        
	        addBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, AddContactActivity.class);
					intent.putExtra("addFlag", true);
					startActivity(intent);
				}        	
	        });
	        
	        minusBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, AddContactActivity.class);
					intent.putExtra("addFlag", false);
					startActivity(intent);
				}        	
	        });
	        
	        backBtn = (Button) rootView.findViewById(R.id.button1); 
	        backBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					finish();
				}        	
	        });
			return rootView;
		}
	}

}
