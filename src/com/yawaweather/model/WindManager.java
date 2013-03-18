package com.yawaweather.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WindManager {
	
	private WindVelocityConversion velocityConversion = new WindConvertVelocityKmhToMph();
	
	public String getWindVelocity(int mPHVelocityWind, Context context){
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		
		int scale = Integer.parseInt(settings.getString("setting_wind_velocity", "0"));
			
		switch (scale) {
			case 0:
				this.velocityConversion = new WindConvertVelocityMphToKmh();
				break;
	
			case 1:
				this.velocityConversion = new WindConvertVelocityKmhToMph();
				break;
			
			
		}
		return this.velocityConversion.convert(mPHVelocityWind);
	}
	
}
