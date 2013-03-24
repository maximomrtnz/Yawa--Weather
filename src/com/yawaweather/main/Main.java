package com.yawaweather.main;

import com.yawaweather.utilities.NotificationsManager;
import com.yawaweather.widget.WidgetPreferences;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;



public class Main extends Activity {

		 
	    @SuppressLint("NewApi")
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Display the fragment as the main content.
	       NotificationsManager.getInstance().statusBarNotification(getApplicationContext(), R.string.error_title, R.string.error_getting_weather_data);
	    }
	

}
