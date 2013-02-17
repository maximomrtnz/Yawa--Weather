package com.yawaweather.dialogs;


import java.util.ArrayList;

import com.yawaweather.main.R;
import com.yawaweather.model.Place;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;


public class LocationListDialog extends DialogFragment{
	
	private ArrayList<Place> places;
	
	public void setPlacesList(ArrayList<Place> places){
		this.places = places;
	}
	
	//Make an interface to manage the event in the parent activity
	public interface LocationListDialogListener{	
		public abstract void onLocationListDialogPositiveClick(DialogFragment dialog, int id);
		public abstract void onLocationListDialogNegativeClick(DialogFragment dialog);
	}
		   	
	// Use this instance of the interface to deliver action events
	private LocationListDialogListener mListener;
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (LocationListDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    
	    
	    
	    //Create a Charsequence List to show in dialog list
	    CharSequence[] itemList = new CharSequence[this.places.size()];
	    
	    int i = 0;
	    
	    for(Place place : this.places){
	    	itemList[i] = place.getName()+" ("+place.getCountryName()+") "+"\n"+
	    				 place.getLatitude()+"; "+place.getLongitude();
	    	i++;
	    }
	    
	    builder.setTitle(R.string.yawa_dialog_locations);
	    builder.setItems(itemList, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
               // The 'which' argument contains the index position
               // of the selected item
	        	mListener.onLocationListDialogPositiveClick(LocationListDialog.this,which);
	        }
	    });
	    
	    builder.setNegativeButton(R.string.cancel_new_widget, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Send the positive button event back to the host activity
                mListener.onLocationListDialogNegativeClick(LocationListDialog.this);
            }
        });
	    
	    return builder.create();
	}
	
}
