package com.yawaweather.dialogs;

import java.util.ArrayList;

import com.yawaweather.main.R;
import com.yawaweather.model.Place;
import com.yawaweather.utilities.PlaceAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LocationListView extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();

		ArrayList<Place> places = extras.getParcelableArrayList("places");
		
		PlaceAdapter adapter = new PlaceAdapter(this, R.layout.row_location_layout, places);
		
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Place place = (Place) getListAdapter().getItem(position);
		
		//Return place to call activity
		Intent intent = new Intent();
	    intent.putExtra("place", place);
	    setResult(RESULT_OK, intent);
	    finish();
	}
}
