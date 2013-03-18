package com.yawaweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper{
	
	private static DataBaseHandler instance;

	public static synchronized DataBaseHandler getInstance(Context context){
		if(instance == null){
			instance = new DataBaseHandler(context);
		}
		return instance;
	}
	
	
	// Database Version
	public static final int DATABASE_VERSION = 4;

	// Database Name
	public static final String DATABASE_NAME = "YawaWeather";

			
	
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
			
		//Create a Widgets Table
		String CREATE_WIDGETS_TABLE = "CREATE TABLE " + WidgetContract.TABLE_WIDGETS + 
				"(" + WidgetContract._ID + " INTEGER PRIMARY KEY,"+ 
				WidgetContract.KEY_WIDGETS_ID+ " INTEGER, " + 
				WidgetContract.KEY_WIDGETS_COUNTRY_NAME + " TEXT, "+
				WidgetContract.KEY_WIDGETS_CITY_NAME + " TEXT, " + 
				WidgetContract.KEY_WIDGETS_LAST_TEMPERATURE + " TEXT, " +
				WidgetContract.KEY_WIDGETS_LAST_HUMEDITY + " TEXT, " +
				WidgetContract.KEY_WIDGETS_LAST_PRESURE + " TEXT, " +
				WidgetContract.KEY_WIDGETS_HIGH_TEMPERATURE + " TEXT, " +
				WidgetContract.KEY_WIDGETS_LOW_TEMPERATURE + " TEXT, " +
				WidgetContract.KEY_WIDGETS_SCALE_DATA + " TEXT, " +
				WidgetContract.KEY_WIDGETS_LAST_SKY_CONDITIONS + " TEXT, " +
				WidgetContract.KEY_WIDGETS_LAST_UPDATE_DATETIME + " TEXT, " +
				WidgetContract.KEY_WIDGETS_WIND_DEGREE + " TEXT, " +
				WidgetContract.KEY_WIDGETS_WIND_VELOCITY + " TEXT, " +
				WidgetContract.KEY_WIDGETS_WOEID + " TEXT );";
		db.execSQL(CREATE_WIDGETS_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		// Drop older tables if existed
		db.execSQL("DROP TABLE IF EXISTS " + WidgetContract.TABLE_WIDGETS);
				
		// Create tables again
		onCreate(db);
	}
	
}
