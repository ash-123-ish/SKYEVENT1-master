package com.example.skyevent;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final  String channelName = "Channel";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        createChannels();

    }

    public void createChannels()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(R.color.colorPrimary);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManager().createNotificationChannel(notificationChannel);
        }
    }

    public NotificationManager getManager()
    {
        if(manager == null)
        {
            manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message)
    {
        return new NotificationCompat.Builder(getApplicationContext(), channelID).setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.ic_event);
    }
}
