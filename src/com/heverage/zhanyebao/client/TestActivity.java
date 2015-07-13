package com.heverage.zhanyebao.client;

import java.util.ArrayList;

import com.heverage.zhanyebao.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TestActivity extends Activity implements OnTouchListener , OnGestureListener
 {
	
	private String searchStr = null;  
	  
    private PinYinHelper mSort = null;  
  
    private Context mContext = null;  
  
    private TextView mIcon = null;  
  
    private ClientAdapter mAdapter = null;  
  
    private ListView mListView = null;  
  
    private EditText mSearchText = null;  
  
    private ListView mLetterListView = null;  
  
    private GestureDetector mGesture = null;  
  
    private ArrayList<String> newDataArrayList;  
  
    private ArrayList<String> checkArrayList;  
  
    private String[] newData;  
  
    private String[] english = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z"};  
      
    private String[] oldData = {"一败如水", "胆小如鼠", "引狼入室", "风驰电掣", "刀山火海", "一贫如洗",  
            "料事如神", "视死如归", "对答如流", "挥金如土", "铁证如山", "度日如年", "心急如焚", "巧舌如簧",  
            "如雷贯耳", "如履薄冰", "如日中天", "势如破竹", "稳如泰山", "骨瘦如柴", "爱财如命", "暴跳如雷",  
            "门庭若市", "恩重如山", "从善如流", "观者如云", "浩如烟海", "弃暗投明", "取长补短", "厚今薄古",  
            "同甘共苦", "因小失大", "优胜劣败", "自生自灭", "评头论足", "远交近攻", "求同存异", "well",  
            "hello", "one", "goodtime", "running", "java", "android", "jsp",  
            "html", "struts", "Charles", "Mark", "Bill", "Vincent", "William",  
            "Joseph", "James", "Henry", "Gary", "Martin"};  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);


//		mContext = TestActivity.this;  
//		  
//        mLetterListView = (ListView) this.findViewById(R.id.listView2);  
//  
//        mListView = (ListView) this.findViewById(R.id.listView1);  
//  
//        mSearchText = (EditText) this.getLayoutInflater().inflate(  
//                R.layout.client_list_header, null);  
//  
//        mIcon = (TextView) this.getLayoutInflater()  
//                .inflate(R.layout.client_icon, null);  
//  
//        mGesture = new GestureDetector(mContext, this);  
//  
//        getWindowManager()  
//                .addView(  
//                        mIcon,  
//                        new WindowManager.LayoutParams(  
//                                LayoutParams.WRAP_CONTENT,  
//                                LayoutParams.WRAP_CONTENT,  
//                                WindowManager.LayoutParams.TYPE_APPLICATION,  
//                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE  
//                                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,  
//                                PixelFormat.TRANSLUCENT));  
//  
//        mSort = new PinYinHelper();  
//  
//        checkArrayList = new ArrayList<String>();  
//          
//        //得到排序后的数据（String[]）  
//        newData = mSort.autoSort(oldData);  
//  
//        newDataArrayList = mSort.addChar(newData);
//        
//        
//        mLetterListView.setAdapter(new LetterAdapter(this, english));  
//  
//        mListView.addHeaderView(mSearchText);  
//  
//        mAdapter = new ClientAdapter(this, android.R.layout.simple_list_item_1,  
//                newDataArrayList);  
//  
//        mListView.setAdapter(mAdapter);  
//  
//        mListView.setOnTouchListener(new OnTouchListener()  
//        {  
//              
//            @Override  
//            public boolean onTouch(View v, MotionEvent event)  
//            {  
//                // TODO Auto-generated method stub  
//                if(mSearchText.hasFocus())  
//                    mSearchText.clearFocus();  
//                return false;  
//            }  
//        });  
//        //搜索框监听  
//        mSearchText.addTextChangedListener(new MyWatchToSearch());  
//  
//        mLetterListView.setOnTouchListener(this);  
//  
//        mLetterListView.setLongClickable(true);  
//        
//        
//        mLetterListView.setOnItemClickListener(new OnItemClickListener(){
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				int count = position;
//				if (0 <= count && count < 26)  
//	            {  
//	                mIcon.setText(english[count]);  
//	  
//	                mIcon.setVisibility(View.VISIBLE);  
//	                int pos = mAdapter.checkSection(english[count]) + 1;  
//	                if (pos >= 0) mListView.setSelectionFromTop(pos, 0);  
//	                mIcon.setVisibility(View.INVISIBLE); 
//	            }  
//	            else  
//	            {  
//	                mIcon.setVisibility(View.VISIBLE);  
//	                mIcon.setText("#");  
//	            }  
//			}
//        	
//        });
        
	}

	class MyWatchToSearch implements TextWatcher  
    {  
  
        @Override  
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after)  
        {  
            // TODO Auto-generated method stub  
        	mLetterListView.setVisibility(View.INVISIBLE); 
        }  
  
        @Override  
        public void onTextChanged(CharSequence s, int start, int before,  
                int count)  
        {  
            // TODO Auto-generated method stub  
            //搜索功能只针对ListView中的内容数据，不针对标题数据。  
            //使用没添加的分组字母的数据创建新的ArrayList  
            checkArrayList = mSort.toArrayList(newData);  
            searchStr = mSearchText.getText().toString();  
  
            if (searchStr.length() != 0)  
            {  
                checkSearchStr(searchStr);  
//                mAdapter = new ClientAdapter(mContext,  
//                        android.R.layout.simple_list_item_1, checkArrayList);  
                mListView.setAdapter(mAdapter);  
            }  
            
        }  
  
        @Override  
        public void afterTextChanged(Editable s)  
        {  
            // TODO Auto-generated method stub  
            if (searchStr.length() == 0)  
            {  
//                mAdapter = new ClientAdapter(mContext,  
//                        android.R.layout.simple_list_item_1, newDataArrayList);  
//                mListView.setAdapter(mAdapter);  
            }  
            mSearchText.requestFocus();  
            mLetterListView.setVisibility(View.VISIBLE); 
        }  
  
        /** 
         *  
         */  
        public void checkSearchStr(String search)  
        {  
  
            String tempSearch;  
            String tempList;  
            String newDataChar = null;  
            String checkArrayListItem = null;  
            //当输入的搜索字符为字母时  
            if (search.matches("[a-zA-Z]+"))  
            {  
                for (int i = 0; i < search.length(); i++)  
                {  
                    for (int j = 0; j < checkArrayList.size(); j++)  
                    {  
                        checkArrayListItem = checkArrayList.get(j);  
                        //如果联系人名称不为字母，则得到联系人名称的所有首字母  
                        if (!checkArrayListItem.matches("[a-zA-Z]+"))  
                        {  
                            newDataChar = mSort  
                                    .getAllPinYinHeadChar(checkArrayListItem);  
                        }  
                        else  
                        {  
                            newDataChar = checkArrayListItem;  
                        }  
                        //取出输入的字符串的第i个字母，并转换为大写  
                        tempSearch = String.valueOf(search.charAt(i))  
                                .toUpperCase();  
                        //取出得到的联系人名称所有首字母的第i个字母，并转换为大写  
                        tempList = String.valueOf(newDataChar.charAt(i))  
                                .toUpperCase();  
  
                        if (!(tempSearch.equals(tempList)))  
                        {  
                            checkArrayList.remove(j);  
                            newDataChar = null;  
                            j--;  
                        }  
                    }  
                }  
            }  
            //当输入的搜索字符为汉字时  
            else if (search.matches("[\u4e00-\u9fa5]+"))  
            {  
  
                for (int j = 0; j < checkArrayList.size(); j++)  
                {  
                    if (!checkArrayList.get(j).contains(search))  
                    {  
                        checkArrayList.remove(j);  
                        j--;  
                    }  
                }  
            }  
            else  
            {  
                search = String.valueOf(search.charAt(0));  
                for (int j = 0; j < checkArrayList.size(); j++)  
                {  
                    if (!checkArrayList.get(j).contains(search))  
                    {  
                        checkArrayList.remove(j);  
                        j--;  
                    }  
                }  
            }  
        }  
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
  
    public void util(MotionEvent e2)  
    {  
        //获取触摸点在导航图片中的位置  
        int i = (int) (e2.getY() - mLetterListView.getTop());  
  
          
  
//        mLetterListView.setImageResource(R.drawable.contact_list_scroll_pressed);  
  
        int pos;  
        int count;  
        if (0 <= i && i <= 30)  
        {  
            mIcon.setText("搜");  
            mIcon.setVisibility(View.VISIBLE);  
            mListView.setSelectionFromTop(0, 0);  
        }  
        else  
        {  
            count = (int) ((i - 31) / ((float) (mLetterListView.getHeight() - 62) / 26f));  
  
            if (0 <= count && count < 26)  
            {  
                mIcon.setText(english[count]);  
  
                mIcon.setVisibility(View.VISIBLE);  
                pos = mAdapter.checkSection(english[count]) + 1;  
                if (pos >= 0) mListView.setSelectionFromTop(pos, 0);  
            }  
            else  
            {  
                mIcon.setVisibility(View.VISIBLE);  
                mIcon.setText("#");  
            }  
        }  
  
    }  

}
