package com.heverage.zhanyebao.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.heverage.zhanyebao.R;

public class OptionActivity extends ActionBarActivity {
	
	private String mTitle;
	private int mResourceId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		if (this.getIntent() != null && this.getIntent().getExtras() != null) {
			mTitle = this.getIntent().getExtras().getString("title");
			mResourceId = this.getIntent().getExtras().getInt("resourceId");
		}
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
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
	public class PlaceholderFragment extends Fragment {
		private Context mContext = null;

		private ArrayAdapter<String> mAdapter = null;

		private ListView mListView = null;

		private Button backBtn;
		private TextView mTextView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.util_option_layout_main, container, false);
			mContext = this.getActivity();

			mListView = (ListView) rootView.findViewById(R.id.listView1);

			mTextView = (TextView) rootView.findViewById(R.id.textView1);
			mTextView.setText(mTitle);
			
			Resources res = mContext.getResources();
			final String[] provinces = res.getStringArray(mResourceId);
			mAdapter = new ArrayAdapter<String>(mContext,
					android.R.layout.simple_list_item_1, provinces);
			
			mListView.setAdapter(mAdapter);

			backBtn = (Button) rootView.findViewById(R.id.button1);
			backBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});

			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					String clicked = mAdapter.getItem(position);
	
					Intent i = new Intent();
					i.putExtra("clicked", clicked);
					setResult(RESULT_OK, i);
					finish();
					
				}

			});
			return rootView;
		}


	}

}
