package com.yawaweather.dialogs;

import com.yawaweather.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class InputTemperatureScaleDialog extends DialogFragment{
	//Make an interface to manage the event in the parent activity
	public interface InputTemperatureScaleDialogListener{	
		public abstract void onInputTemperatureScaleDialogClickItem(DialogFragment dialog,int checked);
		public abstract void onInputTemperatureScaleDialogNegativeClick(DialogFragment dialog);
	}
   	
	// Use this instance of the interface to deliver action events
	private InputTemperatureScaleDialogListener mListener;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Set the text with the current time.
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 // Set the dialog title
		 builder.setTitle(R.string.preference_scale_pressure)
		 // Specify the list array, the items to be selected by default (null for none),  
		 // and the listener through which to receive callbacks when items are selected
		 .setNegativeButton(R.string.cancel_new_widget, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
            	   mListener.onInputTemperatureScaleDialogNegativeClick(InputTemperatureScaleDialog.this);
               }
           })
           .setSingleChoiceItems(R.array.yawa_weather_temperature_scales,0,new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					mListener.onInputTemperatureScaleDialogClickItem(InputTemperatureScaleDialog.this, which);
				}
		});
           
		 

		 
		    return builder.create();
		
	}
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.mListener = (InputTemperatureScaleDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement InputLocationDialogListener");
        }
    }		
	
}
