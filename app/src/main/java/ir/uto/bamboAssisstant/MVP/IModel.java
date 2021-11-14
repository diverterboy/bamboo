package ir.uto.bamboAssisstant.MVP;

import java.util.List;

import ir.uto.bamboAssisstant.modele.MyRoutine;
import ir.uto.bamboAssisstant.modele.Records;

public interface IModel {
    //report
    List<Records> getDayRecords();

    String getEachPercent(String title);
    //report

    //routine
    List<MyRoutine> MY_ROUTINES();
//routine

    //splash
    interface OnSplashListener {
        // function to be called
        // once the Handler of Model class
        // completes its execution
        void onSplash(int SPLASH_SCREEN_TIME_OUT);

    }

    interface getAllRoutines {


        void getAllRoutine(List<Records> recordsList);

    }

    void getSplashInt(final Model.OnSplashListener onSplashListener);


//splash

    //water
    boolean getWaterCheck();

    void updateWater();

    void addWater();

    int getDayWater();
    //water

}
