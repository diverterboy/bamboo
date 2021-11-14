package ir.uto.bamboAssisstant.MVP;

import android.content.Context;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.List;

import ir.uto.bamboAssisstant.database.RecordDbAdapter;
import ir.uto.bamboAssisstant.database.RoutineDbAdapter;
import ir.uto.bamboAssisstant.database.WaterDbAdapter;
import ir.uto.bamboAssisstant.modele.MyRoutine;
import ir.uto.bamboAssisstant.modele.Records;

public class Model implements IModel {
    Context context;
    RoutineDbAdapter routineDbAdapter;
    RecordDbAdapter recordDbAdapter;
    WaterDbAdapter waterDbAdapter;
    PersianCalendar calendar;
    private static final int SPLASH_SCREEN_TIME_OUT = 2300;

    public Model(Context context) {
        this.context = context;
        calendar = new PersianCalendar();
        routineDbAdapter = new RoutineDbAdapter(context);
        waterDbAdapter = new WaterDbAdapter(context);
        recordDbAdapter = new RecordDbAdapter(context);


    }

    @Override
    public List<Records> getDayRecords() {
        return recordDbAdapter.getDayRecord(calendar.getPersianShortDate());

    }

    @Override
    public String getEachPercent(String title) {
        return recordDbAdapter.getEachPercent(title, calendar.getPersianShortDate());

    }

    @Override
    public List<MyRoutine> MY_ROUTINES() {
        return routineDbAdapter.getAllRoutines();

    }

    @Override
    public void getSplashInt(OnSplashListener onSplashListener) {
        onSplashListener.onSplash(SPLASH_SCREEN_TIME_OUT);
    }
    @Override
    public boolean getWaterCheck() {
        return waterDbAdapter.checkWater(calendar.getPersianShortDate());
    }

    @Override
    public void updateWater() {
        waterDbAdapter.updateWater(calendar.getPersianShortDate());

    }

    @Override
    public void addWater() {
        waterDbAdapter.addWater(calendar.getPersianShortDate());

    }

    @Override
    public int getDayWater() {
        return waterDbAdapter.getDayWater(calendar.getPersianShortDate());
    }
}
