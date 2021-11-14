package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public interface ContractReport {
    interface view {
        void initial(View view);
        void usePercentRecycler();
        void usePieChart();
        void configReportFragment();

    }

    interface presenter {

        void initPieChart();

        void showPieChart();

        void checkUsePieChart();

        void usePercentRecycler(RecyclerView recyclerPercent);
        void configReportFragment(TextView txt_percent, Activity activity);

    }


}
