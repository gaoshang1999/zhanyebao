package com.heverage.zhanyebao.home;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heverage.zhanyebao.MainActivity;
import com.heverage.zhanyebao.R;

 

public class HomeActivity extends Fragment {
	
	private GridView mgridView;
	private GridAdapter mGridAdapter;
    private Context mContext = null;  
	
	public HomeActivity() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = this.getActivity().getApplicationContext();  
		
//		this.getActivity().getActionBar().hide();
		
		View rootView = inflater
				.inflate(R.layout.home_layout_grid, container, false);
		
		
		mgridView = (GridView) rootView.findViewById(R.id.gridView1);

		mGridAdapter = new GridAdapter(this.getActivity());
		
		String[] items = { "客户", "日程", "提醒", "销售目标", "我的展业", "报表", "寻医问药", "心情钥匙", "我" };
		
		mGridAdapter.addElements(Arrays.asList(items));
		mgridView.setAdapter(mGridAdapter); 
			
		final MainActivity main =(MainActivity)this.getActivity();
		
		mgridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				if(position == 0){
					main.navigate(1);
				}else if(position == 1){
					main.navigate(2);
				}else if(position == 8){
					main.navigate(3);
				}else{
					Toast t = Toast.makeText(mContext, "暂未实现",
							Toast.LENGTH_SHORT);			
					t.show();
				}
			}
			
		});
		
		return rootView;
	}

	
	public class GridAdapter extends BaseAdapter {
		private List<String> list = new LinkedList<String>();
		// private List<String> list = getList(pageNO);
		private LayoutInflater mInflater;

		public GridAdapter(Context context) {
			// TODO Auto-generated constructor
			mInflater = LayoutInflater.from(context);	 
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		
		public void addElements(List<String> list){
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
			BaseViewHolder holder;

			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.view_image_upon_text, null);

				holder = new BaseViewHolder();
				
				holder.image = (ImageView) convertView.findViewById(R.id.imageView1);
				holder.text = (TextView) convertView.findViewById(R.id.textView1);

				convertView.setTag(holder);

			} else {
				holder = (BaseViewHolder) convertView.getTag();
			}
			// Bind the data efficiently with the holder.
			String text = list.get(position);
			holder.text.setText(text);
			holder.text.setTextSize(16);
			
	 
			holder.image.setImageDrawable( getResources().getDrawable(R.drawable.star));

			int padding = 10;
			holder.text.setPadding(2*padding, padding, 2*padding, padding);

			return convertView;		
		}
		
	}
	
	static class BaseViewHolder {
		ImageView image;
		
		TextView text;
	}
}
