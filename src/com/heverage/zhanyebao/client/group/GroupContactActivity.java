package com.heverage.zhanyebao.client.group;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.care.AddContactActivity;
import com.heverage.zhanyebao.client.model.Client;
import com.heverage.zhanyebao.client.model.Group;

public class GroupContactActivity extends ActionBarActivity {

	private String mGroupName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(this)).commit();
		}
		
		mGroupName = (String)this.getIntent().getExtras().get("group");
	}	

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment implements OnTouchListener {
	    private Context mContext = null; 
	    
	    private GroupContactAdapter mAdapter = null;  
	    
	    private ListView mListView = null;  	    
 
	    private Group mGroup;
	    private List<Client> mDataList;  	    

	    private Client mClient;
    
	    private Button backBtn;
 
	    

	    public PlaceholderFragment(GroupContactActivity activity) {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_group_contact_layout_main, container,
					false);
			mContext = this.getActivity().getApplicationContext();  
			
	        mListView = (ListView) rootView.findViewById(R.id.listView1);  
	        	        
	        mGroup = new Group();  	       
	        mClient = new Client();
	        
	        
	        mDataList = mClient.buildGroupedClients().subList(0, 11);
	       
	
		    mAdapter = new GroupContactAdapter(GroupContactActivity.this, android.R.layout.simple_list_item_1,  
		                mDataList);  
	
	        
	        mListView.setAdapter(mAdapter);	        
	        
	        backBtn = (Button) rootView.findViewById(R.id.button1); 
	        backBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					finish();
				}        	
	        });

	        TextView titleTextView = (TextView) rootView.findViewById(R.id.group_name);  
	        titleTextView.setText(mGroupName);
	       
	        final View header = inflater.inflate(  
	                R.layout.client_group_contact_list_header, null);  
	        mListView.addHeaderView(header);
	        	        

	        Button smsBtn = (Button) header.findViewById(R.id.button1); 
	        Button addBtn = (Button) header.findViewById(R.id.button2);
	        Button minusBtn = (Button) header.findViewById(R.id.button3);

	        smsBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast t = Toast.makeText(mContext, "暂未实现",
							Toast.LENGTH_SHORT);			
					t.show();
				}        	
	        });
	        
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
	        
			return rootView;
		}
		
	    @Override  
	    public boolean onTouch(View v, MotionEvent event)  
	    {  
	        // TODO Auto-generated method stub  
	        return true;  
	    } 
		
		public String getSureButtonText(int num){
			String text = "确定("+num+")";
			return text;
		}
		
		
		
	}
	
	

}
