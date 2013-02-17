package com.yawaweather.model;

import android.os.Parcel;
import android.os.Parcelable;

//This Class represent a Place in Yahoo Api.
public class Place implements Parcelable{
	
	private String woeid;
	private String name;
	private String countryName;
	private String latitude;
	private String longitude;
	
	public Place(){}
	
	public String getCountryName() {
		return countryName;
	}
	
	public String getLatitude() {
		return latitude;
	} 
	
	public String getLongitude() {
		return longitude;
	}
	public String getName() {
		return name;
	}
	
	public String getWoeid() {
		return woeid;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	} 
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel output, int flags) {
		// TODO Auto-generated method stub
		output.writeString(this.woeid);
		output.writeString(this.name);
		output.writeString(this.countryName);
		output.writeString(this.latitude);
		output.writeString(this.longitude);
	}
	
	public Place(Parcel input){
		this.woeid 			= input.readString();
		this.name 			= input.readString();
		this.countryName 	= input.readString();
		this.latitude 		= input.readString();
		this.longitude 		= input.readString();
	}
	
	public static final Parcelable.Creator<Place> CREATOR= new Parcelable.Creator<Place>() {
		public Place createFromParcel(Parcel input) {
		    return new Place(input);
		}

		public Place[] newArray(int size) {
		    return new Place[size];
		}
	};

	
}
