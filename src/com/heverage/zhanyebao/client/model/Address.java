package com.heverage.zhanyebao.client.model;

import com.heverage.zhanyebao.util.JSONHelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable{
	private int address_type_value;
	
	private String region;
	
	private String detail_address;
	 
	public int getAddress_type_value() {
		return address_type_value;
	}
	public void setAddress_type_value(int address_type_value) {
		this.address_type_value = address_type_value;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String address) {
		this.detail_address = address;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(address_type_value);
		dest.writeString(region);
		dest.writeString(detail_address);
	}		
	
	public static final Parcelable.Creator<Address> CREATOR = new Creator<Address>() { 
        public Address createFromParcel(Parcel source) { 
        	Address ue = new Address(); 
            ue.address_type_value = source.readInt();
            ue.region = source.readString(); 
            ue.detail_address = source.readString(); 
            return ue; 
        } 
        public Address[] newArray(int size) { 
        	return new Address[size]; 
        } 
    }; 
    
	public String toString() {
		return JSONHelper.toJSON(this);
	}
}
