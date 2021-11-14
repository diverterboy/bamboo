package ir.uto.bamboAssisstant.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import ir.uto.bamboAssisstant.MVP.ContractRoutine;
import ir.uto.bamboAssisstant.MVP.Model;
import ir.uto.bamboAssisstant.MVP.PresenterRoutine;
import ir.uto.bamboAssisstant.R;


public class MyRoutineFragment extends Fragment implements ContractRoutine.view {

    RecyclerView recyclerView;
    ContractRoutine.Presenter presenter;
    ExtendedFloatingActionButton btn_fab;

    public MyRoutineFragment() {
        // Required empty public constructor
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_routine, container, false);

        initial(view);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFabClicked(getActivity(), recyclerView);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume(recyclerView);
    }

    @Override
    public void onFabClicked(Activity activity, RecyclerView recyclerView) {
        presenter.clickFab(activity, recyclerView);
    }

    @Override
    public void initial(View view) {
        recyclerView = view.findViewById(R.id.recycler_routine);
        presenter = new PresenterRoutine(getContext(), new Model(getContext()), this);
        btn_fab = view.findViewById(R.id.btn_fab);
    }
}