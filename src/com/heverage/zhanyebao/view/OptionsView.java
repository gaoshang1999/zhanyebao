package com.heverage.zhanyebao.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

		
		optionTitle = a.getString(R.styleable.OptionsView_textHint);
		final int arrayId = a.getResourceId(R.styleable.OptionsView_array, R.array.sex);
		
		View rootView = LayoutInflater.from(context).inflate(R.layout.view_options_view, null);
		
		final LinearLayout line = (LinearLayout)rootView.findViewById(R.id.client_element_line);
		final TextView title = (TextView)rootView.findViewById(R.id.textView1);
		title.setText(optionTitle);
		
		renderText = (TextView)rootView.findViewById(R.id.client_element);
		
		line.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {	
				OptionsDialog.createOptionDialog(context, arrayId, mOptionCallbackListener, renderText);			
			}        	
        });	
		
		this.addView(rootView);
		
	    Resources res = context.getResources();
	    constants =res.getStringArray(arrayId);
	}
	private String[] constants;
	private CharSequence optionTitle;
	
	private OptionCallbackListener mOptionCallbackListener;
	
	public void setOptionCallbackListener(OptionCallbackListener l){
		mOptionCallbackListener = l;
	}
	
	
	public boolean isClicked(){

		 if(renderText.getText().toString().trim().isEmpty()){
			 renderText.setError("请选择"+optionTitle+"!");
			 return false;
		 }
		 return true;
	}
	
    public void setRenderText(int key){
    	renderText.setText(constants[key]);
    }
}
