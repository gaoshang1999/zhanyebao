package com.heverage.zhanyebao.client.add;

import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.model.Address;
import com.heverage.zhanyebao.client.model.Client;
import com.heverage.zhanyebao.client.model.Phone;
import com.heverage.zhanyebao.util.OptionActivity;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.view.OptionsView;
import com.heverage.zhanyebao.view.ToggleDownView;

public class NewClientActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dummy);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(this))
					.commit();
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
	public class PlaceholderFragment extends Fragment implements OnTouchListener{
		private Context mContext = null;

		private Client mClient;;


		private Button backBtn;
		private Button cacelBtn;
		private Button saveBtn;

		private LayoutInflater mInflater;
		private ToggleDownView mBasicView;
		private ToggleDownView mJobView;
		private ToggleDownView mFamilyView;
		private ToggleDownView mIncomeView;
		private ToggleDownView mSourceView;
		private ToggleDownView mTemperView;
		private ToggleDownView mServiceView;
		
		public PlaceholderFragment(NewClientActivity activity) {

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_new_layout_main,
					container, false);
			mContext = this.getActivity();

			mClient = new Client();


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
			
			saveBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					finish();
				}        	
	        });
            
			mInflater = inflater;
			
			mBasicView = (ToggleDownView)rootView.findViewById(R.id.client_new_basic_region);
			mJobView = (ToggleDownView)rootView.findViewById(R.id.client_new_job_region);
			mFamilyView = (ToggleDownView)rootView.findViewById(R.id.client_new_family_region);
			mIncomeView = (ToggleDownView)rootView.findViewById(R.id.client_new_income_region);
			mSourceView = (ToggleDownView)rootView.findViewById(R.id.client_new_source_region);
			mTemperView = (ToggleDownView)rootView.findViewById(R.id.client_new_temper_region);
			mServiceView = (ToggleDownView) rootView.findViewById(R.id.client_new_service_region);
			
			mBasicView.show();
			mJobView.show();
			mFamilyView.show();
			mIncomeView.show();
			mSourceView.show();
			mTemperView.show();
			mServiceView.show();
			
			initBasic();
			initJob();			
			initFamily();
			initIncome();
		    initSource();
		    initTemper();
		    initService();
		    
			return rootView;
		}
		
	    @Override  
	    public boolean onTouch(View v, MotionEvent event)  
	    {  
	        // TODO Auto-generated method stub  	  
	        if(mNameText.hasFocus()) { 
	        	mNameText.clearFocus();  
	        }
	        return true;  
	    }  
		
	    /**
	     * 基本信息
	     */
		public void initBasic(){
			initNameView();

			initGenderView();
			initBirthdayView();
			
			initCityTypeView();
			initEducationView();
			
			initRegionView();
			
			initPhoneView();
			initAddressView();
		}
		private EditText mNameText;
		
		public void initNameView(){		
			 LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_name_line);			
			 final EditText renderText = (EditText)mBasicView.findViewById(R.id.client_name);
			 mNameText = renderText;
			 
	        if(mNameText.hasFocus()) { 
	        	mNameText.clearFocus();  
	        }
			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setName(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		/**
		 * 选项对话框选择性别
		 */
		public void initGenderView(){	
			OptionsView line = (OptionsView)mBasicView.findViewById(R.id.client_gender_line);			

			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getGender() == 0) ? 1 : mClient.getGender();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setGender(key);

				}						
			};			
			line.setOptionCallbackListener(oc);	
		}
		

		
		public void initBirthdayView(){
			LinearLayout dialogView = (LinearLayout)mInflater.inflate(R.layout.util_datepicker_dialog, null);  
		      
		    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
		           .setView(dialogView)  
		           .create();  
		    
		    LinearLayout birthday = (LinearLayout)mBasicView.findViewById(R.id.client_birthday_line);			
		    final TextView birthdayText = (TextView)mBasicView.findViewById(R.id.client_birthday);
		    final TextView ageText = (TextView)mBasicView.findViewById(R.id.client_age);
		    
		    final DatePicker dp = (DatePicker)dialogView.findViewById(R.id.datePicker1);	
		    Button cancel = (Button)dialogView.findViewById(R.id.button1);	
		    Button ok = (Button)dialogView.findViewById(R.id.button2);	
		    
		    birthday.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.show();					
				}
		    });	
		    
		    ok.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
					String text = dp.getYear()+"年"+(dp.getMonth()+1)+"月"+dp.getDayOfMonth()+"日";					
					birthdayText.setText(text);
					Calendar c = Calendar.getInstance();					
					ageText.setText((c.get(Calendar.YEAR)-dp.getYear())+"");
					c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());					
					mClient.setBirthday(c.getTime());
				}
		    });	
		    
		    cancel.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
				}
		    });	
		}
		
		/**
		 * 选项对话框选择性别
		 */
		public void initCityTypeView(){

			OptionsView line = (OptionsView)mBasicView.findViewById(R.id.client_cityType_line);	
	
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getCityType() == 0) ? 1 : mClient.getCityType();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setCityType(key);
				}						
			};
			
			line.setOptionCallbackListener(oc);
		}
		

		public void initEducationView(){

			OptionsView line = (OptionsView)mBasicView.findViewById(R.id.client_educaiton_line);				
					
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getEducaiton() == 0) ? 1 : mClient.getEducaiton();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setEducaiton(key);
				}						
			};
			
			line.setOptionCallbackListener(oc);
		}
		
		TextView regionRenderText = null;
		
		public void initRegionView(){

		    LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_region_line);			
		    final TextView renderText = (TextView)mBasicView.findViewById(R.id.client_region);
			this.regionRenderText = renderText;
			
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					Intent intent = new Intent(mContext, RegionActivity.class);
					startActivityForResult(intent, REGION_requestCode);					
				}        	
	        });			
		}
		
		private final int REGION_requestCode = 100;
			
		 public void onActivityResult(int requestCode, int resultCode,
	             Intent data) {
			if (requestCode == REGION_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String text = data.getStringExtra("city");
	            	 this.regionRenderText.setText(text);
	            	 mClient.setRegion(text);
	             }
	         } else if (requestCode == PHONE_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 Phone phone = data.getParcelableExtra("phone");
	            	 mClient.getPhoneList().add(phone);
	            	 afterPhoneCreate(phone);
	             }
	         }else if (requestCode == ADDRESS_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 Address add = data.getParcelableExtra("address");
	            	 mClient.getAddressList().add(add);
	            	 afterAddressCreate(add);
	             }
	         }else if (requestCode == INDUSTRY_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String ind = data.getStringExtra("clicked");
	            	 mClient.setIndustry(ind);
	            	 afterIndustrySelected(ind);
	             }
	         }else if (requestCode == JOBTYPE_requestCode) {
	             if (resultCode == RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String ind = data.getStringExtra("clicked");
	            	 mClient.setJobType(ind);
	            	 afterJobTypeSelected(ind);
	             }
	         }
			
	     }
	
		 
		 
		 public void initPhoneView(){
			 	Button buttonAddPhone =    (Button) mBasicView.findViewById(R.id.buttonAddPhone);			
			    
			    buttonAddPhone.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {					
						Intent intent = new Intent(mContext, NewPhoneActivity.class);
						startActivityForResult(intent, PHONE_requestCode);					
					}        	
		        });	
		 }
		
		private final int PHONE_requestCode = 101;
		
		public void afterPhoneCreate(final Phone phone){
			final LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_phone_line);	
 
			
			final LinearLayout phoneView = (LinearLayout)mInflater.inflate(R.layout.client_new_basic_phone_line, null);  
			final TextView renderText = (TextView)phoneView.findViewById(R.id.client_phone);
			renderText.setText(phone.getPhoneNum());
			
			Button buttonDeletePhone = (Button) phoneView.findViewById(R.id.buttonDeletePhone);			
			buttonDeletePhone.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					mClient.getPhoneList().remove(phone);
					line.removeView(phoneView);
				}        	
	        });	
			line.addView(phoneView);
		}
		
		
		 public void initAddressView(){
			 	Button buttonAddAddress =    (Button) mBasicView.findViewById(R.id.buttonAddAddress);			
			    
			 	buttonAddAddress.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {					
						Intent intent = new Intent(mContext, NewAddressActivity.class);
						startActivityForResult(intent, ADDRESS_requestCode);					
					}        	
		        });	
		 }
		
		private final int ADDRESS_requestCode = 102;
		
		public void afterAddressCreate(final Address add){
			final LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_address_line);
			
			final LinearLayout phoneView = (LinearLayout)mInflater.inflate(R.layout.client_new_basic_address_line, null);  
			final TextView renderText = (TextView)phoneView.findViewById(R.id.client_address);
			renderText.setText(add.getRegion()+" "+add.getAddress());
			
		    Resources res = mContext.getResources();
		    final String[] constants =res.getStringArray(R.array.addressType);
			final TextView renderText2 = (TextView)phoneView.findViewById(R.id.client_addressType);
			renderText2.setText(constants[add.getAddressType()]);
			
			Button buttonDeleteAddress = (Button) phoneView.findViewById(R.id.buttonDeleteAddress);			
			buttonDeleteAddress.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					mClient.getAddressList().remove(add);
					line.removeView(phoneView);
				}        	
	        });	
			line.addView(phoneView);
		}
		
		
	
	    /**
	     * 工作情况
	     */
		public void initJob(){
			initJobUnitView();
			initIndustryView();
			initJobNatureView();
			initJobTypeView();
			initJobTitleView();
		}
		
		public void initJobUnitView(){		
			 LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_job_unit_line);			
			 final EditText renderText = (EditText)mJobView.findViewById(R.id.client_job_unit);
			 
			 renderText.clearFocus();
			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setJobUnit(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		private int INDUSTRY_requestCode = 200;
		private TextView industryRenderText;
		public void initIndustryView(){			

		    LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_job_industry_line);			
		    final TextView renderText = (TextView)mJobView.findViewById(R.id.client_job_industry);
		    industryRenderText = renderText;
		    
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					
					Intent intent = new Intent(mContext, OptionActivity.class);
					intent.putExtra("title", "选择行业类型");
					intent.putExtra("resourceId", R.array.industry);
					startActivityForResult(intent, INDUSTRY_requestCode);	
				}        	
	        });					
			
		}
		
		public void afterIndustrySelected(String ind){
			industryRenderText.setText(ind);
		}
		
		/**
		 * 选项对话框选择企业性质
		 */
		public void initJobNatureView(){			

			OptionsView line = (OptionsView)mJobView.findViewById(R.id.client_job_nature_line);			
 
			
 
		    OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getJobNature() == 0) ? 1 : mClient.getJobNature();
				}
	
				@Override
				public void callback(int key, String value) {
				// TODO Auto-generated method stub
							mClient.setJobNature(key);
 
						}						
					};
			line.setOptionCallbackListener(oc);
		}
		
		
		private int JOBTYPE_requestCode = 201;
		private TextView jobTypeRenderText;
		
		public void initJobTypeView(){			
			
		    LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_job_type_line);			
		    final TextView renderText = (TextView)mJobView.findViewById(R.id.client_job_type);
		    jobTypeRenderText = renderText;
		    
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					
					Intent intent = new Intent(mContext, OptionActivity.class);
					intent.putExtra("title", "选择职业类型");
					intent.putExtra("resourceId", R.array.jobType);
					startActivityForResult(intent, JOBTYPE_requestCode);	
				}        	
	        });					
			
		}
		
		public void afterJobTypeSelected(String ind){
			jobTypeRenderText.setText(ind);
		}
		

		/**
		 * 选项对话框选择职位
		 */
		public void initJobTitleView(){			

			OptionsView line = (OptionsView)mJobView.findViewById(R.id.client_job_title_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getJobTitle() == 0) ? 1 : mClient.getJobTitle();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setJobTitle(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
 		
		}
		
		
	    /**
	     * 家庭情况
	     */
		public void initFamily(){
			initMarriageView();
			initWeddingAnniversary();

		}
		
		/**
		 * 选项对话框选择婚姻状况
		 */
		
		public void initMarriageView(){			

			OptionsView line = (OptionsView)mFamilyView.findViewById(R.id.client_marriage_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getMarriage() == 0) ? 1 : mClient.getMarriage();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setMarriage(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		/**
		 * 结婚纪念日期选择
		 */
		public void initWeddingAnniversary(){
			LinearLayout dialogView = (LinearLayout)mInflater.inflate(R.layout.util_datepicker_dialog, null);  
		      
		    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
		           .setView(dialogView)  
		           .create();  
		    
		    LinearLayout birthday = (LinearLayout)mFamilyView.findViewById(R.id.client_weddingAnniversary_line);			
		    final TextView birthdayText = (TextView)mFamilyView.findViewById(R.id.client_weddingAnniversary);

		    
		    final DatePicker dp = (DatePicker)dialogView.findViewById(R.id.datePicker1);	
		    Button cancel = (Button)dialogView.findViewById(R.id.button1);	
		    Button ok = (Button)dialogView.findViewById(R.id.button2);	
		    
		    birthday.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.show();					
				}
		    });	
		    
		    ok.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
					String text = dp.getYear()+"年"+(dp.getMonth()+1)+"月"+dp.getDayOfMonth()+"日";					
					birthdayText.setText(text);
					Calendar c = Calendar.getInstance();					
					c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());					
					mClient.setWeddingAnniversary(c.getTime());
				}
		    });	
		    
		    cancel.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
				}
		    });	
		}
		
		
	    /**
	     * 收入情况
	     */
		public void initIncome(){
			initIncomeView();
			initIncomeTypeView();
			initFamilyIncomeView();
			initFamilyIncomeTypeView();
			initSupportTypeView();
			
		}
		
		
		/**
		 * 个人年收
		 */
		public void initIncomeView(){		
			 LinearLayout line = (LinearLayout)mIncomeView.findViewById(R.id.client_income_line);			
			 final EditText renderText = (EditText)mIncomeView.findViewById(R.id.client_income);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setIncome(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		
		
		/**
		 * 选项对话框选择个人年收分类
		 */		
		public void initIncomeTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_incomeType_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getIncomeType() == 0) ? 1 : mClient.getIncomeType();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setIncomeType(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
		/**
		 * 家庭年收
		 */
		public void initFamilyIncomeView(){		
			 LinearLayout line = (LinearLayout)mIncomeView.findViewById(R.id.client_familyIncome_line);			
			 final EditText renderText = (EditText)mIncomeView.findViewById(R.id.client_familyIncome);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setFamilyIncome(renderText.getText().toString());
					}
				}				 
			 });
		}
				
		
		/**
		 * 选项对话框选择  家庭年收分类
		 */		
		public void initFamilyIncomeTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_familyIncomeType_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getFamilyIncomeType() == 0) ? 1 : mClient.getFamilyIncomeType();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setFamilyIncomeType(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		/**
		 * 选项对话框选择  家庭支柱类型
		 */		
		public void initSupportTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_supportType_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getSupportType() == 0) ? 1 : mClient.getSupportType();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setSupportType(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
		/**
		 * 选项对话框选择  家庭有无缺口
		 */		
		public void initHasShortageView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_hasShortage_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getHasShortage() == 0) ? 1 : mClient.getHasShortage();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setHasShortage(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
	    /**
	     * 来源信息
	     */
		public void initSource(){
			initSourceView();
			initTouchableDegreeView();
			initIntroducerView();
			initRelationWithIntroducerView();
			initNearWithIntroducerView();
			initIntroducerOpinionView();
			initProblemToBeMindView();
		}
		
		/**
		 * 客户来源
		 */
		public void initSourceView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_source_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_source);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setSource(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		
		/**
		 * 选项对话框选择  可接触程度
		 */		
		public void initTouchableDegreeView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_touchableDegree_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getTouchableDegree() == 0) ? 1 : mClient.getTouchableDegree();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setTouchableDegree(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
		
		/**
		 * 介绍人
		 */
		public void initIntroducerView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_introducer_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_introducer);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setIntroducer(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		/**
		 * 选项对话框选择  与介绍人关系
		 */		
		public void initRelationWithIntroducerView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_relationWithIntroducer_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getRelationWithIntroducer() == 0) ? 1 : mClient.getRelationWithIntroducer();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setRelationWithIntroducer(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		/**
		 * 选项对话框选择 与介绍人亲密度
		 */		
		public void initNearWithIntroducerView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_nearWithIntroducer_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getNearWithIntroducer() == 0) ? 1 : mClient.getNearWithIntroducer();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setNearWithIntroducer(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
		/**
		 * 介绍人评价
		 */
		public void initIntroducerOpinionView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_introducerOpinion_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_introducerOpinion);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setIntroducerOpinion(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		/**
		 * 联系时注意问题
		 */
		public void initProblemToBeMindView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_problemToBeMind_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_problemToBeMind);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setProblemToBeMind(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		
		 /**
	     * 性格相关
	     */
		public void initTemper(){
			initPDPTypeView();
			initHobbyView();
		}
		
		
		/**
		 * 选项对话框选择 PDP类型
		 */		
		public void initPDPTypeView(){			

			OptionsView line = (OptionsView)mTemperView.findViewById(R.id.client_PDPType_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return (mClient.getPDPType() == 0) ? 1 : mClient.getPDPType();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setPDPType(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 		
		}
		
		
		
		/**
		 * 兴趣爱好
		 */
		public void initHobbyView(){		
			 LinearLayout line = (LinearLayout)mTemperView.findViewById(R.id.client_hobby_line);			
			 final EditText renderText = (EditText)mTemperView.findViewById(R.id.client_hobby);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setHobby(renderText.getText().toString());
					}
				}				 
			 });
		}
		
		
		 /**
	     * 服务
	     */
		public void initService(){
			initMindingServiceView();
		}
		
		/**
		 * 关注的服务
		 */
		public void initMindingServiceView(){		
			 LinearLayout line = (LinearLayout)mServiceView.findViewById(R.id.client_mindingService_line);			
			 final EditText renderText = (EditText)mServiceView.findViewById(R.id.client_mindingService);

			 renderText.clearFocus();  

			 renderText.setOnFocusChangeListener(new OnFocusChangeListener(){

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					// TODO Auto-generated method stub
					if(!hasFocus){
						mClient.setMindingService(renderText.getText().toString());
					}
				}				 
			 });
		}
		
	}
	
	

}
