package com.heverage.zhanyebao.client.group;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.db.GroupSQLiteHelper;
import com.heverage.zhanyebao.client.group.GroupActivity.PlaceholderFragment;
import com.heverage.zhanyebao.client.model.Group;
import com.heverage.zhanyebao.util.GenaralEditDialog;
import com.heverage.zhanyebao.util.GenaralEditDialog.GenaralEditDialogCallBack;
import com.heverage.zhanyebao.view.SwipeItemView;
import com.heverage.zhanyebao.view.SwipeItemView.OnSlideListener;
import com.heverage.zhanyebao.view.SwipeListView;

public class GroupAdapterSwiped extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Group> mGroupList;
    private Context mContext;
    private SwipeItemView mLastSlideViewWithStatusOn;
    
    private GroupSQLiteHelper mSQLiteHelper;
    private PlaceholderFragment mPlaceholderFragment;
    public GroupAdapterSwiped(Context context, PlaceholderFragment placeholderFragment) {
        mInflater = LayoutInflater.from(context);
        this.mContext=context;
        
        this.mSQLiteHelper = new GroupSQLiteHelper(context);
        
        this.mGroupList = this.mSQLiteHelper.queryAllGroups();
        
        this.mPlaceholderFragment = placeholderFragment;
    }

    @Override
    public int getCount() {
        return mGroupList.size();
    }

    @Override
    public Group getItem(int position) {
        return mGroupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public void add(Group g){
    	this.mSQLiteHelper.saveGroup(g);
    	mGroupList.add(g);
    	this.notifyDataSetChanged();
	    if(this.getCount() > 0){
	    	this.mPlaceholderFragment.hideNo_data_image();
	    }
    }
    
    public void update(int position, String newName){
    	Group g = mGroupList.get(position);
    	g.setName(newName);
    	this.mSQLiteHelper.updateGroup(g);    	
    	this.notifyDataSetChanged();
    }
    
    public void delete(int position){
    	
    	Group g = mGroupList.get(position);
    	this.mSQLiteHelper.deleteGroup(g);
		mGroupList.remove(position);
		notifyDataSetChanged(); 
	    if(this.getCount() > 0){
	    	this.mPlaceholderFragment.hideNo_data_image();
	    }else if(this.getCount() == 0){
	    	this.mPlaceholderFragment.showNo_data_image();
	    }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        SwipeItemView slideView = (SwipeItemView) convertView;
    	final String name = getItem(position).getName();
        if (slideView == null) {
        	
            View itemView = mInflater.inflate(  
                    R.layout.client_group_list_item, null);   
            

            slideView = new SwipeItemView(mContext);
            slideView.setContentView(itemView);

            holder = new ViewHolder(slideView);
            slideView.setOnSlideListener(new OnSlideListener() {
				
				@Override
				public void onSlide(View view, int status) {
					// TODO Auto-generated method stub
					 if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
				            mLastSlideViewWithStatusOn.shrink();
				        }

				        if (status == SLIDE_STATUS_ON) {
				            mLastSlideViewWithStatusOn = (SwipeItemView) view;
				        }
				}
			});
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        Group item = getItem(position);
//        item.slideView = slideView;
        if(SwipeListView.mFocusedItemView!=null){
        	SwipeListView.mFocusedItemView.shrink();
        }


        holder.text.setText(item.getName());

        holder.deleteHolder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				delete(position);
	 
			}
		});
        
        holder.renameHolder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String groupNamp = mGroupList.get(position).getName();			
				
				GenaralEditDialogCallBack cb = new GenaralEditDialogCallBack(){

					@Override
					public String getTitle() {
						// TODO Auto-generated method stub
						return "重命名群组";
					}

					@Override
					public String getText() {
						// TODO Auto-generated method stub
						return groupNamp;
					}

					@Override
					public void setText(String value) {
						// TODO Auto-generated method stub
						 update(position, value);
					}
					
					
				};
				
				GenaralEditDialog ged = new GenaralEditDialog();
				ged.createEditDialog(mContext, cb);
			}
		});

        return slideView;
    }
    private static class ViewHolder {
 
        public TextView text;

        public ViewGroup deleteHolder;
        public ViewGroup renameHolder;

        ViewHolder(View view) {
        	text = (TextView) view.findViewById(R.id.group_name);
        	
        	deleteHolder = (ViewGroup)view.findViewById(R.id.delete_holder);
        	renameHolder = (ViewGroup)view.findViewById(R.id.rename_holder);
        }
    }
}