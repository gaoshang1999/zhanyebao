package com.heverage.zhanyebao.client.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import android.util.Log;

import com.heverage.zhanyebao.client.PinYinHelper;
import com.heverage.zhanyebao.util.JSONHelper;

public class Client implements Comparable<Client>{
	
	/**
	 * 客户id
	 */
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 归属用户id	owner_user_id
	 */
	private int owner_user_id;
	
	public int getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(int owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	/** 
	 * 姓名
	 */
	private String client_name;
	
	private String pinyinName;
	
	private String firstCharHeader;
	
	private String allCharHeader;
	


	private boolean isRealPerson = true;

	private PinYinHelper mHelper = null;
	
	public Client() {
		super();
		mHelper = new PinYinHelper();  
	}
	
	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String name) {
		this.client_name = name;
		setPinyinName(mHelper.getPinYinChar(name).toUpperCase());
		setAllCharHeader(mHelper.getAllPinYinHeadChar(name).toUpperCase());
	}

	public String getPinyinName() {
		return pinyinName;
	}

	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}

	public boolean isRealPerson() {
		return isRealPerson;
	}

	public void setRealPerson(boolean isRealPerson) {
		this.isRealPerson = isRealPerson;
	}
	
	public String getFirstCharHeader() {
		return firstCharHeader;
	}

	public void setFirstCharHeader(String firstCharHeader) {
		this.firstCharHeader = firstCharHeader;
	}

	public String getAllCharHeader() {
		return allCharHeader;
	}

	public void setAllCharHeader(String allCharHeader) {
		this.allCharHeader = allCharHeader;
		setFirstCharHeader((allCharHeader.length() > 0) ? String.valueOf(allCharHeader.charAt(0)):"");
	}
	

    private String[] initData = {"一败如水", "胆小如鼠", "引狼入室", "风驰电掣", "刀山火海", "一贫如洗",  
            "料事如神", "视死如归", "对答如流", "挥金如土", "铁证如山", "度日如年", "心急如焚", "巧舌如簧",  
            "如雷贯耳", "如履薄冰", "如日中天", "势如破竹", "稳如泰山", "骨瘦如柴", "爱财如命", "暴跳如雷",  
            "门庭若市", "恩重如山", "从善如流", "观者如云", "浩如烟海", "弃暗投明", "取长补短", "厚今薄古",  
            "同甘共苦", "因小失大", "优胜劣败", "自生自灭", "评头论足", "远交近攻", "求同存异", "well",  
            "hello", "one", "goodtime", "running", "java", "android", "jsp",  
            "html", "struts", "Charles", "Mark", "Bill", "Vincent", "William",  
            "Joseph", "James", "Henry", "Gary", "Martin"};  
	
    /**
     * 
     * @return 按拼音顺序排序的Client 列表
     */
    public List<Client> buildClientsList(){
		List<Client> clientsList = new ArrayList<Client>();
		for(String c : initData){
			Client client = new Client();
			client.setClient_name(c);
			client.setRealPerson(true);
			clientsList.add(client);
		}
		Collections.sort(clientsList);		
		return clientsList;
	}


    /**
     * 
     * @return 按拼音字母分组的Client 列表，拼音首字母也是列表的元素，它的isRealPerson 为false.
     */
    public List<Client> buildGroupedClients(){
    	List<Client> clientsList = buildClientsList();    	
    	return buildGroupedClients(clientsList);
    }
    
    /**
     * 
     * @return 按拼音字母分组的Client 列表，拼音首字母也是列表的元素，它的isRealPerson 为false.
     */
    public static List<Client> buildGroupedClients(List<Client> clientsList){
    	List<Client> groupedClientsList = new ArrayList<Client>(clientsList.size());
    	
    	Collections.sort(clientsList);		
    	Client previousClient = null;    	
    	for(int i=0; i < clientsList.size(); i++){
    		Client client = clientsList.get(i);
    		if(previousClient== null || !previousClient.getFirstCharHeader().equals(client.getFirstCharHeader() )) {
    			Client letterClient = new Client();
    			letterClient.setClient_name(client.getFirstCharHeader());
    			letterClient.setRealPerson(false);
    			groupedClientsList.add(letterClient);
    		}
    		groupedClientsList.add(client);
    		previousClient = client;
    	}
    	return groupedClientsList;
    }
	

	/**
	 * 按拼音字母顺序比较大小
	 */
	@Override
	public int compareTo(Client another) {
		// TODO Auto-generated method stub		
		return this.pinyinName.compareTo(another.pinyinName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client_name == null) ? 0 : client_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (client_name == null) {
			if (other.client_name != null)
				return false;
		} else if (!client_name.equals(other.client_name))
			return false;
		return true;
	}  
	
	/**
	 * 性别
	 */
	private int sex;

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}


	/**
	 * 出生日期
	 */
	private Date birth_date;
	
	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	
	public String getFormatBirth_date() {
		if(null == birth_date){
			return "";
		}
		return new SimpleDateFormat("yyyy年MM月dd").format(getBirth_date());
	}

	public int getAge(){
		if(null == this.getBirth_date()){
			return -1;
		}
		
		Calendar now = Calendar.getInstance();
		
		Calendar birth = Calendar.getInstance();
		birth.setTime(this.getBirth_date());
		
		return now.get(Calendar.YEAR)- birth.get(Calendar.YEAR);
	}
	
	/**
	 * 身份证号
	 */
	private String id_number;
	
	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	
	private List<Email> emailList = new ArrayList<Email>();
	
	public List<Email> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<Email> emailList) {
		this.emailList = emailList;
	}

	private List<Phone> phoneList = new ArrayList<Phone>();

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	} 
	
	private List<Address> addressList = new ArrayList<Address>();

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}	
	
	/**
	 * 地区
	 */
	private String region;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

	/**
	 * 地区分类
	 */
	private int region_type;	
	
	public int getRegion_type() {
		return region_type;
	}

	public void setRegion_type(int cityType) {
		this.region_type = cityType;
	}

	/**
	 * 教育程度
	 */
	private int education_type;

	public int getEducation_type() {
		return education_type;
	}

	public void setEducation_type(int educaiton) {
		this.education_type = educaiton;
	}
	



	
	/**
	 * 工作单位
	 */
	private String company;
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	/**
	 * 行业类型
	 */
	private String trade_type;

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
	/**
	 * 企业性质
	 */
	private int company_nature;

	public int getCompany_nature() {
		return company_nature;
	}

	public void setCompany_nature(int company_nature) {
		this.company_nature = company_nature;
	}
	
	
	/**
	 * 职业类型
	 */
	private String career_type;

	public String getCareer_type() {
		return career_type;
	}

	public void setCareer_type(String career_type) {
		this.career_type = career_type;
	}
	
	/**
	 * 职位
	 */
	private int job_position;

	public int getJob_position() {
		return job_position;
	}

	public void setJob_position(int job_position) {
		this.job_position = job_position;
	}
	
	/**
	 * 职级
	 */
	private int job_level;

	public int getJob_level() {
		return job_level;
	}

	public void setJob_level(int job_level) {
		this.job_level = job_level;
	}
	
	/**
	 * 婚姻状况
	 */
	private int marital_status;

	public int getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(int marital_status) {
		this.marital_status = marital_status;
	}
	
	/**
	 * 结婚纪念日
	 */
	private Date wedding_date;

	public Date getWedding_date() {
		return wedding_date;
	}

	public void setWedding_date(Date wedding_date) {
		this.wedding_date = wedding_date;
	}
	
	public String getFormatWedding_date() {
		if(null == wedding_date){
			return "";
		}
		return new SimpleDateFormat("yyyy年MM月dd").format(getWedding_date());
	}
	
	/**
	 * 男孩数
	 */
	private int boy_num;
	
	
    public int getBoy_num() {
		return boy_num;
	}

	public void setBoy_num(int boy_num) {
		this.boy_num = boy_num;
	}
	
	/**
	 * 女孩数
	 */
	private int girl_num;
	
	
    public int getGirl_num() {
		return girl_num;
	}

	public void setGirl_num(int girl_num) {
		this.girl_num = girl_num;
	}
	
	/**
	 * 家庭成员
	 */
	private String family_info;
	
	public String getFamily_info() {
		return family_info;
	}

	public void setFamily_info(String family_info) {
		this.family_info = family_info;
	}

	/**
	 * 个人年收
	 */
	private double annual_income_personal;

	public double getAnnual_income_personal() {
		return annual_income_personal;
	}

	public void setAnnual_income_personal(double income) {
		this.annual_income_personal = income;
	}
	
	public void setAnnual_income_personal(String income) {
		this.annual_income_personal = Double.parseDouble(income);
	}
	
	/**
	 * 个人收入分类
	 */
	private int annual_income_personal_type;

	public int getAnnual_income_personal_type() {
		return annual_income_personal_type;
	}

	public void setAnnual_income_personal_type(int incomeType) {
		this.annual_income_personal_type = incomeType;
	}
	
	
	/**
	 * 家庭年收
	 */
	private double annual_income_family;

	public double getAnnual_income_family() {
		return annual_income_family;
	}

	public void setAnnual_income_family(double familyIncome) {
		this.annual_income_family = familyIncome;
	}
	
	public void setAnnual_income_family(String familyIncome) {
		this.annual_income_family = Double.parseDouble(familyIncome);
	}
	
	/**
	 * 家庭收入分类
	 */
	private int annual_income_family_type;

	public int getAnnual_income_family_type() {
		return annual_income_family_type;
	}

	public void setAnnual_income_family_type(int familyIncomeType) {
		this.annual_income_family_type = familyIncomeType;
	}
	
	/**
	 * 家庭收入特点
	 */
	private int family_income_feature;

	public int getFamily_income_feature() {
		return family_income_feature;
	}

	public void setFamily_income_feature(int supportType) {
		this.family_income_feature = supportType;
	}

	/**
	 * 财务状况
	 */
	private int family_financial_standing;
	
	public int getFamily_financial_standing() {
		return family_financial_standing;
	}

	public void setFamily_financial_standing(int hasShortage) {
		this.family_financial_standing = hasShortage;
	}
	
	/**
	 * 客户来源
	 */	
	private int source_type;

	public int getSource_type() {
		return source_type;
	}

	public void setSource_type(int source_type) {
		this.source_type = source_type;
	}
	/**
	 * 介绍人
	 */
	private String introducer_name;

	public String getIntroducer_name() {
		return introducer_name;
	}

	public void setIntroducer_name(String introducer) {
		this.introducer_name = introducer;
	}
	

	
	
	/**
	 * 与介绍人关系
	 */
	private int introducer_relationship;
		
	public int getIntroducer_relationship() {
		return introducer_relationship;
	}

	public void setIntroducer_relationship(int relationWithIntroducer) {
		this.introducer_relationship = relationWithIntroducer;
	}


	/**
	 * 与介绍人亲密度
	 */
	private int introducer_closeness;

	public int getIntroducer_closeness() {
		return introducer_closeness;
	}

	public void setIntroducer_closeness(int nearWithIntroducer) {
		this.introducer_closeness = nearWithIntroducer;
	}
	
	/**
	 * 介绍人评价
	 */
	private String introducer_evaluation;

	public String getIntroducer_evaluation() {
		return introducer_evaluation;
	}

	public void setIntroducer_evaluation(String introducerOpinion) {
		this.introducer_evaluation = introducerOpinion;
	}
	
	
	/**
	 * 可接触程度
	 */
	private int contact_type;

	public int getContact_type() {
		return contact_type;
	}

	public void setContact_type(int touchableDegree) {
		this.contact_type = touchableDegree;
	}
	
	
	/**
	 * 联系时注意问题
	 */
	private String contact_attention;

	public String getContact_attention() {
		return contact_attention;
	}

	public void setContact_attention(String problemToBeMind) {
		this.contact_attention = problemToBeMind;
	}
	
	/**
	 * 血型
	 */
	private int blood_group;
		
	public int getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(int blood_group) {
		this.blood_group = blood_group;
	}

	/**
	 * PDP类型
	 */
	private int pdp_type;

	public int getPdp_type() {
		return pdp_type;
	}

	public void setPdp_type(int pDPType) {
		pdp_type = pDPType;
	}
	

	/**
	 * 	兴趣爱好
	 */
	private String hobbies;

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobby) {
		this.hobbies = hobby;
	}
	
	/**
	 * 关注的服务
	 */
	private String interesting_service;

	public String getInteresting_service() {
		return interesting_service;
	}

	public void setInteresting_service(String interesting_service) {
		this.interesting_service = interesting_service;
	}
	
	public String toJSON() {
//		JSONObject json = new JSONObject();
//		try {
//			json.put("owner_user_id", owner_user_id);
//			json.put("client_name", client_name);			
//			json.put("sex", sex);
//			json.put("id_number", id_number);
//			json.put("birth_date", new SimpleDateFormat("yyyy-MM-dd").format(birth_date));
//			
//		} catch (JSONException ex) {
//			ex.printStackTrace();
//		}
		String j = JSONHelper.toJSON(this);
		Log.v("bean->json", j);
		return j;
	}

	
	public static Client setJSON(String json){
		try{
			Log.v("json->bean", JSONHelper.parseObject(json, Client.class).toJSON());
			return JSONHelper.parseObject(json, Client.class); 
		} catch (JSONException e) {  
	        e.printStackTrace();  
	    } 
		return null;
	}
}
