package com.yawaweather.widget;

import java.text.DateFormat;
import java.util.Date;

import com.yawaweather.database.DataBaseMapper;
import com.yawaweather.main.R;
import com.yawaweather.model.Weather;
import com.yawaweather.model.Widget;
import com.yawaweather.rss.GetXMLFromWebServices;
import com.yawaweather.utilities.CheckList;

import android.app.IntentService;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateService extends IntentService{

	public UpdateService() {
		super("update widget");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		//get widget id from intent
		try{

			Log.d("Service", "Entre_1");
			
			int widgetId = intent.getIntExtra("widgetId", 0);
			
			Log.d("Service", Integer.toString(widgetId));
			
			//Get Widget Information from DataBase
			
			Widget widget = DataBaseMapper.getInstance().getWidgetInitData(Integer.toString(widgetId),getApplicationContext());
			
			if(widget!=null){
				Log.d("Service", "Entre_2");			
				//Check Network Connection to update widget information
				if(CheckList.getInstance().isNetworkConnected((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))){
					Log.d("Service", "Entre_3");
					
					Weather weather = null;
					
					GetXMLFromWebServices getXMLFromWebServices = new GetXMLFromWebServices();
					
					weather = getXMLFromWebServices.callYahooWeather(widget.getWoeid(), widget.getScale());
					
					//Update Widget Data
					
					//Transform Sky Conditions Id to Text
					
					Resources res = getResources();
					
					String[] skyConditions = res.getStringArray(R.array.yahoo_sky_conditions_array);
					
					int idSkyConditions = Integer.parseInt(weather.getSkyConditions());
										
					String skyCondition;
					
					if(idSkyConditions == 3200){
						skyCondition = skyConditions[skyConditions.length];
					}else{
						skyCondition = skyConditions[idSkyConditions];
					}
					
					widget.setHighTemperature(weather.get(0).getHighTemperature());
					widget.setLowTemperature(weather.get(0).getLowTemperature());
					widget.setPressure(weather.getPressure());
					widget.setHumidity(weather.getHumidity());
					widget.setSkyConditions(skyCondition);
					widget.setTemperature(weather.getTemperature());
					widget.setUpdateDateTime(DateFormat.getDateTimeInstance().format(new Date()));
					//Update DataBase Information
					DataBaseMapper.getInstance().updateWidget(widget,getApplicationContext());
				}
				
				//Finally Update Widget
				
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
				
				RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(),R.layout.weather_widget);
				
				views.setTextViewText(R.id.city_name,widget.getCityName() +" ("+widget.getCountryName()+")");
				views.setTextViewText(R.id.temperature, widget.getTemperature());
				views.setTextViewText(R.id.sky_conditions, widget.getSkyConditions());
				views.setTextViewText(R.id.humidity, "H"+" "+widget.getHumidity());
				views.setTextViewText(R.id.pressure, "P"+" "+widget.getPressure());
				views.setTextViewText(R.id.max_temperature, "Max."+" "+widget.getHighTemperature());
				views.setTextViewText(R.id.min_temperature, "Min."+" "+widget.getLowTemperature());
				
				appWidgetManager.updateAppWidget(widgetId, views);
			}
			
		}catch (Exception e) {
				// TODO: handle exception
				Log.d("Error while weather data was getting", e.getMessage());
		}		
				
	}
	
	


}
