package com.heverage.zhanyebao.client.add;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.heverage.zhanyebao.R;

public class RegionActivity extends ActionBarActivity {

	private String mProvince;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		if (this.getIntent() != null && this.getIntent().getExtras() != null) {
			mProvince = this.getIntent().getExtras().getString("province");
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

		private Map<String, List<String>> provinceCitysMap;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.client_region_layout_main, container, false);
			mContext = this.getActivity();

			mListView = (ListView) rootView.findViewById(R.id.listView1);

			if (mProvince == null) {
				Resources res = mContext.getResources();
				final String[] provinces = res.getStringArray(R.array.province);
				mAdapter = new ArrayAdapter<String>(mContext,
						android.R.layout.simple_list_item_1, provinces);
			} else {
				initCitys();
				final List<String> provinces = provinceCitysMap.get(mProvince);
				mAdapter = new ArrayAdapter<String>(mContext,
						android.R.layout.simple_list_item_1, provinces);
			}

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
					String city = mAdapter.getItem(position);

					if (mProvince == null) {
						mCity = city;
						Intent intent = new Intent(mContext,
								RegionActivity.class);
						intent.putExtra("province", city);
						startActivityForResult(intent, 100);
					} else {
						Intent i = new Intent();
						i.putExtra("city", city);
						setResult(RESULT_OK, i);
						finish();
					}
				}

			});
			return rootView;
		}

		private String mCity;

		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (requestCode == 100) {
				if (resultCode == RESULT_OK) {
					// A contact was picked. Here we will just display it
					// to the user.
					String city = data.getStringExtra("city");
					Intent i = new Intent();
					i.putExtra("city", mCity  + " " + city);
					setResult(RESULT_OK, i);
					finish();
				}
			}
		}

		public void initCitys() {
			Resources res = mContext.getResources();
			final String[] citys = res.getStringArray(R.array.city);
			provinceCitysMap = new HashMap<String, List<String>>(citys.length);

			for (String c : citys) {
				int split = c.indexOf("-");
				String province = c.substring(0, split);
				String city = c.substring(split + 1);
				List<String> cityList = provinceCitysMap.get(province);
				if (cityList == null) {
					cityList = new ArrayList<String>();
					provinceCitysMap.put(province, cityList);
				}
				cityList.add(city);
			}
		}
	}

}
