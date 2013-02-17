package com.yawaweather.widget;


import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.yawaweather.database.DataBaseMapper;
import com.yawaweather.asynctask.PlacesLoader;
import com.yawaweather.asynctask.WeatherLoader;
import com.yawaweather.database.DataBaseHandler;
import com.yawaweather.dialogs.InputLocationDialog;
import com.yawaweather.dialogs.InputLocationDialog.InputLocationDialogListener;
import com.yawaweather.dialogs.LoadingDataDialog;
import com.yawaweather.dialogs.LoadingDataDialog.LoadingDataDialogListener;
import com.yawaweather.dialogs.LocationListDialog;
import com.yawaweather.dialogs.LocationListDialog.LocationListDialogListener;
import com.yawaweather.dialogs.LocationListView;
import com.yawaweather.dialogs.NetworkConnectionOffDialog;
import com.yawaweather.dialogs.NetworkConnectionOffDialog.NetworkConnectionOffListener;
import com.yawaweather.main.R;
import com.yawaweather.model.Place;
import com.yawaweather.model.Weather;
import com.yawaweather.model.Widget;
import com.yawaweather.utilities.CheckList;
import com.yawaweather.utilities.PlacesSetter;
import com.yawaweather.utilities.WeatherSetter;

public class WidgetConfigurationActivity extends FragmentActivity implements PlacesSetter,WeatherSetter,NetworkConnectionOffListener,InputLocationDialogListener,LoadingDataDialogListener, LocationListDialogListener{
	
	
	private PlacesLoader placesLoader = new PlacesLoader();
	private WeatherLoader weatherLoader = new WeatherLoader();
	private int widgetID;
	private ArrayList<Place> places;
	private Widget widget = new Widget();
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("WCA", "Destroy");
		super.onDestroy();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			
		setResult(RESULT_CANCELED);
		
		Intent intent = getIntent();
		
		Bundle extras = intent.getExtras();
		
		if (extras != null) {
		    this.widgetID = extras.getInt(
		            AppWidgetManager.EXTRA_APPWIDGET_ID, 
		            AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		// If gave us an intent without the widget id, just finish.
	    if (this.widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
	         finish();
	    }
		
		setVisible(false);
		
	
		//Check Network Connection
		
		if(CheckList.getInstance().isNetworkConnected((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))){
			//If We have Network Connection then We show Input Location Dialog
			showInputLocationDialog();
		}else{
			//We don't have Network Connection so We show an alert dialog explains this
			showNetworkConnectionOffDialog();
		}
		
		
	}
	
	 private void showInputLocationDialog() {
		 FragmentManager fragmentManager = getSupportFragmentManager();
	     InputLocationDialog inputLocationDialog = new InputLocationDialog();
	     inputLocationDialog.show(fragmentManager, "fragment_input_location");
	 }
	
	 private void showNetworkConnectionOffDialog(){
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 NetworkConnectionOffDialog networkConnectionOffDialog = new NetworkConnectionOffDialog();
		 networkConnectionOffDialog.show(fragmentManager, "fragment_network_connection");
	 }
	 
	 private void showLoadingDataDialog(){
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 LoadingDataDialog loadingDataDialog = new LoadingDataDialog();
		 loadingDataDialog.show(fragmentManager, "fragment_loading_data");
	 }
	 
	 private void showLocationsListDialog(){
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 LocationListDialog locationListDialog = new LocationListDialog();
		 locationListDialog.setPlacesList(this.places);
		 locationListDialog.show(fragmentManager, "fragment_loading_data");
	 }

	@Override
	public void onNetworkConnectionOffDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void onInputLocationDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
		//Init AsinkTask to get Data From WebServices
		this.showLoadingDataDialog();
			
		this.placesLoader.execute(((InputLocationDialog)dialog).getLocation(),this);
		
	}

	@Override
	public void onInputLocationDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void onLoadingDataDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		finish();
	}


	private void dissmissLoadingDialog(){
	 
		DialogFragment dialogFragment = (DialogFragment)getSupportFragmentManager().findFragmentByTag("fragment_loading_data"); 
		if (dialogFragment != null) { 
			dialogFragment.dismiss(); 
		} 
		
	}

	@Override
	public void onLocationListDialogPositiveClick(DialogFragment dialog, int id) {
		// TODO Auto-generated method stub
		//Init AsinkTask to get Data From WebServices
		this.showLoadingDataDialog();
		
		//Set Place Data to Widget
		this.widget.setCityName(this.places.get(id).getName());
		this.widget.setCountryName(this.places.get(id).getCountryName());
		this.widget.setWoeid(this.places.get(id).getWoeid());
		
		//Call AsynTask to get weather data from web services
		this.weatherLoader.execute(this.places.get(id).getWoeid(),"",this);

	}

	@Override
	public void onLocationListDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	public void setWeather(Weather weather) {
		// TODO Auto-generated method stub
		this.dissmissLoadingDialog();
		
		this.widget.setWidgetID(Integer.toString(this.widgetID));
		
		this.widget.setHighTemperature(weather.get(0).getHighTemperature());
		
		this.widget.setLowTemperature(weather.get(0).getLowTemperature());
		
		this.widget.setTemperature(weather.getTemperature());
		
		this.widget.setHumidity(weather.getHumidity());
		
		this.widget.setPressure(weather.getPressure());
		
		this.widget.setSkyConditions(weather.getSkyConditions());
		
		this.widget.setScale("F");
		
		this.widget.setUpdateDateTime(DateFormat.getDateTimeInstance().format(new Date()));
		
		DataBaseMapper dataBaseMapper = DataBaseMapper.getInstance();
		
		dataBaseMapper.addWidget(this.widget, getApplicationContext());
		
		//Update Widget
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
		
		RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(),R.layout.weather_widget);
		
		// Set the text with the current time.
		
		Resources res = getResources();
		
		String[] skyConditions = res.getStringArray(R.array.yahoo_sky_conditions_array);
		
		int idSkyConditions = Integer.parseInt(widget.getSkyConditions());
		
		String skyCondition;
		
		if(idSkyConditions == 3200){
			skyCondition = skyConditions[skyConditions.length];
		}else{
			skyCondition = skyConditions[idSkyConditions];
		}
		
		
		views.setTextViewText(R.id.city_name,widget.getCityName() +" ("+widget.getCountryName()+")");
		views.setTextViewText(R.id.temperature, widget.getTemperature());
		views.setTextViewText(R.id.sky_conditions, skyCondition);
		views.setTextViewText(R.id.humidity, "H"+" "+weather.getHumidity());
		views.setTextViewText(R.id.pressure, "P"+" "+weather.getPressure());
		views.setTextViewText(R.id.max_temperature, "Max."+" "+weather.get(0).getHighTemperature());
		views.setTextViewText(R.id.min_temperature, "Min."+" "+weather.get(0).getLowTemperature());
		
		appWidgetManager.updateAppWidget(this.widgetID, views);
				
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, this.widgetID);
		setResult(RESULT_OK, resultValue);
		finish();
	}

	@Override
	public void setPlaces(ArrayList<Place> places) {
		// TODO Auto-generated method stub
		this.dissmissLoadingDialog();
		
		this.places = places;
		
		if(this.places.size() > 0){
			//showLocationsListDialog();
			Intent intent = new Intent(this, LocationListView.class);
			Bundle bundle = new Bundle();
		    intent.putParcelableArrayListExtra("places", places);
		    startActivityForResult(intent, 1);
		}else{
			//We dont find your location, please try again
			Context context = getApplicationContext();
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, R.string.yawa_toast_loaction_not_results, duration);
			toast.show();
			this.showInputLocationDialog();
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		if(requestCode == 1 && resultCode == RESULT_OK){
			Bundle bundle = intent.getExtras();
			Place place = bundle.getParcelable("place");
			
			//Set Place Data to Widget
			this.widget.setCityName(place.getName());
			this.widget.setCountryName(place.getCountryName());
			this.widget.setWoeid(place.getWoeid());
			
			//Call AsynTask to get weather data from web services
			this.weatherLoader.execute(place.getWoeid(),"",this);
		}
	
	}
	
	
}

