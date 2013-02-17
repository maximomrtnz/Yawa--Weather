package com.yawaweather.buttonlisteners;

import java.util.Observable;

import com.yawaweather.asynctask.PlacesLoader;

import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.EditText;
import android.content.DialogInterface;

public class PositiveButtonInputLocationDialogListener extends Observable implements DialogInterface.OnClickListener{
	
	private DialogFragment dialogFragment;
	private EditText location;
	
	public PositiveButtonInputLocationDialogListener(DialogFragment dialogFragment,EditText location){
		this.dialogFragment = dialogFragment;
		this.location = location;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
		//Call an Asynchronous Task to load the Information About Places from the Web
		PlacesLoader placesLoader = new PlacesLoader();
		placesLoader.execute(this.location.getText().toString());
		
		//Notify UI to show Progress Bar here we use Observer Pattern
		this.setChanged();
		
		this.notifyObservers();
		
	}

}
