package com.yawaweather.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yawaweather.model.Weather;
import com.yawaweather.model.WeatherForecast;

import android.util.Log;



public class RSSHandler extends DefaultHandler{
	
	private Weather weather;

	
	/*
	 * getFeed - this returns our weather when all of the parsing is complete
	 */
	public Weather getWeather(){
		return weather;
	}
	
	
	public void startDocument() throws SAXException{
		// initialize our RSSFeed object - this will hold our parsed contents
		this.weather = new Weather();
	}
	
	
	public void endDocument() throws SAXException{
	}
	
	public void startElement(String namespaceURI, String localName,String qName, Attributes attributes) throws SAXException{
			
		if (localName.equals("atmosphere")) {
			this.weather.setHumidity(attributes.getValue("humidity"));
			this.weather.setPressure(attributes.getValue("pressure"));
		}
		
		if (localName.equals("condition")) {
			this.weather.setSkyConditions(attributes.getValue("code"));
			this.weather.setTemperature(attributes.getValue("temp"));
		}
		
		if (localName.equals("forecast")) {
			//We only need the first occurrence of forecast because this contain
			//today high and low temperature
			WeatherForecast weatherForecast = new WeatherForecast();
			weatherForecast.setDay(attributes.getValue("day"));
			weatherForecast.setLowTemperature(attributes.getValue("low"));
			weatherForecast.setHighTemperature(attributes.getValue("high"));
			weatherForecast.setSkyConditions(attributes.getValue("high"));
			
			this.weather.addForecast(weatherForecast);
		}
		
		

	}
	
	
}
