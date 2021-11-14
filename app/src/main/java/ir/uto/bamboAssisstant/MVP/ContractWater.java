package ir.uto.bamboAssisstant.MVP;

import android.content.Context;
import android.widget.TextView;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public interface ContractWater {


    interface view {
        // method to display progress bar
        // when next random course details
        // is being fetched
        void setWaterClick();

        void reloadWater();
        void initial();
    }


    interface Presenter {

        // method to be called when
        // the button is clicked
        void onButtonClick();

        void setWaterOnclick(
                CircularFillableLoaders circularFillableLoaders,
                Context context, TextView txt_drunk);


        void reloadWater(
                CircularFillableLoaders circularFillableLoaders,
                Context context, TextView txt_drunk);
    }
}


