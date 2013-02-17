package com.yawaweather.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//This class is use to check somethings that YawaWeather needs to found

public class CheckList {
	
	//make this class singleton
	private static CheckList instance = new CheckList();
	
	private CheckList(){}
	
	public static CheckList getInstance(){
		return instance;
	}
	
	//This method is important because we need Network Connection to get Weather Information 
	public boolean isNetworkConnected(ConnectivityManager connectivityManager){
		 NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		 return activeNetworkInfo != null;
	}
	
}
