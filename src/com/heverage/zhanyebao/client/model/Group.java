package com.heverage.zhanyebao.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group implements Comparable<Group>{

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
	
	public List<Client> getClentsList(){
		List<Client> clientsList = new ArrayList<Client>();
		 	
		return clientsList;
	}
}
