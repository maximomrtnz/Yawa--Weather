package com.yawaweather.utilities;

import java.util.List;

import com.yawaweather.main.R;
import com.yawaweather.model.Place;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceAdapter extends ArrayAdapter<Place>{
	
	private Context context;
	private int layoutResourceId;
	private List<Place> places;
	
	static class ViewHolder {
	    public TextView cityName;
	    public TextView countryName;
	    public TextView latitude;
	    public TextView longitude;
	    public ImageView image;
	}
	
	public PlaceAdapter(Context context, int layoutResourceId,
			List<Place> places) {
		super(context, layoutResourceId, places);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.places = places;
	}
	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent){
		View rowView = convertView;
		
		if (rowView == null) {
		      LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      rowView = inflater.inflate(this.layoutResourceId, null);
		      ViewHolder viewHolder 	= new ViewHolder();
		      viewHolder.cityName 		= (TextView) rowView.findViewById(R.id.label_city);
		      viewHolder.countryName 	= (TextView) rowView.findViewById(R.id.label_country);
		      viewHolder.latitude 		= (TextView) rowView.findViewById(R.id.label_latitude);
		      viewHolder.longitude 		= (TextView) rowView.findViewById(R.id.label_longitude);
		      viewHolder.image 			= (ImageView) rowView.findViewById(R.id.icon);
		      rowView.setTag(viewHolder);
		 }
		
		ViewHolder holder = (ViewHolder) rowView.getTag();
	    Place place = this.places.get(position);
	    holder.cityName.setText(place.getName());
	    holder.countryName.setText(place.getCountryName());
	    holder.latitude.setText(place.getLatitude());
	    holder.longitude.setText(place.getLongitude());
	    
	    return rowView;
		
    }
	

}
