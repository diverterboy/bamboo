package ir.uto.bamboAssisstant.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.RemoteViews;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.WaterReminderActivity;
import ir.uto.bamboAssisstant.database.WaterDbAdapter;

/**
 * Implementation of App Widget functionality.
 */
public class WaterWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        PersianCalendar calendar = new PersianCalendar();
        WaterDbAdapter waterDbAdapter = new WaterDbAdapter(context);

        String widgetText = "امروز" + " " + String.valueOf(waterDbAdapter.getDayWater(calendar.getPersianShortDate()))
                .concat(" لیوان آب خوردید");
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.water_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        Intent intent = new Intent(context, WaterReminderActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        }, 10000);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}