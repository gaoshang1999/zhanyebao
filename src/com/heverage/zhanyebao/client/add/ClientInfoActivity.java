package com.heverage.zhanyebao.client.add;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.db.ClientSQLiteHelper;
import com.heverage.zhanyebao.client.model.Client;

public class ClientInfoActivity extends ActionBarActivity {

	private int mId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);		

		if (this.getIntent() != null && this.getIntent().getExtras() != null) {
			mId = this.getIntent().getExtras().getInt("id");
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
		
		private GridView mgridView;
		private GridAdapter mGridAdapter;

		private Button backBtn;
		private Button saveBtn;

		private String[] items = { "基本", "工作", "家庭", "收入", "来源", "性格", "服务", "其他", ""};
		private String[] titles = {"基本情况", "工作情况", "家庭情况", "收入情况", "来源信息", "性格相关", "服务"};
		private int[] layouts = {R.layout.client_new_basic, R.layout.client_new_job, R.layout.client_new_family, R.layout.client_new_income, R.layout.client_new_source, R.layout.client_new_temper, R.layout.client_new_service};
		
	    private Client mClient;
		private ClientSQLiteHelper mSQLiteHelper;
		  
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.client_new_all_info, container, false);
			mContext = this.getActivity();
	        this.mSQLiteHelper = new ClientSQLiteHelper(mContext);


			backBtn = (Button) rootView.findViewById(R.id.buttonBack);
			backBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			mgridView = (GridView) rootView.findViewById(R.id.gridView1);

			mGridAdapter = new GridAdapter(mContext);
			
			
			mGridAdapter.addAll(Arrays.asList(items));
			mgridView.setAdapter(mGridAdapter); 
			
			
			mgridView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub		
					   if(position < titles.length){
							Intent intent = new Intent(mContext, ViewClientActivity.class);
							intent.putExtra("title", titles[position]);
							intent.putExtra("layout", layouts[position]);
							intent.putExtra("id", mId);
							intent.putExtra("index", position);
							startActivity(intent);
					   }else{
							Toast t = Toast.makeText(mContext, "暂未实现",
									Toast.LENGTH_SHORT);			
							t.show();
					   }
				}
				
			});
			
			Button allBtn = (Button) rootView.findViewById(R.id.client_all_info);
			allBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, NewClientActivity.class);
					intent.putExtra("id", mId);
					startActivity(intent);
				}
			});
					
			client_name = (TextView) rootView.findViewById(R.id.client_name);
			client_sex = (TextView) rootView.findViewById(R.id.client_sex);
			
		    Resources res = mContext.getResources();
		    constants =res.getStringArray(R.array.sex);
			
			return rootView;
		}
		
		private TextView client_name;
		private TextView client_sex;
		private String[] constants;
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
	        mClient = mSQLiteHelper.queryClient(mId);	  
			client_name.setText(mClient.getClient_name());			
			client_sex.setText(constants[mClient.getSex()]);
		}


		
	}
	

	public class GridAdapter extends BaseAdapter {
		private List<String> list = new LinkedList<String>();
	    private Context mContext;
		private LayoutInflater mInflater;

		public GridAdapter(Context context) {
			// TODO Auto-generated constructor
			mContext = context;
			mInflater = LayoutInflater.from(context);	 
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		
		public void addAll(List<String> list){
			this.list.addAll(list);
		}
		/**
		 * Since the data comes from an array, just returning the index is sufficent
		 * to get at the data. If we were using a more complex data structure, we
		 * would return whatever object represents one row in the list.
		 * 
		 * @see android.widget.ListAdapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		/**
		 * Use the array index as a unique id.
		 * 
		 * @see android.widget.ListAdapter#getItemId(int)
		 */
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			TextView textView;

			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.client_new_all_info_grid_item, null);

				textView = (TextView) convertView.findViewById(R.id.textView1);

				convertView.setTag(textView);

			} else {
				textView = (TextView) convertView.getTag();
			}
			// Bind the data efficiently with the holder.
			String text = list.get(position);
			
			textView.setText(text);

			return convertView;		
		}
		
	 
	}

}
