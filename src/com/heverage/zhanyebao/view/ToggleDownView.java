package com.heverage.zhanyebao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;

public class ToggleDownView extends LinearLayout {
	
	private boolean toggled;
	
	private TextView mTitleTextView;
	private ViewStub mViewstub;
	private ImageView mRightOrDownImage;
	private Context mContext;
	
	public ToggleDownView(Context context) {
		this(context, null);
	}

	public ToggleDownView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ToggleDownView(final Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mContext = context;
		
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ToggleDownView);

		
		CharSequence text = a.getString(R.styleable.ToggleDownView_text);
		int includeLayout = a.getResourceId(R.styleable.ToggleDownView_includeLayout, R.layout.client_new_basic);
		toggled = a.getBoolean(R.styleable.ToggleDownView_toggled, false);
		
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_toggle_down_view, null);
		
		final LinearLayout client_basic_line = (LinearLayout)rootView.findViewById(R.id.client_basic_line);
		mRightOrDownImage = (ImageView)rootView.findViewById(R.id.client_basic_image);
		mTitleTextView = (TextView)rootView.findViewById(R.id.textViewBasic);
		mTitleTextView.setText(text);
		
		mViewstub = (ViewStub)rootView.findViewById(R.id.viewstub);
		mViewstub.setLayoutResource(includeLayout);
//		mViewstub.setVisibility(View.VISIBLE);
 
		if(!toggled){
			mViewstub.setVisibility(View.GONE);
			mRightOrDownImage.setImageDrawable(context.getResources().getDrawable(R.drawable.triangle_right));
		}
				
		
		client_basic_line.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {	
				if(toggled){
					mRightOrDownImage.setImageDrawable(context.getResources().getDrawable(R.drawable.triangle_right));
					mViewstub.setVisibility(View.GONE);
					toggled = false;
				}else{
					mRightOrDownImage.setImageDrawable(context.getResources().getDrawable(R.drawable.triangle_down));
					mViewstub.setVisibility(View.VISIBLE);
					toggled = true;
				}					
			}        	
        });	
		
		this.addView(rootView);
	}
	
	public void setTitle(String title){
		mTitleTextView.setText(title);
	}
	
	public void setLayout(int layout){
		mViewstub.setLayoutResource(layout);
		
//		mViewstub.inflate();


		mViewstub.setVisibility(View.VISIBLE);
	}

	public void show(){
		mViewstub.setVisibility(View.VISIBLE);
		if(!toggled){
			mViewstub.setVisibility(View.GONE);
			mRightOrDownImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.triangle_right));
		}
	}
}
