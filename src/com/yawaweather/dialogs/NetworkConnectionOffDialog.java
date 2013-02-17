package com.yawaweather.dialogs;


import com.yawaweather.main.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class NetworkConnectionOffDialog extends DialogFragment{
	
	//Make an interface to manage the event in the parent activity
	public interface NetworkConnectionOffListener{	
		public abstract void onNetworkConnectionOffDialogPositiveClick(DialogFragment dialog);
	}
	
	// Use this instance of the interface to deliver action events
	private NetworkConnectionOffListener mListener;
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.mListener = (NetworkConnectionOffListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		//Instantiate an AlertDialog.Builder with its constructor
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		//Chain together various setter methods to set the dialog characteristics
		builder.setMessage(R.string.yawa_dialog_network_connection_off_message).setTitle(R.string.yawa_dialog_network_connection_off_title);
		
		//Set the only button
		builder.setPositiveButton(R.string.ok_new_widget,  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Send the positive button event back to the host activity
                mListener.onNetworkConnectionOffDialogPositiveClick(NetworkConnectionOffDialog.this);
            }
        });

		// 3. Get the AlertDialog from create()
		return builder.create();
    }

}
