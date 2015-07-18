package com.heverage.zhanyebao.client.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.db.ClientSQLiteHelper;
import com.heverage.zhanyebao.view.ToggleDownView;

public class ViewClientFragment extends NewClientFragment {

	
		private Button backBtn;
		private Button cacelBtn;
		private Button saveBtn;	

		
		private String mTitle;
		private int mLayout;
		private int mIndexOfInfo;
	
		public ViewClientFragment(int mIndexOfInfo, int mId, boolean mNewable,
				String mTitle, int mLayout) {
			super(mId, mNewable);
			this.mIndexOfInfo = mIndexOfInfo;
			this.mTitle = mTitle;
			this.mLayout = mLayout;
		}
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.client_new_fragment_layout_main, container, false);
			mContext = this.getActivity();
	
			this.mSQLiteHelper = new ClientSQLiteHelper(mContext);
	
			mClient = mSQLiteHelper.queryClient(mId);
	
			backBtn = (Button) rootView.findViewById(R.id.buttonBack);
			cacelBtn = (Button) rootView.findViewById(R.id.button1);
			saveBtn = (Button) rootView.findViewById(R.id.button2);
	
			OnClickListener finishListener = new OnClickListener() {
				@Override
				public void onClick(View v) {					
					finish();
				}
			};
	
			backBtn.setOnClickListener(finishListener);
			cacelBtn.setOnClickListener(finishListener);
	
			saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mSQLiteHelper.updateClient(mClient);
					finish();
				}
			});
	
			mInflater = inflater;
	

	
			mBasicView = (ToggleDownView) rootView
					.findViewById(R.id.client_new_basic_region);
			mJobView = mBasicView;
			mFamilyView = mBasicView;
			mIncomeView = mBasicView;
			mSourceView = mBasicView;
			mTemperView = mBasicView;
			mServiceView = mBasicView;
	
			mBasicView.setTitle(mTitle);
			mBasicView.setLayout(mLayout);
	
			switch (mIndexOfInfo) {
				case 0: {
					initBasic();
					break;
				}
				case 1: {
					initJob();
					break;
				}
				case 2: {
					initFamily();
					break;
				}
				case 3: {
					initIncome();
					break;
				}
				case 4: {
					initSource();
					break;
				}
				case 5: {
					initTemper();
					break;
				}
				case 6: {
					initService();
					break;
				}
				default: {
					break;
				}
			}
	
	
			return rootView;
		}
}