package com.heverage.zhanyebao.client.group;

import java.util.List;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Group;
import com.heverage.zhanyebao.util.WatchToEditViewSwitchButton;

public class GroupActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		
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
	    
//	    private GroupAdapter mAdapter = null;  
	    GroupAdapterSwiped mAdapter = null;  
	    private ListView mListView = null;  	
	    private View mFooter;
 
	    private Group mGroup;
	    private List<Group> mDataList;  	    

    
	    private Button backBtn;
	    private Button addNewBtn;

	    private ImageView no_data_image;

	    public PlaceholderFragment(GroupActivity activity) {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_group_layout_main, container,
					false);
			mContext = this.getActivity().getApplicationContext();  
			
	        mListView = (ListView) rootView.findViewById(R.id.listView1);  
	        	        
	        mGroup = new Group();          
	        mDataList = mGroup.buildGroupsList();
	       
	
//		    mAdapter = new GroupAdapter(GroupActivity.this, android.R.layout.simple_list_item_1,  
//		                mDataList);  
		    mAdapter = new GroupAdapterSwiped(GroupActivity.this, this);
	        
		    
		    no_data_image = (ImageView) rootView.findViewById(R.id.no_data_image);  
		    if(mAdapter.getCount() > 0){
		    	hideNo_data_image();
		    }
		    
	        mListView.setAdapter(mAdapter);	        
//	        mListView.setOnTouchListener(this);
	        mListView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
			    	mListView.removeFooterView(mFooter);
			    	addNewBtn.setEnabled(true);
					Intent intent = new Intent(mContext, GroupContactActivity.class);
					intent.putExtra("group", mAdapter.getItem(position).getName());
					startActivity(intent);					
				} 
	        
	        });

	        backBtn = (Button) rootView.findViewById(R.id.button1); 
	        addNewBtn = (Button) rootView.findViewById(R.id.button2);
	
	        
	        mFooter = inflater.inflate(  
	                R.layout.client_group_footer, null);  
	        
	        backBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					finish();
				}        	
	        });
	        
	        final EditText group_name = (EditText)mFooter.findViewById(R.id.group_name); 
	        
	        addNewBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					mListView.addFooterView(mFooter);
					addNewBtn.setEnabled(false);
					
					//滚动到末尾
					mListView.setSelection(mListView.getAdapter().getCount());
					hideNo_data_image();
				}        	
	        });
	        
	        

	        Button saveBtn = (Button) mFooter.findViewById(R.id.button1); 
//	        Button cancelBtn = (Button) footer.findViewById(R.id.button2);
	        
	        group_name.addTextChangedListener(new WatchToEditViewSwitchButton(saveBtn));
	        saveBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					String group = group_name.getText().toString();
					if(group.trim().isEmpty()){
						Toast t = Toast.makeText(mContext, "请输入群组名称",
								Toast.LENGTH_SHORT);			
						t.show();
						return;
					}
					Group g = new Group();
					g.setName(group);
					mAdapter.add(g);
					
					mListView.removeFooterView(mFooter);
	
					group_name.setText("");
					addNewBtn.setEnabled(true);
					
					//滚动到末尾
					int index = mListView.getFirstVisiblePosition();
					mListView.smoothScrollToPosition(index+1);
					
					//隐藏软键盘
			       InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE); 
			       imm.hideSoftInputFromWindow(group_name.getWindowToken(), 0);     
				}        	
	        });
	        
//	        cancelBtn.setOnClickListener(new OnClickListener(){
//				@Override
//				public void onClick(View v) {
//					group_name.setText("");
//				}        	
//	        });
	        
			return rootView;
		}
		
	    @Override  
	    public boolean onTouch(View v, MotionEvent event)  
	    {  
	        // TODO Auto-generated method stub  
	    	mListView.removeFooterView(mFooter);
	    	addNewBtn.setEnabled(true);
	    	
	        return true;  
	    } 
		
	
	    public void hideNo_data_image(){
	    	no_data_image.setVisibility(View.GONE);
	    }
	    
	    public void showNo_data_image(){
	    	no_data_image.setVisibility(View.VISIBLE);
	    }
	}

}
