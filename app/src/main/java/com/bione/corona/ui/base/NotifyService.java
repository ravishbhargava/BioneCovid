package com.bione.corona.ui.base;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.bione.corona.R;

public class NotifyService extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";

    public NotifyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Intent notificationIntent = new Intent(getApplicationContext(), BaseActivity.class);
        notificationIntent.putExtra("fromNotification", true);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), default_notification_channel_id);
        mBuilder.setContentTitle("My Notification");
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContentText("Notification Listener Service Example");
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new
                    NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
        throw new UnsupportedOperationException("Not yet implemented");
    }

//   @Override
//   public void onCreate() {
//      super.onCreate();
//      NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//      Notification notification = new Notification(R.drawable.notification_icon, "Notify Alarm strart", System.currentTimeMillis());
//      Intent myIntent = new Intent(this , MyActivity.class);
//      PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
//      notification.setLatestEventInfo(this, "Notify label", "Notify text", contentIntent);
//      mNM.notify(NOTIFICATION, notification);
//   }
}