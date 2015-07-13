package com.heverage.zhanyebao.client.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.heverage.zhanyebao.client.PinYinHelper;

public class Client implements Comparable<Client>{

	/** 
	 * 姓名
	 */
	private String name;
	
	private String pinyinName;
	
	private String firstCharHeader;
	
	private String allCharHeader;
	


	private boolean isRealPerson;

	private PinYinHelper mHelper = null;
	
	public Client() {
		super();
		mHelper = new PinYinHelper();  
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			client.setName(c);
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
    public List<Client> buildGroupedClients(List<Client> clientsList){
    	List<Client> groupedClientsList = new ArrayList<Client>(clientsList.size());
    	
    	Client previousClient = null;    	
    	for(int i=0; i < clientsList.size(); i++){
    		Client client = clientsList.get(i);
    		if(previousClient== null || !previousClient.getFirstCharHeader().equals(client.getFirstCharHeader() )) {
    			Client letterClient = new Client();
    			letterClient.setName(client.getFirstCharHeader());
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}  
	
	/**
	 * 性别
	 */
	private int gender;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	/**
	 * 出生日期
	 */
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}	
	
	/**
	 * 城市分类
	 */
	private int cityType;	
	
	public int getCityType() {
		return cityType;
	}

	public void setCityType(int cityType) {
		this.cityType = cityType;
	}

	/**
	 * 教育程度
	 */
	private int educaiton;

	public int getEducaiton() {
		return educaiton;
	}

	public void setEducaiton(int educaiton) {
		this.educaiton = educaiton;
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
	 * 工作单位
	 */
	private String jobUnit;
	
	public String getJobUnit() {
		return jobUnit;
	}

	public void setJobUnit(String jobUnit) {
		this.jobUnit = jobUnit;
	}


	/**
	 * 行业类型
	 */
	private String industry;

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	/**
	 * 企业性质
	 */
	private int jobNature;

	public int getJobNature() {
		return jobNature;
	}

	public void setJobNature(int jobNature) {
		this.jobNature = jobNature;
	}
	
	
	/**
	 * 职业类型
	 */
	private String jobType;

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	/**
	 * 职位
	 */
	private int jobTitle;

	public int getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(int jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * 婚姻状况
	 */
	private int marriage;

	public int getMarriage() {
		return marriage;
	}

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}
	
	/**
	 * 结婚纪念日
	 */
	private Date weddingAnniversary;

	public Date getWeddingAnniversary() {
		return weddingAnniversary;
	}

	public void setWeddingAnniversary(Date weddingAnniversary) {
		this.weddingAnniversary = weddingAnniversary;
	}
	
	
	/**
	 * 个人年收
	 */
	private BigDecimal income;

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	
	public void setIncome(String income) {
		this.income = new BigDecimal(income);
	}
	
	/**
	 * 个人收入分类
	 */
	private int incomeType;

	public int getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
	}
	
	
	/**
	 * 家庭年收
	 */
	private BigDecimal familyIncome;

	public BigDecimal getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
	}
	
	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = new BigDecimal(familyIncome);
	}
	
	/**
	 * 家庭收入分类
	 */
	private int familyIncomeType;

	public int getFamilyIncomeType() {
		return familyIncomeType;
	}

	public void setFamilyIncomeType(int familyIncomeType) {
		this.familyIncomeType = familyIncomeType;
	}
	
	/**
	 * 支柱类型
	 */
	private int supportType;

	public int getSupportType() {
		return supportType;
	}

	public void setSupportType(int supportType) {
		this.supportType = supportType;
	}

	/**
	 * 有无缺口
	 */
	private int hasShortage;
	
	public int getHasShortage() {
		return hasShortage;
	}

	public void setHasShortage(int hasShortage) {
		this.hasShortage = hasShortage;
	}
	
	/**
	 * 客户来源
	 */	
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * 可接触程度
	 */
	private int touchableDegree;

	public int getTouchableDegree() {
		return touchableDegree;
	}

	public void setTouchableDegree(int touchableDegree) {
		this.touchableDegree = touchableDegree;
	}
	
	/**
	 * 介绍人
	 */
	private String introducer;

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	
	/**
	 * 与介绍人关系
	 */
	private int relationWithIntroducer;
		
	public int getRelationWithIntroducer() {
		return relationWithIntroducer;
	}

	public void setRelationWithIntroducer(int relationWithIntroducer) {
		this.relationWithIntroducer = relationWithIntroducer;
	}


	/**
	 * 与介绍人亲密度
	 */
	private int nearWithIntroducer;

	public int getNearWithIntroducer() {
		return nearWithIntroducer;
	}

	public void setNearWithIntroducer(int nearWithIntroducer) {
		this.nearWithIntroducer = nearWithIntroducer;
	}
	
	/**
	 * 介绍人评价
	 */
	private String introducerOpinion;

	public String getIntroducerOpinion() {
		return introducerOpinion;
	}

	public void setIntroducerOpinion(String introducerOpinion) {
		this.introducerOpinion = introducerOpinion;
	}
	
	/**
	 * 联系时注意问题
	 */
	private String problemToBeMind;

	public String getProblemToBeMind() {
		return problemToBeMind;
	}

	public void setProblemToBeMind(String problemToBeMind) {
		this.problemToBeMind = problemToBeMind;
	}
	
	/**
	 * PDP类型
	 */
	private int PDPType;

	public int getPDPType() {
		return PDPType;
	}

	public void setPDPType(int pDPType) {
		PDPType = pDPType;
	}
	

	/**
	 * 	兴趣爱好
	 */
	private String hobby;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	/**
	 * 关注的服务
	 */
	private String mindingService;

	public String getMindingService() {
		return mindingService;
	}

	public void setMindingService(String mindingService) {
		this.mindingService = mindingService;
	}
}
