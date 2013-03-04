package com.yawaweather.model;

public class FahrenheitToCelsius implements TemperatureConversion{

	@Override
	public String convert(float temperatureFahrenheit) {
		// TODO Auto-generated method stub
		float temperatureCelcius = (temperatureFahrenheit - 32)*5/9;
		return Math.round(temperatureCelcius) + "°C";
	}

}
