package com.yawaweather.buttonlisteners;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;

public class NegativeButtonInputLocationDialogListener implements DialogInterface.OnClickListener{

	private DialogFragment dialogFragment;
	
	public NegativeButtonInputLocationDialogListener(DialogFragment dialogFragment){
		this.dialogFragment = dialogFragment;
	}
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		this.dialogFragment.getActivity().finish();
	}

}
