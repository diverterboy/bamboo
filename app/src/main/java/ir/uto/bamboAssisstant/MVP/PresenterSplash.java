package ir.uto.bamboAssisstant.MVP;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import ir.uto.bamboAssisstant.FirstActivity;

public class PresenterSplash implements ContractSplash.Presenter {
    ContractSplash.view view;
    IModel model;

    public PresenterSplash(ContractSplash.view view, IModel model, Context viewContext) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void onCreateSplash(Context context) {
        view.setLoader();

        model.getSplashInt(new IModel.OnSplashListener() {
            @Override
            public void onSplash(int SPLASH_SCREEN_TIME_OUT) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(context,
                                FirstActivity.class);
                        //Intent is used to switch from one activity to another.
                        context.startActivity(i);
                        //invoke the SecondActivity.
                        view.onDes();
                        //the current activity will get finished.
                    }
                }, SPLASH_SCREEN_TIME_OUT);
            }
        });
    }




}
