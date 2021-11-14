package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.uto.bamboAssisstant.Adapter.RecyclerRoutineAdapter;
import ir.uto.bamboAssisstant.Util.CustomDialogClass;
import ir.uto.bamboAssisstant.Util.IRecycler;
import ir.uto.bamboAssisstant.modele.MyRoutine;
import ir.uto.bamboAssisstant.services.StopWatchService;

public class PresenterRoutine implements ContractRoutine.Presenter {
    IModel model;
    ContractRoutine.view view;
    Context context;

    public PresenterRoutine(Context context, IModel model, ContractRoutine.view view) {
        this.model = model;
        this.view = view;
        this.context = context;
    }

    @Override
    public void clickFab(Activity activity, RecyclerView recyclerView) {

        CustomDialogClass customDialogClass = new CustomDialogClass();
        customDialogClass.showAddRoutineDialog(activity, new IRecycler() {
            @Override
            public void hi() {
                onResume(recyclerView);
            }
        });
    }

    @Override
    public void onResume(RecyclerView recyclerView) {
        List<MyRoutine> myRoutineList = model.MY_ROUTINES();
        List<StopWatchService> stopService = new ArrayList<>();
        List<Boolean> stStop = new ArrayList<>();

        for (int i = 0; myRoutineList.size() > i; i++) {
            stopService.add(new StopWatchService());
            stStop.add(new Boolean(false));
        }

        RecyclerRoutineAdapter adapter = new RecyclerRoutineAdapter(context, myRoutineList, stopService, stStop);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,
                false));

        ((RecyclerRoutineAdapter) recyclerView.getAdapter()).refreshList(model.MY_ROUTINES());
    }


}
