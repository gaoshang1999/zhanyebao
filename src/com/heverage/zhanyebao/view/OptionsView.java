package com.heverage.zhanyebao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.util.OptionsDialog;

public class OptionsView extends LinearLayout {
	

	private TextView renderText;
	
	public OptionsView(Context context) {
		this(context, null);
	}

	public OptionsView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public OptionsView(final Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.OptionsView);

		
		CharSequence text = a.getString(R.styleable.OptionsView_textHint);
		final int array = a.getResourceId(R.styleable.OptionsView_array, R.array.gender);
		
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_options_view, null);
		
		final LinearLayout line = (LinearLayout)rootView.findViewById(R.id.client_element_line);
		final TextView hintText = (TextView)rootView.findViewById(R.id.textView1);
		hintText.setText(text);
		
		renderText = (TextView)rootView.findViewById(R.id.client_element);
		
		line.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {	
				OptionsDialog.createOptionDialog(context, array, mOptionCallbackListener, renderText);			
			}        	
        });	
		
		this.addView(rootView);
	}
	
	private OptionCallbackListener mOptionCallbackListener;
	
	public void setOptionCallbackListener(OptionCallbackListener l){
		mOptionCallbackListener = l;
	}
	
	

}
