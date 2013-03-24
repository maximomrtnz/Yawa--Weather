package com.yawaweather.widget;


import com.yawaweather.controller.Controller;
import com.yawaweather.main.R;
import com.yawaweather.utilities.NotificationsManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;



@SuppressLint("NewApi")
public class WidgetPreferences extends PreferenceActivity {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		try {
			Controller.getInstance().updateAllWidget(getApplicationContext());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			NotificationsManager.getInstance().toastNotification(getApplicationContext(), R.string.error);
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

	
	
}