package com.yawaweather.utilities;


import com.yawaweather.main.R;

public class WeatherImageManager {
	
	private static WeatherImageManager instance;

	public static synchronized WeatherImageManager getInstance(){
		if(instance == null){
			instance = new WeatherImageManager();
		}
		return instance;
	}
	
	
	public int getImageId(int weatherConditionId){

		switch (weatherConditionId) {
			case 3:
				return R.drawable.ic_storm;
			case 4:
				return R.drawable.ic_storm;
			case 5:
				return R.drawable.ic_showers_scattered;
			case 6:
				return R.drawable.ic_showers_scattered;
			case 7:
				return R.drawable.ic_snow;
			case 8:
				return R.drawable.ic_showers_scattered;
			case 9:
				return R.drawable.ic_showers_scattered;
			case 10:
				return R.drawable.ic_showers_scattered;
			case 11:
				return R.drawable.ic_showers;	
			case 12:
				return R.drawable.ic_showers;
			case 13:
				return R.drawable.ic_snow;		
			case 14:
				return R.drawable.ic_snow;
			case 15:
				return R.drawable.ic_snow;
			case 16:
				return R.drawable.ic_snow;
			case 17:
				return R.drawable.ic_severe_alert;
			case 18:
				return R.drawable.ic_snow;	
			case 20:
				return R.drawable.ic_fog;
			case 21:
				return R.drawable.ic_fog;
			case 22:
				return R.drawable.ic_fog;
			case 23:
				return R.drawable.ic_severe_alert;
			case 24:
				return R.drawable.ic_few_clouds;
			case 25:
				return R.drawable.ic_few_clouds;
			case 26:
				return R.drawable.ic_overcast;
			case 27:
				return R.drawable.ic_few_clouds_night;
			case 28:
				return R.drawable.ic_few_clouds;	
			case 29:
				return R.drawable.ic_few_clouds_night;
			case 30:
				return R.drawable.ic_few_clouds;	
			case 31:
				return R.drawable.ic_night;
			case 32:
				return R.drawable.ic_sunny;	
			case 33:
				return R.drawable.ic_night;
			case 34:
				return R.drawable.ic_sunny;	
			case 35:
				return R.drawable.ic_showers_scattered;
			case 36:
				return R.drawable.ic_sunny;
			case 37:
				return R.drawable.ic_storm;
			case 38:
				return R.drawable.ic_storm;
			case 39:
				return R.drawable.ic_storm;
			case 40:
				return R.drawable.ic_showers_scattered;
			case 41:
				return R.drawable.ic_snow;
			case 42:
				return R.drawable.ic_snow;
			case 43:
				return R.drawable.ic_snow;
			case 44:
				return R.drawable.ic_few_clouds;	
			case 45:
				return R.drawable.ic_storm;
			case 46:
				return R.drawable.ic_snow;
			case 47:
				return R.drawable.ic_snow;
			default:
				return R.drawable.ic_sunny;
				
		}
		
	}

}
