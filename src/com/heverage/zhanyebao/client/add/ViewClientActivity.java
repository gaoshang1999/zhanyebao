package com.heverage.zhanyebao.client.add;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Client;
import com.heverage.zhanyebao.view.ToggleDownView;

public class ViewClientActivity extends ActionBarActivity {

	private int mIndexOfInfo;
	private String mTitle;
	private int mLayout;	
	private int mId;
	
	/**
	 * 默认为新建 客户 
	 */
	private boolean mNewable = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);
		
		if (this.getIntent() != null && this.getIntent().getExtras() != null) {
			mTitle = this.getIntent().getExtras().getString("title");
			mLayout = this.getIntent().getExtras().getInt("layout");
			mId = this.getIntent().getExtras().getInt("id");
			mIndexOfInfo= this.getIntent().getExtras().getInt("index");
			mNewable = false;
		}
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new ViewClientFragment(mIndexOfInfo, mId, mNewable, mTitle ,mLayout))
					.commit();
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}



}
