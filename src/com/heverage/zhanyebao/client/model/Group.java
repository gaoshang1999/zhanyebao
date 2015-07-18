package com.heverage.zhanyebao.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.heverage.zhanyebao.util.JSONHelper;

import android.content.Context;

public class Group implements Comparable<Group>{
	
	private Context mContext;

	public Group(Context mContext) {
		super();
		this.mContext = mContext;
	}
	

	public Context getmContext() {
		return mContext;
	}


	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}


	public Group() {
		super();
	}
	
	private int userId;

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int id;
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	private String name;
	

	
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	private String[] initData = {"儿童保险", "重疾病", "子女教育培训", "重疾病", "子女教育培训", "XXXXXXXX", "YYYYYYY", "XXXXXXXX", "YYYYYYY",  "XXXXXXXX", "YYYYYYY"};  

	public List<Group> buildGroupsList(){
		List<Group> groupsList = new ArrayList<Group>();
		for(String c : initData){
			Group group = new Group();
			group.setName(c); 
			groupsList.add(group);
		}
		Collections.sort(groupsList);		
		return groupsList;
	}
	
	@Override
	public int compareTo(Group another) {
		// TODO Auto-generated method stub		
		return this.name.compareTo(another.name);
	}
	
	
	public String toString() {
		return JSONHelper.toJSON(this);
	}

	
}
