package com.yawaweather.asynctask;

import java.util.ArrayList;

import com.yawaweather.model.Place;
import com.yawaweather.model.Weather;
import com.yawaweather.rss.GetXMLFromWebServices;
import com.yawaweather.utilities.Observable;
import com.yawaweather.utilities.Observer;
import com.yawaweather.utilities.WeatherSetter;

import android.os.AsyncTask;
import android.util.Log;

public class WeatherLoader extends AsyncTask<Object, Void, Weather> {
	
	private WeatherSetter callback;

	@Override
	protected Weather doInBackground(Object... params) {
		// TODO Auto-generated method stub
		Weather weather = null;
		
		//Set callback object
		
		this.callback = (WeatherSetter)params[2];
		
		try{
			GetXMLFromWebServices getXMLFromWebServices = new GetXMLFromWebServices();
			weather = getXMLFromWebServices.callYahooWeather(String.valueOf(params[0]), String.valueOf(params[1]));
		}catch (Exception e) {
			// TODO: handle exception
			Log.d("Error while weather data was getting", e.getMessage());
		}
		return weather;
	}
	
	@Override
	protected void onPostExecute(Weather result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		//Notify View
		this.callback.setWeather(result);
	
	}


}
