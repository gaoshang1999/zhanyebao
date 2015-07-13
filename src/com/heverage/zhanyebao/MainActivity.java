package com.heverage.zhanyebao;

 
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.heverage.zhanyebao.client.ClientActivity;
import com.heverage.zhanyebao.home.CActivity;
import com.heverage.zhanyebao.home.DActivity;
import com.heverage.zhanyebao.home.HomeActivity;
import com.heverage.zhanyebao.util.UpdateManager;

public class MainActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;
    private UpdateManager mUpdateManager;  
	
	private LayoutInflater inflater;

	private String[] titles = { "主页", "客户", "日程", "我" };
	private int[] images_clicked = { R.drawable.home, R.drawable.client,
			R.drawable.routine, R.drawable.star };

	private int[] images_default = { R.drawable.home_1, R.drawable.client_1,
			R.drawable.routine_1, R.drawable.star_1 };
	private Class[] frames = { HomeActivity.class, ClientActivity.class,
			CActivity.class, DActivity.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        //这里来检测版本是否需要更新  
        mUpdateManager = new UpdateManager(this);  
        mUpdateManager.checkUpdateInfo();  
		
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		inflater = getLayoutInflater();

		for (int i = 0; i < titles.length; i++) {
			TabSpec tabSpec = mTabHost.newTabSpec(titles[i]).setIndicator(
					getTabItemView(i));
			mTabHost.addTab(tabSpec, frames[i], null);
			// mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.btn_enable);
		}

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				for (int i = 0; i < titles.length; i++) {
					View v = mTabHost.getTabWidget().getChildAt(i);
					if (titles[i].equals(tabId)) {
						changeImageAndTextColor(v, i, true);
					} else {
						changeImageAndTextColor(v, i, false);
					}
				}
			}
		});

		
		mTabHost.setCurrentTab(0);
	}


	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
		View view = LayoutInflater.from(this).inflate(
				R.layout.view_image_upon_text, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);

		if (index == 0) {			
			imageView.setImageResource(images_clicked[index]);
		} else {

			imageView.setImageResource(images_default[index]);
		}
		
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		textView.setText(titles[index]);
		
		if (index == 0) {
			textView.setTextColor(Color.rgb(0x45, 0xc0, 0x1a) );
		} else {
			textView.setTextColor(Color.rgb(0x00, 0x00, 0x00) );
		}
		return view;
	}

	/**
	 * click 切换图片和文字颜色
	 * @param view
	 * @param index
	 * @param clicked
	 */
	private void changeImageAndTextColor(View view, int index, boolean clicked) {
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
		
		if (clicked) {			
			imageView.setImageResource(images_clicked[index]);
		} else {

			imageView.setImageResource(images_default[index]);
		}
		TextView textView = (TextView) view.findViewById(R.id.textView1);

		if (clicked) {
			textView.setTextColor(Color.rgb(0x45, 0xc0, 0x1a) );
		} else {
			textView.setTextColor(Color.rgb(0x00, 0x00, 0x00) );
		}
	}
	
	public void navigate(int index){
		assert(index>=0 && index < titles.length);
		mTabHost.setCurrentTab(index);
	}
}