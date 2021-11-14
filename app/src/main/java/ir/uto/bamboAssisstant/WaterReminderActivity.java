package ir.uto.bamboAssisstant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import ir.uto.bamboAssisstant.MVP.ContractWater;
import ir.uto.bamboAssisstant.MVP.Model;
import ir.uto.bamboAssisstant.MVP.PresenterWater;

public class WaterReminderActivity extends AppCompatActivity implements ContractWater.view {

    CircularFillableLoaders circularFillableLoaders;
    TextView txt_drunk;
    ContractWater.Presenter presenter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_reminder);

        initial();
        txt_drunk.setText("");
        circularFillableLoaders.setProgress(100);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setWaterClick();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadWater();
    }


    @Override
    public void setWaterClick() {
        presenter.setWaterOnclick(circularFillableLoaders,
                getApplicationContext(), txt_drunk);
    }

    @Override
    public void reloadWater() {
        presenter.reloadWater(circularFillableLoaders,
                getApplicationContext(), txt_drunk);
    }

    @Override
    public void initial() {
        button = findViewById(R.id.btn_drunk);
        txt_drunk = findViewById(R.id.txt_drunk);
        presenter = new PresenterWater(this, new Model(getApplicationContext()), getApplicationContext());
        circularFillableLoaders = (CircularFillableLoaders) findViewById(R.id.waterFillable);
    }
}