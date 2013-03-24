package com.yawaweather.utilities;

import com.yawaweather.main.Main;


import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;
import android.app.NotificationManager;
import android.app.PendingIntent;


public class NotificationsManager {

	private static NotificationsManager instance;

	public static synchronized NotificationsManager getInstance() {
		if (instance == null) {
			instance = new NotificationsManager();
		}
		return instance;
	}
	
	private NotificationsManager(){}
	
	public void statusBarNotification(Context context,int titleId, int textId) {
		
		Resources resources = context.getResources();
		
		
		//Show a Status Bar Notification to notify something to user
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(context, Main.class);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				context).setSmallIcon(R.drawable.ic_dialog_alert)
				.setContentTitle(resources.getString(titleId))
				.setContentText(resources.getString(titleId));
		
		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(Main.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		
		mNotificationManager.notify(0, mBuilder.build());
	}
	
	
	public void toastNotification(Context context, int messageId){
		Resources resources = context.getResources();
		
		//Show simple toast notification
		CharSequence text = resources.getString(messageId);
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
