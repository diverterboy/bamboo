package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.uto.bamboAssisstant.Adapter.PercentAdapter;
import ir.uto.bamboAssisstant.Util.ColorUtil;
import ir.uto.bamboAssisstant.modele.Records;

public class PresenterReport implements ContractReport.presenter {
    ContractReport.view view;
    IModel model;
    PieChart pieChart;
    Context context;

        public PresenterReport(Context context, PieChart pieChart, ContractReport.view view, IModel model) {
        this.model = model;
        this.view = view;
        this.pieChart = pieChart;
        this.context = context;
    }

    @Override
    public void initPieChart() {
        //using percentage as values instead of amount
        pieChart.setUsePercentValues(true);
        //remove the description label on the lower left corner, default true if not set
        pieChart.getDescription().setEnabled(false);

        //enabling the user to rotate the chart, default true
        pieChart.setRotationEnabled(true);
        //adding friction when rotating the pie chart
        pieChart.setDragDecelerationFrictionCoef(0.9f);
        //setting the first entry start from right hand side, default starting from top
        pieChart.setRotationAngle(0);

        //highlight the entry when it is tapped, default true if not set
        pieChart.setHighlightPerTapEnabled(true);
        //adding animation so the entries pop up from 0 degree
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    @Override
    public void showPieChart() {
        Records records = new Records();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        Map<String, Integer> typeAmountMap = new HashMap<>();

        //initializing data
        for (int i = 0; i < model.getDayRecords().size(); i++) {
            records = model.getDayRecords().get(i);
            typeAmountMap.put(records.getTitle(), (int) records.getRecordedTime());

        }

        for (String type : typeAmountMap.keySet()) {
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }
        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        //setting text size of the value
        pieDataSet.setValueTextSize(9f);

        //providing color list for coloring different entries
        pieDataSet.setColors(ColorUtil.getColorsRes());

        List<Integer> d = pieDataSet.getColors();


        /*ReportAdapter reportAdapter = new ReportAdapter(getContext(), recordsList,pieDataSet);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        recyclerReport.setAdapter(reportAdapter);
        recyclerReport.setLayoutManager(manager);*/

        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);

        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
       /* pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(80f);*/
        pieChart.setDrawSliceText(false);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void checkUsePieChart() {

        if (model.getDayRecords() != null) {
            initPieChart();
            showPieChart();

            pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry entry, Highlight highlight) {
                    PieEntry entry1 = (PieEntry) entry;
                    String title = "";
                    String result = "";
                    title = entry1.getLabel();
                    result = model.getEachPercent(title);

                    title = new StringBuilder().append(title).append("\n").append(result).append("%").append("\n")
                            .toString();
                    pieChart.setCenterText(title);
                }

                @Override
                public void onNothingSelected() {

                }
            });
         /*   ReportAdapter reportAdapter = new ReportAdapter(getContext(), recordsList);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
            recyclerReport.setAdapter(reportAdapter);
            recyclerReport.setLayoutManager(manager);*/
        } else {
            Toast.makeText(context, "شما اغمروز روتینی را استارت نکردید", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void usePercentRecycler(RecyclerView recyclerPercent) {
        if (model.getDayRecords() != null) {
            recyclerPercent.setAdapter(new PercentAdapter(context, model.getDayRecords()));
            recyclerPercent.setLayoutManager(new LinearLayoutManager(context,
                    RecyclerView.VERTICAL, false));
        }
    }

    @Override
    public void configReportFragment(TextView txt_percent, Activity activity) {

        if (model.getDayRecords().size() > 0) {
            txt_percent.setText("مقدار فعالیت شما:");
        } else if (model.getDayRecords().size()==0) {
            Snackbar.make(activity.findViewById(android.R.id.content)
                    , "شما هنوز برای امروز فعالیتی رو به پایان نرسوندید", BaseTransientBottomBar.LENGTH_LONG).show();
            txt_percent.setText("لطفا اول از قسمت روتین ها فعالیت خود را شروع کنید تا گزارشتون حاضر بشه");
        }
    }


}
