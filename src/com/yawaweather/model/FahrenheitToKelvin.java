package com.yawaweather.model;

import android.util.Log;

public class FahrenheitToKelvin implements TemperatureConversion{

	@Override
	public String convert(float temperatureFahrenheit) {
		// TODO Auto-generated method stub
		float temperatureKelvin = 5*(temperatureFahrenheit - 32)/9 + 273;
		Log.d("K", Float.toHexString(temperatureKelvin));
		return Math.round(temperatureKelvin) + "K";
	}

}
