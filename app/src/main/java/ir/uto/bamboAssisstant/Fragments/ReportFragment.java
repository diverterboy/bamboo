package ir.uto.bamboAssisstant.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;

import ir.uto.bamboAssisstant.MVP.ContractReport;
import ir.uto.bamboAssisstant.MVP.Model;
import ir.uto.bamboAssisstant.MVP.PresenterReport;
import ir.uto.bamboAssisstant.R;


public class ReportFragment extends Fragment implements ContractReport.view {

    PieChart pieChart;
    RecyclerView recyclerReport;
    RecyclerView recyclerPercent;
    TextView txt_percent;
    ContractReport.presenter presenter;

    public ReportFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        initial(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        configReportFragment();

        usePieChart();

        usePercentRecycler();


    }


    @Override
    public void initial(View view) {
        pieChart = view.findViewById(R.id.pieChart_view);
        recyclerReport = view.findViewById(R.id.recycler_report);
        recyclerPercent = view.findViewById(R.id.recycler_percent);
        txt_percent = view.findViewById(R.id.txt_percent);
        presenter = new PresenterReport(getContext(), pieChart, this, new Model(getContext()));
    }

    @Override
    public void usePercentRecycler() {
        presenter.usePercentRecycler(recyclerPercent);
    }

    @Override
    public void usePieChart() {
        presenter.checkUsePieChart();
    }

    @Override
    public void configReportFragment() {
        presenter.configReportFragment(txt_percent,getActivity());
    }
}