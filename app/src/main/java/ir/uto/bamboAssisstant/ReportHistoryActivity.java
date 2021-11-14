package ir.uto.bamboAssisstant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import ir.uto.bamboAssisstant.MVP.ContractHistory;
import ir.uto.bamboAssisstant.MVP.Model;
import ir.uto.bamboAssisstant.MVP.PresenterHistory;

public class ReportHistoryActivity extends AppCompatActivity implements ContractHistory.view {
    Button button;
    RecyclerView recyclerReport;
    RecyclerView recyclerPercent;
    ContractHistory.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_history);
        initial();
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClick(view);
            }
        });
    }

    @Override
    public void initial() {
        button = findViewById(R.id.btn_report);
        recyclerReport = findViewById(R.id.recycler_report);
        recyclerPercent = findViewById(R.id.recycler_percent);
        presenter = new PresenterHistory(getApplicationContext(), this, new Model(getApplicationContext()));
    }

    @Override
    public void onClick() {
        presenter.onClick(recyclerPercent, ReportHistoryActivity.this);

    }

}