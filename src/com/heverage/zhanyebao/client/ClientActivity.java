package com.heverage.zhanyebao.client;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.add.NewClientActivity;
import com.heverage.zhanyebao.client.care.AddContactActivity;
import com.heverage.zhanyebao.client.care.CareActivity;
import com.heverage.zhanyebao.client.group.GroupActivity;
import com.heverage.zhanyebao.client.model.Client;

public class ClientActivity extends Fragment implements OnTouchListener , OnGestureListener{
	
	public ClientActivity() {
	}

    private Context mContext = null;  
  
    private TextView mIcon = null;  
  
    private ClientAdapter mAdapter = null;  
  
    private ListView mListView = null;  
  
    private LinearLayout header = null;
    private EditText mSearchText = null;  
  
    private ListView mLetterListView = null;  
  
    private GestureDetector mGesture = null;  
  
    private List<Client> mDataList;  
  
    private String[] english = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z"};  
      
    private Client mClient;
    
    private Button careBtn;
    private Button groupBtn;
    private Button addBtn;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater
				.inflate(R.layout.client_layout_main, container, false);	
		
//		this.getActivity().getActionBar().hide();
		
		mContext = this.getActivity();  
		  
        mLetterListView = (ListView) rootView.findViewById(R.id.listView2);  
  
        mListView = (ListView) rootView.findViewById(R.id.listView1);  
  
        header = (LinearLayout)inflater.inflate(  
                R.layout.client_list_header, null); 
        
        mSearchText = (EditText) header.findViewById(R.id.searchText);  
        mSearchText.setFocusable(true);
        
        careBtn = (Button) header.findViewById(R.id.button1); 
        groupBtn = (Button) header.findViewById(R.id.button2); 
        addBtn = (Button) header.findViewById(R.id.button3); 
  
        mIcon = (TextView) inflater  
                .inflate(R.layout.client_icon, null);  
  
        mGesture = new GestureDetector(mContext, this);  
  
        this.getActivity().getWindowManager()  
                .addView(  
                        mIcon,  
                        new WindowManager.LayoutParams(  
                                LayoutParams.WRAP_CONTENT,  
                                LayoutParams.WRAP_CONTENT,  
                                WindowManager.LayoutParams.TYPE_APPLICATION,  
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  
                                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,  
                                PixelFormat.TRANSLUCENT));  
  
        mClient = new Client();          
        mDataList = mClient.buildGroupedClients();
       
        mLetterListView.setAdapter(new LetterAdapter(mContext, english));  
  
        mListView.addHeaderView(header);  
  
        mAdapter = new ClientAdapter(mContext, android.R.layout.simple_list_item_1,  
                mDataList);  
  
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
        
        mSearchText.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
					mLetterListView.setVisibility(View.GONE);
				}else{
					mLetterListView.setVisibility(View.VISIBLE);
				}
			}
        	
        });
  
        mLetterListView.setOnTouchListener(this);  
  
        mLetterListView.setLongClickable(true);  
        
        
        mLetterListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int count = position;
				if (0 <= count && count < 26)  
	            {  
	                mIcon.setText(english[count]);  
	  
	                mIcon.setVisibility(View.VISIBLE);  
	                int pos = mAdapter.checkSection(english[count]) + 1;  
	                if (pos >= 0) mListView.setSelectionFromTop(pos, 0);  
	                mIcon.setVisibility(View.INVISIBLE); 
	            }  
	            else  
	            {  
	                mIcon.setVisibility(View.VISIBLE);  
	                mIcon.setText("#");  
	            }  
			}
        	
        });
        
 
        careBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, CareActivity.class);
				startActivity(intent);
			}
        	
        });
        
        groupBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, GroupActivity.class);
				startActivity(intent);
			}        	
        });
        
        addBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				LayoutInflater factory = LayoutInflater.from(mContext);  
			    //获得自定义对话框  
			    View view = factory.inflate(R.layout.client_new_dialog, null);  
			      
			    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
			           .setView(view)  
			           .create();  
			    dialog.show();  
			    
			    Button newBtn = (Button)view.findViewById(R.id.button1);
			    newBtn.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						Intent intent = new Intent(mContext, NewClientActivity.class);
						startActivity(intent);
					}
				});
			    
			    Button impBtn = (Button)view.findViewById(R.id.button2);
			    impBtn.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						Intent intent = new Intent(mContext, AddContactActivity.class);
						startActivity(intent);
					}
				});
			    
			    Button cancel = (Button)view.findViewById(R.id.button3);
			    cancel.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
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
        return mGesture.onTouchEvent(event);  
    }  
  
    @Override  
    public boolean onDown(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
//        util(e);  
        return false;  
    }  
  
    @Override  
    public void onShowPress(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public boolean onSingleTapUp(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
  
//        mLetterListView.setImageResource(R.drawable.contact_list_scroll_normal);  
        mIcon.setVisibility(View.INVISIBLE);  
        return false;  
    }  
  
    @Override  
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,  
            float distanceY)  
    {  
//        util(e2);  
        // TODO Auto-generated method stub  
        return false;  
    }  
  
    @Override  
    public void onLongPress(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
//        mLetterListView.setImageResource(R.drawable.contact_list_scroll_normal);  
        mIcon.setVisibility(View.INVISIBLE);  
    }  
  
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY)  
    {  
        // TODO Auto-generated method stub  
//        mLetterListView.setImageResource(R.drawable.contact_list_scroll_normal);  
        mIcon.setVisibility(View.INVISIBLE);  
        return false;  
    }  
  
}
