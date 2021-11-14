package ir.uto.bamboAssisstant.MVP;

import android.content.Context;
import android.widget.TextView;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import ir.uto.bamboAssisstant.Util.Shared;

public class PresenterWater implements ContractWater.Presenter {
    ContractWater.view view;
    IModel model;


    public PresenterWater(ContractWater.view view, IModel model, Context Context) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onButtonClick() {

    }

    @Override
    public void setWaterOnclick( CircularFillableLoaders circularFillableLoaders, Context context, TextView txt_drunk) {
        if (model.getWaterCheck()) {
            model.updateWater();
        } else {
            model.addWater();
        }
        switch (model.getDayWater()) {
            case 0:
                circularFillableLoaders.setProgress(100);
                txt_drunk.setText("شما امروز آب نخوردید");
                break;
            case 1:
                circularFillableLoaders.setProgress(87);
                txt_drunk.setText("شما امروز 1 لیوان اب خوردید");
                Shared.startAlarm(context);
                break;
            case 2:
                circularFillableLoaders.setProgress(75);
                txt_drunk.setText("شما امروز 2 لیوان اب خوردید");

                break;
            case 3:
                circularFillableLoaders.setProgress(63);
                txt_drunk.setText("شما امروز 3 لیوان اب خوردید");

                break;
            case 4:
                circularFillableLoaders.setProgress(50);
                txt_drunk.setText("شما امروز 4 لیوان اب خوردید");

                break;
            case 5:
                circularFillableLoaders.setProgress(38);
                txt_drunk.setText("شما امروز 5 لیوان اب خوردید");

                break;
            case 6:
                circularFillableLoaders.setProgress(25);
                txt_drunk.setText("شما امروز 6 لیوان اب خوردید");

                break;
            case 7:
                circularFillableLoaders.setProgress(13);
                txt_drunk.setText("شما امروز 7 لیوان اب خوردید");

                break;
            case 8:
                circularFillableLoaders.setProgress(0);
                txt_drunk.setText("تبریک! شما امروز 8 لیوان آب خوردید");
                Shared.stopAlarm();
                break;
            default:
                circularFillableLoaders.setProgress(0);
                txt_drunk.setText("تبریک! شما امروز 8 لیوان آب خوردید");

                break;

        }

    }

    @Override
    public void reloadWater(CircularFillableLoaders circularFillableLoaders, Context context, TextView txt_drunk) {
        switch (model.getDayWater()) {
            case 0:
                circularFillableLoaders.setProgress(100);
                txt_drunk.setText("شما امروز آب نخوردید");
                break;
            case 1:
                circularFillableLoaders.setProgress(87);
                txt_drunk.setText("شما امروز 1 لیوان اب خوردید");
                break;
            case 2:
                circularFillableLoaders.setProgress(75);
                txt_drunk.setText("شما امروز 2 لیوان اب خوردید");

                break;
            case 3:
                circularFillableLoaders.setProgress(63);
                txt_drunk.setText("شما امروز 3 لیوان اب خوردید");

                break;
            case 4:
                circularFillableLoaders.setProgress(50);
                txt_drunk.setText("شما امروز 4 لیوان اب خوردید");

                break;
            case 5:
                circularFillableLoaders.setProgress(38);
                txt_drunk.setText("شما امروز 5 لیوان اب خوردید");

                break;
            case 6:
                circularFillableLoaders.setProgress(25);
                txt_drunk.setText("شما امروز 6 لیوان اب خوردید");

                break;
            case 7:
                circularFillableLoaders.setProgress(13);
                txt_drunk.setText("شما امروز 7 لیوان اب خوردید");

                break;
            case 8:
                circularFillableLoaders.setProgress(0);
                txt_drunk.setText("تبریک! شما امروز 8 لیوان آب خوردید");

                break;
            default:
                circularFillableLoaders.setProgress(0);
                txt_drunk.setText("تبریک! شما امروز 8 لیوان آب خوردید");

                break;

        }
    }
}



