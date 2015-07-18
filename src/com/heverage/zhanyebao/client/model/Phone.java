package com.heverage.zhanyebao.client.model;

import com.heverage.zhanyebao.util.JSONHelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable{
	private int phone_type_value;
	
	private String phone_number;
	
	public int getPhone_type_value() {
		return phone_type_value;
	}
	public void setPhone_type_value(int phone_type_value) {
		this.phone_type_value = phone_type_value;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(phone_type_value);
		dest.writeString(phone_number);
	}		
	
	public static final Parcelable.Creator<Phone> CREATOR = new Creator<Phone>() { 
        public Phone createFromParcel(Parcel source) { 
        	Phone ue = new Phone(); 
            ue.phone_type_value = source.readInt();
            ue.phone_number = source.readString(); 
            return ue; 
        } 
        public Phone[] newArray(int size) { 
        	return new Phone[size]; 
        } 
    }; 
    
	public String toString() {
		return JSONHelper.toJSON(this);
	}
}
