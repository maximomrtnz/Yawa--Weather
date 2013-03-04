package com.yawaweather.model;

public class FahrenheitToFahrenheit implements TemperatureConversion{
	
	@Override
	public String convert(float temperatureFahrenheit) {
		// TODO Auto-generated method stub
		return Math.round(temperatureFahrenheit) + "°F";
	}
}
