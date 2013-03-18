package com.yawaweather.controller;

import java.text.DateFormat;
import java.util.Date;

import com.yawaweather.database.DataBaseMapper;
import com.yawaweather.main.R;
import com.yawaweather.model.PressureManager;
import com.yawaweather.model.TemperatureConversion;
import com.yawaweather.model.TemperatureManager;
import com.yawaweather.model.Weather;
import com.yawaweather.model.Widget;
import com.yawaweather.model.WindConversion;
import com.yawaweather.model.WindDegreesToWindDirection;
import com.yawaweather.model.WindManager;
import com.yawaweather.rss.GetXMLFromWebServices;
import com.yawaweather.utilities.CheckList;
import com.yawaweather.widget.UpdateService;
import com.yawaweather.widget.WeatherWidgetProvider;
import com.yawaweather.widget.WidgetPreferences;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.RemoteViews;

public class Controller {

	private static Controller instance;

	private Controller() {
	}

	public static synchronized Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void addNewWidget(Context context, Widget widget) {
		DataBaseMapper dataBaseMapper = DataBaseMapper.getInstance();
		dataBaseMapper.addWidget(widget, context);
		this.updateWidgetView(context, widget);
	}

	private void updateWidgetView(Context context, Widget widget) {

		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.weather_widget);

		// Set the text with the current time.
		Resources res = context.getResources();

		String[] skyConditions = res
				.getStringArray(R.array.yahoo_sky_conditions_array);
		
		String[] windDirections = res.getStringArray(R.array.yawa_weather_wind_directions);
		
		

		int idSkyConditions = Integer.parseInt(widget.getSkyConditions());

		String skyCondition;

		if (idSkyConditions == 3200) {
			skyCondition = skyConditions[skyConditions.length];
		} else {
			skyCondition = skyConditions[idSkyConditions];
		}

		views.setTextViewText(R.id.city_name, widget.getCityName() + " ("
				+ widget.getCountryName() + ")");
		
		TemperatureManager temperatureManager = new TemperatureManager();
		WindConversion windConversion = new WindDegreesToWindDirection();
		WindManager windManager = new WindManager();
		int windDirectionIndex = windConversion.convert(Integer.parseInt(widget.getWindDegree()));
		
		String windDirection = windDirections[windDirectionIndex];
		
		PressureManager pressureManager = new PressureManager();
		
		views.setTextViewText(R.id.temperature, temperatureManager.getTemperature(Float.parseFloat(widget.getTemperature()), context));
		views.setTextViewText(R.id.sky_conditions, skyCondition);
		views.setTextViewText(R.id.humidity, "H" + " " + widget.getHumidity() + "%");
		views.setTextViewText(R.id.pressure, "P" + " " + pressureManager.getPressure(Double.parseDouble(widget.getPressure()), context));
		views.setTextViewText(R.id.max_temperature, temperatureManager.getTemperature(Float.parseFloat(widget.getHighTemperature()), context));
		views.setTextViewText(R.id.min_temperature, temperatureManager.getTemperature(Float.parseFloat(widget.getLowTemperature()), context));
		views.setTextViewText(R.id.wind,res.getString(R.string.wind) + " " + windManager.getWindVelocity(Integer.parseInt(widget.getWindVelocity()), context) + " "+ windDirection);
		
		//Make a Clickleable Widget
		Intent intent = new Intent(context, WidgetPreferences.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.weather_widget_id, pendingIntent);
		
		appWidgetManager.updateAppWidget(
				Integer.parseInt(widget.getWidgetID()), views);
	}

	public void updateWidgetData(Context context, Widget widget) {

		widget = DataBaseMapper.getInstance().getWidgetInitData(
				widget.getWidgetID(), context);

		if (widget != null) {

			// Check Network Connection to update widget information
			if (CheckList.getInstance().isNetworkConnected(
					(ConnectivityManager) context
							.getSystemService(Context.CONNECTIVITY_SERVICE))) {

				Weather weather = null;

				GetXMLFromWebServices getXMLFromWebServices = new GetXMLFromWebServices();

				weather = getXMLFromWebServices.callYahooWeather(
						widget.getWoeid(), widget.getScale());

				// Update Widget Data
				widget.setHighTemperature(weather.get(0).getHighTemperature());
				widget.setLowTemperature(weather.get(0).getLowTemperature());
				widget.setPressure(weather.getPressure());
				widget.setHumidity(weather.getHumidity());
				widget.setSkyConditions(weather.getSkyConditions());
				widget.setTemperature(weather.getTemperature());
				widget.setUpdateDateTime(DateFormat.getDateTimeInstance().format(new Date()));
				widget.setWindDegree(weather.getWindDegree());
				widget.setWindVelocity(weather.getWindVelocity());
				
				// Update DataBase Information
				DataBaseMapper.getInstance().updateWidget(widget, context);
			}
			//Update Widget View
			updateWidgetView(context, widget);

		}
	}
	
	public void updateAllWidget(Context context){
		
		AppWidgetManager appWidgetManager = AppWidgetManager
				.getInstance(context);
		ComponentName name = new ComponentName(context, WeatherWidgetProvider.class);
		
		int [] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(name);
		
		int n = appWidgetIds.length;
		
		for (int i=0; i<n; i++) {
            int appWidgetId = appWidgetIds[i];
            
            Intent serviceIntent = new Intent(context, UpdateService.class);
            serviceIntent.putExtra("widgetId",appWidgetId);
            context.startService(serviceIntent);
            
        }
		
	}
	
}
