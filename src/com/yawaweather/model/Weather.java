package com.yawaweather.model;

import java.util.ArrayList;

public class Weather {

	private String temperature;
	private String humidity;
	private String pressure;
	private String skyConditions;
	private String windVelocity;
	private String windDegree;
	private ArrayList<WeatherForecast> forecasts = new ArrayList<WeatherForecast>();

	
	public String getSkyConditions() {
		return skyConditions;
	}
	
	public void setSkyConditions(String skyConditions) {
		this.skyConditions = skyConditions;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public void addForecast(WeatherForecast weatherForecast){
		this.forecasts.add(weatherForecast);
	}
	
	public WeatherForecast get(int id){
		return this.forecasts.get(id);
	}
	
	public String getWindVelocity() {
		return windVelocity;
	}
	public void setWindVelocity(String windVelocity) {
		this.windVelocity = windVelocity;
	}
	
	public String getWindDegree() {
		return windDegree;
	}
	public void setWindDegree(String windDegree) {
		this.windDegree = windDegree;
	}
	
	
	
}
