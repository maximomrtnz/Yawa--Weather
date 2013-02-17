package com.yawaweather.model;

//This Class represent a Place in Yahoo Api.
public class Place {
	
	private String woeid;
	private String name;
	private String countryName;
	private String latitude;
	private String longitude;
	
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
	
}
