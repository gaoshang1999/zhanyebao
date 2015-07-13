package com.heverage.zhanyebao.client.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable{
	private int addressType;
	
	private String region;
	
	private String address;
	
	
	public int getAddressType() {
		return addressType;
	}
	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(addressType);
		dest.writeString(region);
		dest.writeString(address);
	}		
	
	public static final Parcelable.Creator<Address> CREATOR = new Creator<Address>() { 
        public Address createFromParcel(Parcel source) { 
        	Address ue = new Address(); 
            ue.addressType = source.readInt();
            ue.region = source.readString(); 
            ue.address = source.readString(); 
            return ue; 
        } 
        public Address[] newArray(int size) { 
        	return new Address[size]; 
        } 
    }; 
}
