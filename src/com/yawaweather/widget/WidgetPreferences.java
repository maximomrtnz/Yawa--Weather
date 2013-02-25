package com.yawaweather.widget;


import com.yawaweather.dialogs.InputPresureScaleDialog;
import com.yawaweather.dialogs.InputPresureScaleDialog.InputPresureScaleDialogListener;
import com.yawaweather.dialogs.InputTemperatureScaleDialog;
import com.yawaweather.dialogs.InputTemperatureScaleDialog.InputTemperatureScaleDialogListener;
import com.yawaweather.main.R;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class WidgetPreferences extends FragmentActivity implements InputTemperatureScaleDialogListener,InputPresureScaleDialogListener{
	
	private TextView temperatureScale;
	private TextView pressureScale;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.widget_preferences);
		
		this.temperatureScale = (TextView)findViewById(R.id.widget_temperature_scale);
		this.pressureScale = (TextView)findViewById(R.id.widget_preference);
		
		this.temperatureScale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSetTemperatureScaleDialog();
			}
		});
		
		this.pressureScale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSetPressureScaleDialog();
			}
		});
		
	}
	
	
	
	public void showSetTemperatureScaleDialog(){
		 FragmentManager fragmentManager = getSupportFragmentManager();
	     InputTemperatureScaleDialog inputTemperatureScaleDialog = new InputTemperatureScaleDialog();
	     inputTemperatureScaleDialog.show(fragmentManager, "fragment_input_temperature_scale");
	     //This is to prevent back button
	     inputTemperatureScaleDialog.setCancelable(false);
	}
	
	public void showSetPressureScaleDialog(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		InputPresureScaleDialog inputPresureScaleDialog = new InputPresureScaleDialog();
		inputPresureScaleDialog.show(fragmentManager, "fragment_input_pressure_scale");
		 //This is to prevent back button
	    inputPresureScaleDialog.setCancelable(false);
	}



	@Override
	public void onInputTemperatureScaleDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onInputTemperatureScaleDialogClickItem(DialogFragment dialog,
			int checked) {
		// TODO Auto-generated method stub
		Log.d("Temperature Scale", Integer.toString(checked));
	}



	@Override
	public void onInputPresureScaleDialogClickItem(DialogFragment dialog,
			int checked) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onInputPresureScaleDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
	
}
