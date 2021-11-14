package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface ContractRoutine {

    interface view {
        void onFabClicked(Activity activity,RecyclerView recyclerView);

        void initial(View view);
    }



    interface Presenter {

        void clickFab(Activity activity,RecyclerView recyclerView);

        void onResume(RecyclerView recyclerView);



    }
}
