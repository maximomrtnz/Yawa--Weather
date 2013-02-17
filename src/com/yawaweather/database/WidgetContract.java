package com.yawaweather.database;

import android.provider.BaseColumns;

public class WidgetContract implements BaseColumns {
	
	// Widgets Table Name
	public static final String TABLE_WIDGETS = "Widgets";
	

	// Widgets Table Columns Names
	public static final String KEY_WIDGETS_ID = "Widget_Id";
	public static final String KEY_WIDGETS_CITY_NAME = "City_Name";
	public static final String KEY_WIDGETS_COUNTRY_NAME = "Country_Name";
	public static final String KEY_WIDGETS_WOEID = "WOIED";
	public static final String KEY_WIDGETS_LAST_TEMPERATURE = "Last_Temperature";
	public static final String KEY_WIDGETS_LAST_PRESURE= "Last_Presure";
	public static final String KEY_WIDGETS_LAST_HUMEDITY = "Last_Humedity";
	public static final String KEY_WIDGETS_HIGH_TEMPERATURE = "High_Temperature";
	public static final String KEY_WIDGETS_LOW_TEMPERATURE = "Low_Temperature";
	public static final String KEY_WIDGETS_SCALE_DATA = "Scale_Data";
	public static final String KEY_WIDGETS_LAST_SKY_CONDITIONS = "Last_Sky_Conditions";
	public static final String KEY_WIDGETS_LAST_UPDATE_DATETIME = "Last_Update_Time";
	
	
}
