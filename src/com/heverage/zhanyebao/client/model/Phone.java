package com.heverage.zhanyebao.client.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable{
	private int phoneType;
	private String phoneNum;
	public int getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(int phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(phoneType);
		dest.writeString(phoneNum);
	}		
	
	public static final Parcelable.Creator<Phone> CREATOR = new Creator<Phone>() { 
        public Phone createFromParcel(Parcel source) { 
        	Phone ue = new Phone(); 
            ue.phoneType = source.readInt();
            ue.phoneNum = source.readString(); 
            return ue; 
        } 
        public Phone[] newArray(int size) { 
        	return new Phone[size]; 
        } 
    }; 
}
