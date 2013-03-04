package com.yawaweather.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class TemperatureManager {
	
	private TemperatureConversion temperatureConversion;
	
	public String getTemperature(float temperatureFahrenheit, Context context){
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		
		int scale = Integer.parseInt(settings.getString("setting_temperature", "0"));
		
		Log.d("T",Integer.toString(scale));
		
		switch (scale) {
			case 0:
				this.temperatureConversion = new FahrenheitToFahrenheit();
				break;
	
			case 1:
				this.temperatureConversion = new FahrenheitToCelsius();
				break;
			
			case 2:
				this.temperatureConversion = new FahrenheitToKelvin();
				break;	
		}
		return this.temperatureConversion.convert(temperatureFahrenheit);
	}
	
}
