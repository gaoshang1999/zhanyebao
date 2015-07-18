package com.heverage.zhanyebao.client.add;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.heverage.zhanyebao.R;

public class NewClientActivity extends ActionBarActivity {
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
			mId = this.getIntent().getExtras().getInt("id");
			mNewable = false;
		}
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new NewClientFragment(mId, mNewable))
					.commit();
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
}
