package ir.uto.bamboAssisstant;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import ir.uto.bamboAssisstant.MVP.ContractSplash;
import ir.uto.bamboAssisstant.MVP.Model;
import ir.uto.bamboAssisstant.MVP.PresenterSplash;

public class SplashActivity extends AppCompatActivity implements ContractSplash.view {
    CircularFillableLoaders circularFillableLoaders;
    ContractSplash.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        circularFillableLoaders = (CircularFillableLoaders) findViewById(R.id.yourCircularFillableLoaders);

        presenter = new PresenterSplash(this, new Model(getApplicationContext()), this);
        presenter.onCreateSplash(this);


    }


    @Override
    public void setLoader() {
        // Set Progress
        circularFillableLoaders.setProgress(65);
// Set Wave and Border Color
// Set Border Widt
        circularFillableLoaders.setBorderWidth(10 * getResources().getDisplayMetrics().density);
// Set Wave Amplitude (between 0.00f and 0.10f)
        circularFillableLoaders.setAmplitudeRatio(0.1f);

    }

    @Override
    public void onDes() {
        finish();
    }

}
