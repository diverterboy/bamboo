package ir.uto.bamboAssisstant.MVP;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import ir.uto.bamboAssisstant.Adapter.PercentAdapter;

public class PresenterHistory implements ContractHistory.Presenter {
    Context context;
    ContractHistory.view view;
    IModel model;
    PersianCalendar persianCalendar;

    public PresenterHistory(Context context, ContractHistory.view view, IModel model) {
        this.context = context;
        this.model = model;
        this.view = view;
        persianCalendar = new PersianCalendar();

    }

    @Override
    public void onClick(RecyclerView recyclerPercent, Activity activity) {
        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                                                                   @Override
                                                                   public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                                                       String mMonth, mDAY;
                                                                       mDAY = String.valueOf(dayOfMonth);
                                                                       mMonth = String.valueOf(monthOfYear + 1);
                                                                       if (monthOfYear < 10) {
                                                                           mMonth = "0" + mMonth;
                                                                       }
                                                                       if (dayOfMonth < 10) {
                                                                           mDAY = "0" + mDAY;
                                                                       }
                                                                       recyclerPercent.setAdapter(new PercentAdapter(context, model.getDayRecords()));
                                                                       recyclerPercent.setLayoutManager(new LinearLayoutManager(context,
                                                                               RecyclerView.VERTICAL, false));
                                                                   }
                                                               }, persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay());
        dialog.setThemeDark(true);
        dialog.show(activity.getFragmentManager(), "Datepickerdialog");
    }

}
