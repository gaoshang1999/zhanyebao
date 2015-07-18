package com.heverage.zhanyebao.client.model;

import com.heverage.zhanyebao.util.JSONHelper;

import android.os.Parcel;
import android.os.Parcelable;

public class Email implements Parcelable{
	private int email_type_value;
	private String email;
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(email_type_value);
		dest.writeString(email);
	}		
	
	public static final Parcelable.Creator<Email> CREATOR = new Creator<Email>() { 
        public Email createFromParcel(Parcel source) { 
        	Email ue = new Email(); 
            ue.email_type_value = source.readInt();
            ue.email = source.readString(); 
            return ue; 
        } 
        public Email[] newArray(int size) { 
        	return new Email[size]; 
        } 
    };


 
	public int getEmail_type_value() {
		return email_type_value;
	}
	public void setEmail_type_value(int email_type_value) {
		this.email_type_value = email_type_value;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	public String toString() {
		return JSONHelper.toJSON(this);
	}
}
