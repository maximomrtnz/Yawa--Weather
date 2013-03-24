package com.yawaweather.widget;



import com.yawaweather.controller.Controller;
import com.yawaweather.main.R;
import com.yawaweather.model.Widget;
import com.yawaweather.utilities.NotificationsManager;

import android.app.IntentService;

import android.content.Intent;

import android.util.Log;


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
			
			int widgetId = intent.getIntExtra("widgetId", 0);
			
			Widget widget = new Widget();
			
			widget.setWidgetID(Integer.toString(widgetId));
			
			Controller.getInstance().updateWidgetData(getApplicationContext(), widget);
			
			
		}catch (Exception e) {
				// TODO: handle exception
				NotificationsManager.getInstance().statusBarNotification(getApplicationContext(), R.string.error_title, R.string.error_getting_weather_data);
				
		}		
				
	}
	
	


}
