package com.yawaweather.dialogs;

import com.yawaweather.buttonlisteners.NegativeButtonInputLocationDialogListener;
import com.yawaweather.buttonlisteners.PositiveButtonInputLocationDialogListener;
import com.yawaweather.dialogs.NetworkConnectionOffDialog.NetworkConnectionOffListener;
import com.yawaweather.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class LoadingDataDialog extends DialogFragment{
		
	//Make an interface to manage the event in the parent activity
		public interface LoadingDataDialogListener{	
			public abstract void onLoadingDataDialogPositiveClick(DialogFragment dialog);
		}
		
		// Use this instance of the interface to deliver action events
		private LoadingDataDialogListener mListener;
	   	
		// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        // Verify that the host activity implements the callback interface
	        try {
	            // Instantiate the NoticeDialogListener so we can send events to the host
	            this.mListener = (LoadingDataDialogListener) activity;
	        } catch (ClassCastException e) {
	            // The activity doesn't implement the interface, throw exception
	            throw new ClassCastException(activity.toString()
	                    + " must implement LoadingDataDialogListener");
	        }
	    }
		
		
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        
	        // Get the layout inflater
	        LayoutInflater inflater = getActivity().getLayoutInflater();
	        
	        View view = inflater.inflate(R.layout.fragment_loading_dialog,null);
	      
	        // Pass null as the parent view because its going in the dialog layout
	        builder.setView(view);
	        
	        //Set the only button
			builder.setPositiveButton(R.string.cancel_new_widget, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                // Send the positive button event back to the host activity
	                mListener.onLoadingDataDialogPositiveClick(LoadingDataDialog.this);
	            }
	        });
	        
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	    
	    

}