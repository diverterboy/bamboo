package ir.uto.bamboAssisstant.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import ir.uto.bamboAssisstant.services.AlarmReceiver;

public class Shared {
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    static AlarmManager am;
    static PendingIntent pi;

    public Shared() {
        int s = preferences.getInt("1", -1);

    }

    public static void startAlarm(Context context) {

        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 55, pi);
        Toast.makeText(context, "started", Toast.LENGTH_SHORT).show();

    }

    public static void stopAlarm() {

        am.cancel(pi);

    }

}
