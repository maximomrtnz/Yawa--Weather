package com.yawaweather.controller;

import java.text.DateFormat;
import java.util.Date;

import com.yawaweather.database.DataBaseMapper;
import com.yawaweather.main.R;
import com.yawaweather.model.Weather;
import com.yawaweather.model.Widget;
import com.yawaweather.rss.GetXMLFromWebServices;
import com.yawaweather.utilities.CheckList;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
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

		int idSkyConditions = Integer.parseInt(widget.getSkyConditions());

		String skyCondition;

		if (idSkyConditions == 3200) {
			skyCondition = skyConditions[skyConditions.length];
		} else {
			skyCondition = skyConditions[idSkyConditions];
		}

		views.setTextViewText(R.id.city_name, widget.getCityName() + " ("
				+ widget.getCountryName() + ")");
		views.setTextViewText(R.id.temperature, widget.getTemperature() + " "
				+ widget.getScale());
		views.setTextViewText(R.id.sky_conditions, skyCondition);
		views.setTextViewText(R.id.humidity, "H" + " " + widget.getHumidity());
		views.setTextViewText(R.id.pressure, "P" + " " + widget.getPressure());
		views.setTextViewText(R.id.max_temperature, widget.getHighTemperature());
		views.setTextViewText(R.id.min_temperature, widget.getLowTemperature());

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
				widget.setUpdateDateTime(DateFormat.getDateTimeInstance()
						.format(new Date()));
				
				// Update DataBase Information
				DataBaseMapper.getInstance().updateWidget(widget, context);
			}
			//Update Widget View
			updateWidgetView(context, widget);

		}
	}

}
