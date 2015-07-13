package com.heverage.zhanyebao.client.care;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.SearchableTextWatcher;
import com.heverage.zhanyebao.client.model.Client;

public class AddContactActivity extends ActionBarActivity {

	private Boolean addFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		if (this.getIntent().getExtras() != null) {
			addFlag = (Boolean)this.getIntent().getExtras().get("addFlag");
		}
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(this)).commit();
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
	public class PlaceholderFragment extends Fragment implements OnTouchListener {
	    private Context mContext = null; 
	    
	    private AddContactAdapter mAdapter = null;  
	    
	    private ListView mListView = null;  
	    
	    private LinearLayout header = null;
	    private EditText mSearchText = null; 
	    
	    private Client mClient;
	    private List<Client> mDataList;  	    

    
	    private Button backBtn;
	    private Button sureBtn;
	    

	    public PlaceholderFragment(AddContactActivity activity) {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_addcontact_layout_main, container,
					false);
			mContext = this.getActivity().getApplicationContext();  
			
	        mListView = (ListView) rootView.findViewById(R.id.listView1);  
	        header = (LinearLayout)inflater.inflate(  
	                R.layout.client_addcontact_list_header, null); 
	        
	        mSearchText = (EditText) header.findViewById(R.id.searchText);  
	        mSearchText.setFocusable(true);
	        	        
	        mClient = new Client();          
	        mDataList = mClient.buildGroupedClients();
	        
	        if( addFlag ) {
	        	mListView.addHeaderView(header);  
	        }
	        
	        List<Client> checkedList = initCheckedList();	
	        
	        if(addFlag){
		        mAdapter = new AddContactAdapter(AddContactActivity.this, android.R.layout.simple_list_item_1,  
		                mDataList, checkedList, this);  
	        }else{
	        	List<Client> groupedCheckedList = mClient.buildGroupedClients(checkedList);
	        	mAdapter = new AddContactAdapter(AddContactActivity.this, android.R.layout.simple_list_item_1,  
	        			groupedCheckedList, checkedList, this);  
	        }
	        
	        mListView.setAdapter(mAdapter);
	        
	        mListView.setOnTouchListener(new OnTouchListener()  
	        {  
	              
	            @Override  
	            public boolean onTouch(View v, MotionEvent event)  
	            {  
	                // TODO Auto-generated method stub  
	                if(mSearchText.hasFocus())  
	                    mSearchText.clearFocus();  
	                return false;  
	            }  
	        });  
	        //搜索框监听  
	        mSearchText.addTextChangedListener(new SearchableTextWatcher(mContext, mAdapter, mSearchText, mDataList));  
	        
	        backBtn = (Button) rootView.findViewById(R.id.button1); 
	        sureBtn = (Button) rootView.findViewById(R.id.button2);
	
	        sureBtn.setText(getSureButtonText(mAdapter.getCheckedListSize()));
	        
	        backBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					finish();
				}        	
	        });
	        
	        sureBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					finish();
				}        	
	        });
	        
			return rootView;
		}
		
	    @Override  
	    public boolean onTouch(View v, MotionEvent event)  
	    {  
	        // TODO Auto-generated method stub  
	  
	        if(mSearchText.hasFocus())  
	        	mSearchText.clearFocus();  
	        return true;  
	    } 
		
		public String getSureButtonText(int num){
			String text = "确定("+num+")";
			return text;
		}
		
		public void changeSureButtonText(int num){
			sureBtn.setText(getSureButtonText(num));
		}
		
	    public List<Client>  initCheckedList()  
	    {  
	    	List<Client> checkedList = new ArrayList<Client>() ;
	        for (int i = 0; i < mDataList.size(); i++)  {
	        	if(i % 4== 1 && mDataList.get(i).isRealPerson()){
	        		final Client client = mDataList.get(i); 
					checkedList.add(client);				
				} 
	        }
	        return checkedList;
	    } 
	}


}
