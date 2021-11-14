package ir.uto.bamboAssisstant.MVP;

import android.content.Context;

public interface ContractSplash {

    interface view {
        // method to display progress bar
        // when next random course details
        // is being fetched
        void setLoader();


        void onDes();

    }

    interface Presenter {

        // method to be called when
        // the button is clicked
        void onButtonClick();

        // method to destroy
        // lifecycle of MainActivity

        void onCreateSplash(Context context);

    }
}
