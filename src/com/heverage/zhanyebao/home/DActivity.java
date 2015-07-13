package com.heverage.zhanyebao.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DActivity extends Fragment {
	
	public DActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		TextView tv = new TextView(container.getContext());
		tv.setText("This is D Activity!");
		tv.setGravity(Gravity.CENTER);		 
		
		return tv;
	}

}
