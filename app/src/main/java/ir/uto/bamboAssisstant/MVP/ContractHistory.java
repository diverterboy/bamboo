package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

public interface ContractHistory {

    interface view {

        void initial();
        void onClick();
    }


    interface Presenter {

        void onClick(RecyclerView recyclerPercent, Activity activity);




    }
}
