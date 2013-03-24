package com.yawaweather.utilities;

import com.yawaweather.widget.UpdateService;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class ConnectivityReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent){
		// TODO Auto-generated method stub

		ComponentName thiswidget = new ComponentName(context, com.yawaweather.widget.WeatherWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		int[] appWidgetIds = manager.getAppWidgetIds(thiswidget);
		
		int n = appWidgetIds.length;
			
		 for (int i=0; i<n; i++) {
	            int appWidgetId = appWidgetIds[i];       
	            Intent serviceIntent = new Intent(context, UpdateService.class);
	            serviceIntent.putExtra("widgetId",appWidgetId);
	            context.startService(serviceIntent);
	     }
		 
		 
	}

}
