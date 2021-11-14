package ir.uto.bamboAssisstant.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.WaterReminderActivity;

public class AlarmReceiver extends BroadcastReceiver {
    private String CHANNEL_ID = "channelId";
    private NotificationManager notifManager;
    NotificationManager notificationManager;

    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("ww","wwww");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "Service Channel";
            String offerChannelDescription = "LoggerChannel";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifChannel = new NotificationChannel("CHANNEL_ID", offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            notifManager = context.getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(notifChannel);

        }
        Intent notifyIntent = new Intent(context, WaterReminderActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder sNotifBuilder = new NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_baseline_timer_24)
                .setContentTitle(" وقت آب خوردنه")
                .setContentText(" یک لیوان اب بنوشید و روی این نوتیفیکیشن کلیک کنید")
                .setVibrate(new long[]{1000, 1000, 1000})
                .setContentIntent(notifyPendingIntent);


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, sNotifBuilder.build());


    }



}
