package com.openclassrooms.realestatemanager.repository;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;

/**
 * Created by <NIATEL BRICE> on <09/04/2021>.
 */
public class NotificationRepo {
    private static final String TAG = "NotificationUtils";
    private static final int NOTIFICATION_ID = 7;
    private static final String NOTIFICATION_TAG = "add";
    private static final CharSequence channelName = "add";
    private final Context context;

    public NotificationRepo(Context context) {
        this.context = context;
    }

    //Send notification to the device
    public void sendNotification() {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //String with notification text;
        String realEstate = context.getResources().getString(R.string.notification_text);

        //Create a Style for the Notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(realEstate);

        //Create a Channel (Android 8)
        String channelId = "fcm_default_channel";

        //Build a Notification object
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_search)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(realEstate)
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent)
                        .setStyle(inboxStyle);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Support Version >= Android 8
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(mChannel);
            } else {
                Log.e(TAG, "notification manager = null");
            }
        }

        //Show notification
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, notificationBuilder.build());
        } else {
            Log.e(TAG, "sendNotification: notification manager = null");
        }
    }
}
