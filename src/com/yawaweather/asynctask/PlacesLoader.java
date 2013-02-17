package com.yawaweather.asynctask;

import java.util.ArrayList;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yawaweather.json.GetJsonFromWebServices;
import com.yawaweather.model.Place;
import com.yawaweather.utilities.PlacesSetter;

import android.os.AsyncTask;
import android.util.Log;

public class PlacesLoader extends AsyncTask<Object, Void, ArrayList<Place>> {
	
	private PlacesSetter callback;
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected ArrayList<Place> doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		//In this method we call the yahoo web services to get the woid 
		//(or woid list) of input location
		
		String location = String.valueOf(params[0]);
		
		//Set callback
		this.callback = (PlacesSetter)params[1];
		
		location = location.replace(" ", "%20");
		
		String url = "http://query.yahooapis.com/v1/public/yql?q=select*from%20geo.places%20where%20text=%22"+location+"%22&format=json";
		
		ArrayList<Place> placesArray = new ArrayList<Place>();
		
        GetJsonFromWebServices getJsonFromWebServices = new GetJsonFromWebServices();
        
        try {
        	
			//Get the Root Yahoo Weather JSON OBject
        	JSONObject jObject = new JSONObject(getJsonFromWebServices.readJSON(url));
			
        	//Get the Query Objects
			JSONObject query = jObject.getJSONObject("query");

			//Get the number of place that the request get
			int count = query.getInt("count");
			
			JSONObject result = query.getJSONObject("results");
			
			Object places = null;
			
			//if count ==1 result has an object call places
			if(count==1){
				places = result.getJSONObject("place");
				
				//Get country information
				JSONObject country = ((JSONObject)places).getJSONObject("country");
				
				//Get geoposition information
				JSONObject geoPosition = ((JSONObject)places).getJSONObject("centroid");
				
				Place place = new Place();
				
				place.setCountryName(country.getString("content"));
				place.setName(((JSONObject)places).getString("name"));
				place.setWoeid(((JSONObject)places).getString("woeid"));
				place.setLongitude(geoPosition.getString("longitude"));
				place.setLatitude(geoPosition.getString("latitude"));
				
				placesArray.add(place);
				
			}else if(count>1){
			
				places = result.getJSONArray("place");
				
				for(int i = 0;i<((JSONArray)places).length();i++){
					JSONObject jsonPlace = ((JSONArray)places).getJSONObject(i);
					
					//Get Country Object
					JSONObject country = jsonPlace.getJSONObject("country");
					
					//Get geoposition information
					JSONObject geoPosition = jsonPlace.getJSONObject("centroid");
					
					Place place = new Place();
					place.setCountryName(country.getString("content"));
					place.setName(jsonPlace.getString("name"));
					place.setWoeid(jsonPlace.getString("woeid"));
					place.setLongitude(geoPosition.getString("longitude"));
					place.setLatitude(geoPosition.getString("latitude"));
					
					placesArray.add(place);
					
				}
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("Error", e.toString());
		}
        
        return placesArray;
		
	}
	
	@Override
	protected void onPostExecute(ArrayList<Place> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		//Notify View
		this.callback.setPlaces(result);
	
	}

	

	
}
