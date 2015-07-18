package com.heverage.zhanyebao.client.add;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heverage.zhanyebao.R;
import com.heverage.zhanyebao.client.db.ClientSQLiteHelper;
import com.heverage.zhanyebao.client.model.Address;
import com.heverage.zhanyebao.client.model.Client;
import com.heverage.zhanyebao.client.model.Email;
import com.heverage.zhanyebao.client.model.Phone;
import com.heverage.zhanyebao.util.OptionActivity;
import com.heverage.zhanyebao.util.OptionCallbackListener;
import com.heverage.zhanyebao.view.OptionsView;
import com.heverage.zhanyebao.view.ToggleDownView;

public class NewClientFragment extends Fragment implements OnTouchListener{

	/**
	 * A placeholder fragment containing a simple view.
	 */
		protected Context mContext = null;

		protected Client mClient;


		private Button backBtn;
		private Button cacelBtn;
		private Button saveBtn;

		protected LayoutInflater mInflater;
		protected ToggleDownView mBasicView;
		protected ToggleDownView mJobView;
		protected ToggleDownView mFamilyView;
		protected ToggleDownView mIncomeView;
		protected ToggleDownView mSourceView;
		protected ToggleDownView mTemperView;
		protected ToggleDownView mServiceView;
		
	    protected ClientSQLiteHelper mSQLiteHelper;
	    
	    protected int mId;
	    protected boolean mNewable;
		
		public NewClientFragment(int mId, boolean mNewable) {
			this.mId = mId;
			this.mNewable = mNewable;
		}		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.client_new_layout_main,
					container, false);
			mContext = this.getActivity();
	        this.mSQLiteHelper = new ClientSQLiteHelper(mContext);
	        
	        if(mNewable){
				mClient = new Client();
	        }else{
	        	mClient = mSQLiteHelper.queryClient(mId);
	        }

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
					if(validate()){
						if(mNewable){
							mSQLiteHelper.saveClient(mClient);
						}else{
							mSQLiteHelper.updateClient(mClient);
						}
						finish();
					}
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
		
		public void finish(){
			getActivity().finish();
		}
		
	    @Override  
	    public boolean onTouch(View v, MotionEvent event)  
	    {  
	        // TODO Auto-generated method stub  	
			//隐藏键盘	
	       InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
	       imm.hideSoftInputFromWindow(clientNameText.getWindowToken(), 0);    
	       imm.hideSoftInputFromWindow(idNumberText.getWindowToken(), 0);    
	       
	       return  true;  
	    }  
		
		public boolean validate(){
			if(clientNameText.getText().toString().isEmpty()){
				clientNameText.setError("请输入客户姓名！");
	    		return false;
	    	}
			mClient.setClient_name(clientNameText.getText().toString());
			
			if(!sexline.isClicked()){
				return false;
			}
			
			return true;
		}
	    
	    
	    /**
	     * 基本信息
	     */
		public void initBasic(){
			initClientNameView();

			initSexView();
			initIdNumberView();
			initBirthDateView();
			
			initEmailView();
			initPhoneView();
			initAddressView();
			
			initRegionView();
			initRegionTypeView();
			initEducationView();
			
		}


		private EditText clientNameText;
		
		/**
		 * 客户姓名
		 */
		public void initClientNameView(){		
			 LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_name_line);			
			 clientNameText = (EditText)mBasicView.findViewById(R.id.client_name);

	 
			 clientNameText.addTextChangedListener(new TextWatcher(){
				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub					
				}
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					mClient.setClient_name(s.toString());
				}
				 
			 });
			 
			 if(!mNewable && mClient.getClient_name() != null){
				 clientNameText.setText(mClient.getClient_name());
			 }
		}
		
		private OptionsView sexline;
		
		/**
		 * 选项对话框选择性别
		 */
		public void initSexView(){	
			sexline = (OptionsView)mBasicView.findViewById(R.id.client_sex_line);			

			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getSex();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setSex(key);

				}						
			};			
			sexline.setOptionCallbackListener(oc);	
			//隐藏键盘
			sexline.setOnTouchListener(this);
			
			if(!mNewable && mClient.getSex() != 0){
				sexline.setRenderText(mClient.getSex());
			 }			
		}
		
		private EditText idNumberText;
		/**
		 * 身份证号
		 */
		public void initIdNumberView(){		
			 LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_id_number_line);			
			 final EditText renderText = (EditText)mBasicView.findViewById(R.id.client_id_number);
			 idNumberText = renderText;
			 
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setId_number(s.toString());
					}
					 
			 });
			
			 if(!mNewable && mClient.getId_number() != null){
				renderText.setText(mClient.getId_number());
			 }	
		}
		
		/**
		 * 出生日期
		 */
		public void initBirthDateView(){
			LinearLayout dialogView = (LinearLayout)mInflater.inflate(R.layout.util_datepicker_dialog, null);  
		      
		    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
		           .setView(dialogView)  
		           .create();  
		    
		    LinearLayout birth_date = (LinearLayout)mBasicView.findViewById(R.id.client_birth_date_line);			
		    final TextView birth_dateText = (TextView)mBasicView.findViewById(R.id.client_birth_date);
		    final TextView ageText = (TextView)mBasicView.findViewById(R.id.client_age);
		    
		    final DatePicker dp = (DatePicker)dialogView.findViewById(R.id.datePicker1);	
		    Button cancel = (Button)dialogView.findViewById(R.id.button1);	
		    Button ok = (Button)dialogView.findViewById(R.id.button2);	
		    
		    birth_date.setOnClickListener(new OnClickListener(){
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
					birth_dateText.setText(text);
					Calendar c = Calendar.getInstance();					
					c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());					
					mClient.setBirth_date(c.getTime());
					ageText.setText(mClient.getAge()+"");
				}
		    });	
		    
		    cancel.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
				}
		    });	
		    
			 if(!mNewable && mClient.getBirth_date() != null){
				 birth_dateText.setText(mClient.getFormatBirth_date());
				 String age = (mClient.getAge() == -1)?"":mClient.getAge()+"";
				 ageText.setText(age);
			 }	
		}
		
		/**
		 * 邮箱
		 */
		 public void initEmailView(){
			 	Button buttonAddPhone =  (Button) mBasicView.findViewById(R.id.buttonAddEmail);			
			    
			    buttonAddPhone.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {					
						Intent intent = new Intent(mContext, NewEmailActivity.class);
						startActivityForResult(intent, EMAIL_requestCode);					
					}        	
		        });	
			 if(!mNewable){
				 for(Email email: mClient.getEmailList()){
					 afterEmailCreate(email);
				 }
			 }	
		 }
		
		private final int EMAIL_requestCode = 100;
		
		public void afterEmailCreate(final Email email){
			final LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_email_line);	

			
			final LinearLayout emailView = (LinearLayout)mInflater.inflate(R.layout.client_new_basic_email_line, null);
			final TextView type = (TextView)emailView.findViewById(R.id.client_email_type);
		    Resources res = mContext.getResources();
		    final String[] constants =res.getStringArray(R.array.email_type);
			type.setText(constants[email.getEmail_type_value()]);
			
			final TextView renderText = (TextView)emailView.findViewById(R.id.client_email);
			renderText.setText(email.getEmail());
			
			Button buttonDeleteEmail = (Button) emailView.findViewById(R.id.buttonDeleteEmail);			
			buttonDeleteEmail.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					mClient.getEmailList().remove(email);
					line.removeView(emailView);
				}        	
	        });	
			line.addView(emailView);
		}
		
		
		/**
		 * 手机号码
		 */
		 public void initPhoneView(){
			 	Button buttonAddPhone =  (Button) mBasicView.findViewById(R.id.buttonAddPhone);			
			    
			    buttonAddPhone.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {					
						Intent intent = new Intent(mContext, NewPhoneActivity.class);
						startActivityForResult(intent, PHONE_requestCode);					
					}        	
		        });	
			    
			 if(!mNewable){
				 for(Phone phone: mClient.getPhoneList()){
					 afterPhoneCreate(phone);
				 }
			 }	
		 }
		
		private final int PHONE_requestCode = 101;
		
		public void afterPhoneCreate(final Phone phone){
			final LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_phone_line);	

			
			final LinearLayout phoneView = (LinearLayout)mInflater.inflate(R.layout.client_new_basic_phone_line, null);  
			final TextView type = (TextView)phoneView.findViewById(R.id.client_phone_type);
		    Resources res = mContext.getResources();
		    final String[] constants =res.getStringArray(R.array.phone_type);
			type.setText(constants[phone.getPhone_type_value()]);
			
			final TextView renderText = (TextView)phoneView.findViewById(R.id.client_phone);
			renderText.setText(phone.getPhone_number());
			
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

			 	if(!mNewable){
					 for(Address add: mClient.getAddressList()){
						 afterAddressCreate(add);
					 }
				 }	
		 }
		
		private final int ADDRESS_requestCode = 102;
		
		public void afterAddressCreate(final Address add){
			final LinearLayout line = (LinearLayout)mBasicView.findViewById(R.id.client_address_line);
			
			final LinearLayout phoneView = (LinearLayout)mInflater.inflate(R.layout.client_new_basic_address_line, null);  
			final TextView renderText = (TextView)phoneView.findViewById(R.id.client_address);
			renderText.setText(add.getRegion()+" "+add.getDetail_address());
			
		    Resources res = mContext.getResources();
		    final String[] constants =res.getStringArray(R.array.address_type);
			final TextView renderText2 = (TextView)phoneView.findViewById(R.id.client_address_type);
			renderText2.setText(constants[add.getAddress_type_value()]);
			
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
		 * 选项对话框选择 地区分类
		 */
		public void initRegionTypeView(){

			OptionsView line = (OptionsView)mBasicView.findViewById(R.id.client_region_type_line);	
	
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return  mClient.getRegion_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setRegion_type(key);
				}						
			};
			
			line.setOptionCallbackListener(oc);
			
		 	if(!mNewable && mClient.getRegion_type() != 0){
		 		line.setRenderText(mClient.getRegion_type());
			 }				
		}
		
		/**
		 * 选项对话框选择 教育程度
		 */
		public void initEducationView(){

			OptionsView line = (OptionsView)mBasicView.findViewById(R.id.client_educaiton_type_line);				
					
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getEducation_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setEducation_type(key);
				}						
			};
			
			line.setOptionCallbackListener(oc);
			
		 	if(!mNewable && mClient.getEducation_type() != 0){
		 		line.setRenderText(mClient.getEducation_type());
			 }	
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
			
			 if(!mNewable  && mClient.getRegion() != null){
				renderText.setText(mClient.getRegion());
			 }	
		}
		
		private final int REGION_requestCode = 103;
			
		 public void onActivityResult(int requestCode, int resultCode,
	             Intent data) {
			 if (requestCode == EMAIL_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 Email email = data.getParcelableExtra("email");
	            	 mClient.getEmailList().add(email);
	            	 afterEmailCreate(email);
	             }
	         }else if (requestCode == PHONE_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 Phone phone = data.getParcelableExtra("phone");
	            	 mClient.getPhoneList().add(phone);
	            	 afterPhoneCreate(phone);
	             }
	         }else if (requestCode == ADDRESS_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 Address add = data.getParcelableExtra("address");
	            	 mClient.getAddressList().add(add);
	            	 afterAddressCreate(add);
	             }
	         }else if (requestCode == REGION_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String text = data.getStringExtra("city");
	            	 this.regionRenderText.setText(text);
	            	 mClient.setRegion(text);
	             }
	         } else if (requestCode == INDUSTRY_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String ind = data.getStringExtra("clicked");
	            	 mClient.setTrade_type(ind);
	            	 afterIndustrySelected(ind);
	             }
	         }else if (requestCode == JOBTYPE_requestCode) {
	             if (resultCode == Activity.RESULT_OK) {
	                 // A contact was picked.  Here we will just display it
	                 // to the user.
	            	 String ind = data.getStringExtra("clicked");
	            	 mClient.setCareer_type(ind);
	            	 afterJobTypeSelected(ind);
	             }
	         }
			
	     }
	
		 
		 
		
		
		
	
	    /**
	     * 工作情况
	     */
		public void initJob(){
			initCompanyView();
			inittTradeTypeView();
			initCompanyNatureView();
			initCareerTypeView();
			initjobPositionView();
			initjobLevelView();
		}
		
		/**
		 * 工作单位
		 */
		public void initCompanyView(){		
			 LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_company_line);			
			 final EditText renderText = (EditText)mJobView.findViewById(R.id.client_company);			 
				 
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setCompany(s.toString());
					}
					 
			 });
			
			 if(!mNewable && mClient.getCompany() != null){
				renderText.setText(mClient.getCompany());
			 }	
		}
		
		/**
		 * 行业类型
		 */
		private int INDUSTRY_requestCode = 200;
		private TextView industryRenderText;
		public void inittTradeTypeView(){			

		    LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_job_trade_type_line);			
		    final TextView renderText = (TextView)mJobView.findViewById(R.id.client_job_trade_type);
		    industryRenderText = renderText;
		    
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					
					Intent intent = new Intent(mContext, OptionActivity.class);
					intent.putExtra("title", "选择行业类型");
					intent.putExtra("resourceId", R.array.trade_type);
					startActivityForResult(intent, INDUSTRY_requestCode);	
				}        	
	        });					
			
			if(!mNewable && mClient.getTrade_type() != null){
				renderText.setText(mClient.getTrade_type());
			 }
		}
		
		public void afterIndustrySelected(String ind){
			industryRenderText.setText(ind);
		}
		
		/**
		 * 选项对话框选择企业性质
		 */
		public void initCompanyNatureView(){			

			OptionsView line = (OptionsView)mJobView.findViewById(R.id.client_job_company_nature);				
 
		    OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getCompany_nature();
				}
	
				@Override
				public void callback(int key, String value) {
				// TODO Auto-generated method stub
							mClient.setCompany_nature(key);
 
						}						
					};
			line.setOptionCallbackListener(oc);
			
			if(!mNewable && mClient.getCompany_nature() != 0){
				line.setRenderText(mClient.getCompany_nature());
			}	
		}
		
		/**
		 * 职业类型
		 */
		private int JOBTYPE_requestCode = 201;
		private TextView careerTypeRenderText;		
		public void initCareerTypeView(){			
			
		    LinearLayout line = (LinearLayout)mJobView.findViewById(R.id.client_career_type_line);			
		    final TextView renderText = (TextView)mJobView.findViewById(R.id.client_career_type);
		    careerTypeRenderText = renderText;
		    
			line.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {					
					
					Intent intent = new Intent(mContext, OptionActivity.class);
					intent.putExtra("title", "选择职业类型");
					intent.putExtra("resourceId", R.array.career_type);
					startActivityForResult(intent, JOBTYPE_requestCode);	
				}        	
	        });					
			
			if(!mNewable && mClient.getCareer_type() != null){
				renderText.setText(mClient.getCareer_type());
			 }
		}
		
		public void afterJobTypeSelected(String ind){
			careerTypeRenderText.setText(ind);
		}
		

		/**
		 * 选项对话框选择 职位
		 */
		public void initjobPositionView(){			

			OptionsView line = (OptionsView)mJobView.findViewById(R.id.client_job_position_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getJob_position();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setJob_position(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
			
			if(!mNewable && mClient.getJob_position() != 0){
				line.setRenderText(mClient.getJob_position());
			}
		}
		
		/**
		 * 选项对话框选择 职级
		 */
		public void initjobLevelView(){			

			OptionsView line = (OptionsView)mJobView.findViewById(R.id.client_job_level_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getJob_level();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setJob_level(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
			
			if(!mNewable && mClient.getJob_level() != 0){
				line.setRenderText(mClient.getJob_level());
			}
		}
		
		
	    /**
	     * 家庭情况
	     */
		public void initFamily(){
			initmaritalStatusView();
			initWeddingDateView();
			
			initChildrenNumView();			
			initBoyNumView();
			initGirlNumView();

			initFamilyInfoView();
		}
		
		/**
		 * 选项对话框选择婚姻状况
		 */
		
		public void initmaritalStatusView(){			

			OptionsView line = (OptionsView)mFamilyView.findViewById(R.id.client_marital_status_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getMarital_status();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setMarital_status(key);
				}						
			};
			line.setOptionCallbackListener(oc);	
			
			if(!mNewable && mClient.getMarital_status() != 0){
				line.setRenderText(mClient.getMarital_status());
			}
		}
		
		/**
		 * 结婚纪念日期选择
		 */
		public void initWeddingDateView(){
			LinearLayout dialogView = (LinearLayout)mInflater.inflate(R.layout.util_datepicker_dialog, null);  
		      
		    final AlertDialog dialog = new AlertDialog.Builder(mContext) 	
		           .setView(dialogView)  
		           .create();  
		    
		    LinearLayout line = (LinearLayout)mFamilyView.findViewById(R.id.client_wedding_date_line);			
		    final TextView weddingDateText = (TextView)mFamilyView.findViewById(R.id.client_wedding_date);

		    
		    final DatePicker dp = (DatePicker)dialogView.findViewById(R.id.datePicker1);	
		    Button cancel = (Button)dialogView.findViewById(R.id.button1);	
		    Button ok = (Button)dialogView.findViewById(R.id.button2);	
		    
		    line.setOnClickListener(new OnClickListener(){
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
					weddingDateText.setText(text);
					Calendar c = Calendar.getInstance();					
					c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());					
					mClient.setWedding_date(c.getTime());
				}
		    });	
		    
		    cancel.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {	
					dialog.dismiss();
				}
		    });	
		    
			 if(!mNewable && mClient.getFormatWedding_date() != null){
				 weddingDateText.setText(mClient.getFormatWedding_date());
			 }
		}
		
		/**
		 * 男孩数
		 */
		public void initBoyNumView(){		
			 LinearLayout line = (LinearLayout)mFamilyView.findViewById(R.id.client_boy_num_line);			
			 final EditText renderText = (EditText)mFamilyView.findViewById(R.id.client_boy_num);			 
				 
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(s.toString().matches("^(\\d)+$")){
							mClient.setBoy_num(Integer.parseInt(s.toString()));
							childrenNumrenderText.setText((mClient.getBoy_num()+mClient.getGirl_num())+"");
						}
					}
					 
			 });
			
			 if(!mNewable && mClient.getBoy_num() != 0){
				renderText.setText(mClient.getBoy_num()+"");
			 }	
		}
		
		/**
		 * 女孩数
		 */
		public void initGirlNumView(){		
			 LinearLayout line = (LinearLayout)mFamilyView.findViewById(R.id.client_girl_num_line);			
			 final EditText renderText = (EditText)mFamilyView.findViewById(R.id.client_girl_num);			 
				 
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(s.toString().matches("^(\\d)+$")){
							mClient.setGirl_num(Integer.parseInt(s.toString()));
							childrenNumrenderText.setText((mClient.getBoy_num()+mClient.getGirl_num())+"");
						}
					}
					 
			 });
			
			 if(!mNewable && mClient.getGirl_num() != 0){
				renderText.setText(mClient.getGirl_num()+"");
			 }	
		}
		
		private TextView childrenNumrenderText;
		/**
		 * 子女数
		 */
		public void initChildrenNumView(){		
			 LinearLayout line = (LinearLayout)mFamilyView.findViewById(R.id.client_children_num_line);			
			 final TextView renderText = (TextView)mFamilyView.findViewById(R.id.client_children_num);			 
			 childrenNumrenderText = renderText;	 
			 
			
			 if(!mNewable){
				renderText.setText((mClient.getBoy_num()+mClient.getGirl_num())+"");
			 }	
		}
		
		/**
		 * 家庭成员
		 */
		public void initFamilyInfoView(){		
			 LinearLayout line = (LinearLayout)mFamilyView.findViewById(R.id.client_family_info_line);			
			 final EditText renderText = (EditText)mFamilyView.findViewById(R.id.client_family_info);			 
			 
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setFamily_info(s.toString());
					}					 
			 });
			
			 if(!mNewable && mClient.getFamily_info() != null){
				renderText.setText(mClient.getFamily_info());
			 }	
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
			initHasShortageView();
		}
		
		
		/**
		 * 个人年收
		 */
		public void initIncomeView(){		
			 LinearLayout line = (LinearLayout)mIncomeView.findViewById(R.id.client_annual_income_personal_line);			
			 final EditText renderText = (EditText)mIncomeView.findViewById(R.id.client_annual_income_personal);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(s.length() > 0 ){
							mClient.setAnnual_income_personal(renderText.getText().toString());
						}
					}					 
			 });
			 
			 if(!mNewable && mClient.getAnnual_income_personal() != 0){
				renderText.setText(mClient.getAnnual_income_personal()+"");
			 }			 
		}
		
		
		
		/**
		 * 选项对话框选择个人年收分类
		 */		
		public void initIncomeTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_annual_income_personal_type_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getAnnual_income_personal_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setAnnual_income_personal_type(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 
			
			if(!mNewable && mClient.getAnnual_income_personal_type() != 0){
				line.setRenderText(mClient.getAnnual_income_personal_type());
			}
		}
		
		
		/**
		 * 家庭年收
		 */
		public void initFamilyIncomeView(){		
			 LinearLayout line = (LinearLayout)mIncomeView.findViewById(R.id.client_annual_income_family_line);			
			 final EditText renderText = (EditText)mIncomeView.findViewById(R.id.client_annual_income_family);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if(s.length() > 0 ){
							mClient.setAnnual_income_family(renderText.getText().toString());
						}
					}					 
			 });
			 
			 if(!mNewable && mClient.getAnnual_income_family() != 0){
				renderText.setText(mClient.getAnnual_income_family()+"");
			 }

		}
				
		
		/**
		 * 选项对话框选择  家庭年收分类
		 */		
		public void initFamilyIncomeTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_annual_income_family_type_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getAnnual_income_family_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setAnnual_income_family_type(key);
				}						
			};
			line.setOptionCallbackListener(oc);	
			
			if(!mNewable && mClient.getAnnual_income_family_type() != 0){
				line.setRenderText(mClient.getAnnual_income_family_type());
			}
		}
		
		/**
		 * 选项对话框选择  家庭收入特点
		 */		
		public void initSupportTypeView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_family_income_feature_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getFamily_income_feature();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setFamily_income_feature(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
			
			if(!mNewable && mClient.getFamily_income_feature() != 0){
				line.setRenderText(mClient.getFamily_income_feature());
			}
		}
		
		
		/**
		 * 选项对话框选择  财务状况
		 */		
		public void initHasShortageView(){			

			OptionsView line = (OptionsView)mIncomeView.findViewById(R.id.client_family_financial_standing_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getFamily_financial_standing();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setFamily_financial_standing(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
			
			if(!mNewable && mClient.getFamily_financial_standing() != 0){
				line.setRenderText(mClient.getFamily_financial_standing());
			}
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
			
			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_source_type_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getSource_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setSource_type(key);
				}						
			};
			line.setOptionCallbackListener(oc);		
			
			if(!mNewable && mClient.getSource_type() != 0){
				line.setRenderText(mClient.getSource_type());
			}
			 
		}
		
		/**
		 * 介绍人
		 */
		public void initIntroducerView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_introducer_name_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_introducer_name);

			
			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setIntroducer_name(renderText.getText().toString());
					}					 
			 });
			 
			 if(!mNewable && mClient.getIntroducer_name() != null){
				renderText.setText(mClient.getIntroducer_name());	
			 }
			 
		}
		
		/**
		 * 选项对话框选择  与介绍人关系
		 */		
		public void initRelationWithIntroducerView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_introducer_relationship_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getIntroducer_relationship();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setIntroducer_relationship(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 	
			
			if(!mNewable && mClient.getIntroducer_relationship() != 0){
				line.setRenderText(mClient.getSource_type());
			}
		}
		
		/**
		 * 选项对话框选择 与介绍人亲密度
		 */		
		public void initNearWithIntroducerView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_introducer_closeness_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getIntroducer_closeness();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setIntroducer_closeness(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 	
			
			if(!mNewable && mClient.getIntroducer_closeness() != 0){
				line.setRenderText(mClient.getIntroducer_closeness());
			}
		}
		
		
		/**
		 * 介绍人评价
		 */
		public void initIntroducerOpinionView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_introducer_evaluation_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_introducer_evaluation);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setIntroducer_evaluation(renderText.getText().toString());
					}					 
			 });
			 
			 if(!mNewable && mClient.getIntroducer_evaluation() != null){
				renderText.setText(mClient.getIntroducer_evaluation() );	
			 }		 
			
		}
				
		/**
		 * 选项对话框选择  可接触程度
		 */		
		public void initTouchableDegreeView(){			

			OptionsView line = (OptionsView)mSourceView.findViewById(R.id.client_contact_type_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getContact_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setContact_type(key);
				}						
			};
			line.setOptionCallbackListener(oc);		 
			
			if(!mNewable && mClient.getContact_type() != 0){
				line.setRenderText(mClient.getContact_type());
			}
		}
		
		
		/**
		 * 联系时注意问题
		 */
		public void initProblemToBeMindView(){		
			 LinearLayout line = (LinearLayout)mSourceView.findViewById(R.id.client_contact_attention_line);			
			 final EditText renderText = (EditText)mSourceView.findViewById(R.id.client_contact_attention);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setContact_attention(renderText.getText().toString());
					}					 
			 });
			 
			 if(!mNewable && mClient.getContact_attention() != null){
				renderText.setText(mClient.getContact_attention() );	
			 }		 

		}
		
		
		 /**
	     * 性格相关
	     */
		public void initTemper(){
			initBloodGroupView();
			initPDPTypeView();
			initHobbyView();
		}
		
		/**
		 * 选项对话框选择 PDP类型
		 */		
		public void initBloodGroupView(){			

			OptionsView line = (OptionsView)mTemperView.findViewById(R.id.client_blood_group_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getBlood_group();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setBlood_group(key);
				}						
			};
			line.setOptionCallbackListener(oc);	
			
			if(!mNewable && mClient.getBlood_group() != 0){
				line.setRenderText(mClient.getBlood_group());
			}
		}
		
		/**
		 * 选项对话框选择 PDP类型
		 */		
		public void initPDPTypeView(){			

			OptionsView line = (OptionsView)mTemperView.findViewById(R.id.client_pdp_type_line);		
			OptionCallbackListener oc = new OptionCallbackListener(){
				@Override
				public int getKey() {
					// TODO Auto-generated method stub
					return mClient.getPdp_type();
				}

				@Override
				public void callback(int key, String value) {
					// TODO Auto-generated method stub
					mClient.setPdp_type(key);
				}						
			};
			line.setOptionCallbackListener(oc);	
			
			if(!mNewable && mClient.getPdp_type() != 0){
				line.setRenderText(mClient.getPdp_type());
			}
		}
		
		
		
		/**
		 * 兴趣爱好
		 */
		public void initHobbyView(){		
			 LinearLayout line = (LinearLayout)mTemperView.findViewById(R.id.client_hobbies_line);			
			 final EditText renderText = (EditText)mTemperView.findViewById(R.id.client_hobbies);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setHobbies(renderText.getText().toString());
					}					 
			 });
			 
			 if(!mNewable && mClient.getHobbies() != null){
				renderText.setText(mClient.getHobbies() );	
			}		
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
			 LinearLayout line = (LinearLayout)mServiceView.findViewById(R.id.client_interesting_service_line);			
			 final EditText renderText = (EditText)mServiceView.findViewById(R.id.client_interesting_service);

			 renderText.addTextChangedListener(new TextWatcher(){
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub					
					}
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						mClient.setInteresting_service(renderText.getText().toString());
					}					 
			 });
			 
			 if(!mNewable && mClient.getInteresting_service() != null){
				renderText.setText(mClient.getInteresting_service() );	
			}	

		}
		
	}
