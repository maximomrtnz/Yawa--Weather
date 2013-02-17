package com.yawaweather.widget;


import com.yawaweather.database.DataBaseMapper;

import com.yawaweather.model.Widget;
import com.yawaweather.utilities.CheckList;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;




public class WeatherWidgetProvider extends AppWidgetProvider{
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		
		DataBaseMapper dataBaseMapper = DataBaseMapper.getInstance();
		
		for(int widgetId : appWidgetIds){
			Widget widget = new Widget();
			widget.setWidgetID(Integer.toString(widgetId));
			dataBaseMapper.deleteWidget(widget, context);
		}
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}
	
	
	
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		// Perform this loop procedure for each App Widget that belongs to this provider
		final int n = appWidgetIds.length;
		
		//Check Network Connection
		
		boolean hasConnectionNetwork = CheckList.getInstance().isNetworkConnected((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
		
		DataBaseMapper dataBaseMapper = DataBaseMapper.getInstance();
		
		for (int i=0; i<n; i++) {
            int appWidgetId = appWidgetIds[i];
            
            Intent serviceIntent = new Intent(context, UpdateService.class);
            serviceIntent.putExtra("widgetId",appWidgetId);
            context.startService(serviceIntent);
            
        }
	}
	
}
