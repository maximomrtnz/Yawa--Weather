package com.yawaweather.database;

import com.yawaweather.model.Widget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseMapper {
	
	private String[] widgetsColumn = {WidgetContract.KEY_WIDGETS_ID,
									  WidgetContract.KEY_WIDGETS_COUNTRY_NAME,
									  WidgetContract.KEY_WIDGETS_CITY_NAME,
									  WidgetContract.KEY_WIDGETS_HIGH_TEMPERATURE,
									  WidgetContract.KEY_WIDGETS_LAST_HUMEDITY,
									  WidgetContract.KEY_WIDGETS_LAST_PRESURE,
									  WidgetContract.KEY_WIDGETS_LOW_TEMPERATURE,
									  WidgetContract.KEY_WIDGETS_LAST_SKY_CONDITIONS,
									  WidgetContract.KEY_WIDGETS_LAST_TEMPERATURE,
									  WidgetContract.KEY_WIDGETS_SCALE_DATA,
									  WidgetContract.KEY_WIDGETS_WOEID,
									  WidgetContract.KEY_WIDGETS_LAST_UPDATE_DATETIME,
									  WidgetContract.KEY_WIDGETS_WIND_DEGREE,
									  WidgetContract.KEY_WIDGETS_WIND_VELOCITY};
	
	//Make This Class Singleton
	public static DataBaseMapper instance;
	
	private DataBaseMapper(){}
	
	public synchronized static DataBaseMapper getInstance(){
		if(instance == null){
			instance =  new DataBaseMapper();
		}
		return instance;
	}
	
	public Widget getWidgetInitData(String widgetId, Context context) throws Exception{
	 	
		
		
		//Get Yawa DataBase
		SQLiteDatabase db = DataBaseHandler.getInstance(context).getReadableDatabase();
		
		Cursor cursorWidgets = db.query(WidgetContract.TABLE_WIDGETS, this.widgetsColumn, WidgetContract.KEY_WIDGETS_ID + " = " + widgetId , null, null, null, null);
		
		if(cursorWidgets==null){
			return null;
		}else if(!cursorWidgets.moveToFirst()){
			cursorWidgets.close();
			return null;
		}
		
		Widget widget = new Widget();
		
		widget.setWidgetID(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_ID)));
		widget.setCountryName(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_COUNTRY_NAME)));
		widget.setCityName(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_CITY_NAME)));
		widget.setTemperature(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LAST_TEMPERATURE)));
		widget.setPressure(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LAST_PRESURE)));
		widget.setHumidity(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LAST_HUMEDITY)));
		widget.setHighTemperature(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_HIGH_TEMPERATURE)));
		widget.setLowTemperature(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LOW_TEMPERATURE)));
		widget.setScale(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_SCALE_DATA)));
		widget.setWoeid(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_WOEID)));
		widget.setSkyConditions(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LAST_SKY_CONDITIONS)));
		widget.setUpdateDateTime(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_LAST_UPDATE_DATETIME)));
		widget.setWindDegree(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_WIND_DEGREE)));
		widget.setWindVelocity(cursorWidgets.getString(cursorWidgets.getColumnIndex(WidgetContract.KEY_WIDGETS_WIND_VELOCITY)));
		
		cursorWidgets.close();
		// return Widget Init Data
		return widget;
	}
	
	
	public void addWidget(Widget widget, Context context) throws Exception{
		
	
				
		//Get Yawa DataBase
		SQLiteDatabase db = DataBaseHandler.getInstance(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(WidgetContract.KEY_WIDGETS_ID, widget.getWidgetID()); 
		
		values.put(WidgetContract.KEY_WIDGETS_COUNTRY_NAME,widget.getCountryName()); 
		
		values.put(WidgetContract.KEY_WIDGETS_CITY_NAME,widget.getCityName()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_TEMPERATURE,widget.getTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_HUMEDITY,widget.getHumidity());
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_PRESURE,widget.getPressure()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LOW_TEMPERATURE,widget.getLowTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_HIGH_TEMPERATURE,widget.getHighTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_SCALE_DATA,widget.getScale()); 
		
		values.put(WidgetContract.KEY_WIDGETS_WOEID,widget.getWoeid()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_SKY_CONDITIONS,widget.getSkyConditions()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_UPDATE_DATETIME, widget.getUpdateDateTime());
		
		values.put(WidgetContract.KEY_WIDGETS_WIND_DEGREE, widget.getWindDegree());
		
		values.put(WidgetContract.KEY_WIDGETS_WIND_VELOCITY, widget.getWindVelocity());
		
		// Inserting Row
		db.insert(WidgetContract.TABLE_WIDGETS, null, values);
	

	}
	
	
	public void updateWidget(Widget widget,Context context) throws Exception{
						
		//Get Yawa DataBase
		SQLiteDatabase db = DataBaseHandler.getInstance(context).getWritableDatabase();
				
		ContentValues values = new ContentValues();
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_TEMPERATURE,widget.getTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_HUMEDITY,widget.getHumidity());
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_PRESURE,widget.getPressure()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LOW_TEMPERATURE,widget.getLowTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_HIGH_TEMPERATURE,widget.getHighTemperature()); 
		
		values.put(WidgetContract.KEY_WIDGETS_SCALE_DATA,widget.getScale()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_SKY_CONDITIONS,widget.getSkyConditions()); 
		
		values.put(WidgetContract.KEY_WIDGETS_LAST_UPDATE_DATETIME, widget.getUpdateDateTime());
		
		values.put(WidgetContract.KEY_WIDGETS_WIND_DEGREE, widget.getWindDegree());
		
		values.put(WidgetContract.KEY_WIDGETS_WIND_VELOCITY, widget.getWindVelocity());
		
		//Actualizamos el registro en la base de datos
		db.update(WidgetContract.TABLE_WIDGETS, values,WidgetContract.KEY_WIDGETS_ID + " = ?" , new String[] { widget.getWidgetID() });
		
	
	}
	
	
	
	// Deleting single Widget
	public void deleteWidget(Widget widget, Context context) throws Exception{
							
		//Get Yawa DataBase
		SQLiteDatabase db = DataBaseHandler.getInstance(context).getWritableDatabase();
			
		db.delete(WidgetContract.TABLE_WIDGETS, WidgetContract.KEY_WIDGETS_ID + " = ?",
				new String[] { widget.getWidgetID() });
		

	}

	
}
