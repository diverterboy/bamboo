package ir.uto.bamboAssisstant.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ir.uto.bamboAssisstant.FirstActivity;
import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.Util.Shared;
import ir.uto.bamboAssisstant.database.RecordDbAdapter;
import ir.uto.bamboAssisstant.modele.Records;
import timerx.Stopwatch;
import timerx.StopwatchBuilder;

public class StopWatchService extends Service {
    private long seconds = 0;
    // Is the stopwatch running?
    private boolean running;
    Stopwatch stopwatch;
    private boolean wasRunning;
    Records records;
    RecordDbAdapter recordDbAdapter;
    PersianCalendar calendar;
    private String CHANNEL_ID = "channelId";
    private NotificationManager notifManager;
    //  SharedPreferences preferences;
    //SharedPreferences.Editor editor;
    int i;
    int orderedTime;
    RemoteViews collapsedView;

    @Override
    public void onCreate() {
        super.onCreate();

        running = true;
        records = new Records();
        recordDbAdapter = new RecordDbAdapter(getApplicationContext());
        calendar = new PersianCalendar();
        stopwatch = new StopwatchBuilder().startFormat("MM:SS")
                .changeFormatWhen(1, TimeUnit.HOURS, "HH:MM:SS")
                .build();
        collapsedView = new RemoteViews(getPackageName(),
                R.layout.javanotification_collapsed);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intent.getAction();
        if (intent.getAction().equals("stopService")) {
            onDestroy();
        } else {
            records.setTitle(intent.getExtras().getString("title"));
            orderedTime = (intent.getExtras().getInt("hour") / 60) + intent.getExtras().getInt("min");
            PersianCalendar persianCalendar = new PersianCalendar();
            records.setRecordsDate(persianCalendar.getPersianShortDate());
            Shared.preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Shared.editor = Shared.preferences.edit();
            run();
            running = true;
            records.setId(intent.getExtras().getInt("id"));

        }

        return super.onStartCommand(intent, flags, startId);


    }


    public void run() {


        stopwatch.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "Service Channel";
            String offerChannelDescription = "LoggerChannel";
            int offerChannelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notifChannel = new NotificationChannel(CHANNEL_ID, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(notifChannel);
        }

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                seconds = stopwatch.getTimeIn(TimeUnit.SECONDS);
                long hours = seconds / 3600;
                long minutes = (seconds % 3600) / 60;
                long secs = seconds % 60;

                Intent stopIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
                stopIntent.setAction("stopService");
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
                PendingIntent intentP = PendingIntent.getActivity(getApplicationContext(), 0
                        , intent, PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent stopPintent = PendingIntent.getBroadcast(getApplicationContext(), 1, stopIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                String Time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

            //    collapsedView.setTextViewText(R.id.text_view_collapsed_1, Time);
              //  collapsedView.setOnClickPendingIntent(R.id.text_view_collapsed_2, stopPintent);
                NotificationCompat.Builder sNotifBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_timer_24)
                        .setContentTitle(records.getTitle())
                        .setContentText(Time)
                   //     .setCustomBigContentView(collapsedView)
                        .addAction(R.drawable.ic_baseline_menu_open_24, "برو به نرم افزار", intentP)
                        .addAction(R.drawable.ic_baseline_menu_open_24, "توقف", stopPintent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bamboo));

                Notification servNotification = sNotifBuilder.build();
                i = Shared.preferences.getInt(records.getTitle(), -1) + 1;
                Shared.editor.putInt(records.getTitle(), i);
                Shared.editor.apply();
                startForeground(1, servNotification);
                handler.postDelayed(this, 1000);
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        stopwatch.stop();
        seconds = stopwatch.getTimeIn(TimeUnit.SECONDS);

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        String Time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

        records.setStringTime(Time);
        records.setRecordedTime(seconds);
        if (recordDbAdapter.checkRecord(records.getTitle(), calendar.getPersianShortDate())) {
            Boolean res = recordDbAdapter.updateRecords(records);

            if (res) {
                Toast.makeText(getApplicationContext(), "زمان با موفقیت آپدیت شد", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(getApplicationContext(), R.string.Error, Toast.LENGTH_LONG).show();
            }

        } else {
            long result = recordDbAdapter.addRecord(records);

            if (result > 0) {
                Toast.makeText(getApplicationContext(), R.string.RoutineAdded, Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(getApplicationContext(), R.string.Error, Toast.LENGTH_LONG).show();

            }
        }

        stopSelf();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
