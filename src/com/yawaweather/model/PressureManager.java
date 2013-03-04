package com.yawaweather.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class PressureManager {
	
	private PressureConversion pressureConversion;
	
	public String getPressure(double pressureInHg, Context context){
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		
		int scale = Integer.parseInt(settings.getString("setting_pressure", "0"));
		

		
		Log.d("T",Integer.toString(scale));
		
		switch (scale) {
			case 0:
				this.pressureConversion = new InHgToInHg();
				break;
	
			case 1:
				this.pressureConversion = new InHgToHectopascal();
				break;
			
		}
		return this.pressureConversion.convert(pressureInHg);
	}
	
}
