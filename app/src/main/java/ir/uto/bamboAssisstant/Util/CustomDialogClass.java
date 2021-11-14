package ir.uto.bamboAssisstant.Util;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.database.RoutineDbAdapter;
import ir.uto.bamboAssisstant.modele.MyRoutine;

public class CustomDialogClass {
    int mHour;
    int mMinute;
    String selectedTime;
    String timeSelected;
    int timeSec;
    String title;

    public void showAddRoutineDialog(Activity activity, IRecycler iRecycler) {


        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.fragment_start, null);

        alert.setView(view);
        TextInputEditText txt_title = view.findViewById(R.id.txt_title);
        AppCompatButton btn_submit = view.findViewById(R.id.btn_submit);
        AppCompatButton btn_time = view.findViewById(R.id.btn_time);
        timeSelected = "";
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String tempHour = "" + hourOfDay;
                                String tempMinute = "" + minute;
                                if (hourOfDay < 10) {
                                    tempHour = "0" + hourOfDay;
                                }
                                if (minute < 10) {
                                    tempMinute = "0" + minute;
                                }
                                selectedTime = tempHour + ":" + tempMinute;

                                timeSelected = "تارگت شما:" + " " + selectedTime;
                                mMinute = minute;
                                mHour = hourOfDay;
                                timeSec = (hourOfDay * 3600) + (minute * 60);
                                btn_time.setText(selectedTime);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = txt_title.getText().toString();

                if (timeSelected.equals("") && title.equals("")) {
                    btn_time.setText("مقدار زمان*");
                    txt_title.setHint("نام روتین اجباری میباشد");
                    Toast.makeText(activity.getApplicationContext(), "لطفا تمام فیلد هارا تکمیل کنید", Toast.LENGTH_LONG).show();

                } else if (timeSelected.equals("")) {
                    btn_time.setText("مقدار زمان*");
                    Toast.makeText(activity.getApplicationContext(), "لطفا مدت طول روتین را انتخاب کنید", Toast.LENGTH_LONG).show();
                } else if (title.equals("")) {
                    txt_title.setHint("نام روتین اجباری میباشد");
                    Toast.makeText(activity.getApplicationContext(), "لطفا نام روتین را وارد نمایید", Toast.LENGTH_LONG).show();
                } else {

                    if (mHour + mMinute == 0) {
                        Snackbar.make(v, "مدت زمان روتین شما، نمیتواند صفر باشد! ", BaseTransientBottomBar.LENGTH_LONG).show();
                    } else {
                        RoutineDbAdapter routineDbAdapter = new RoutineDbAdapter(activity);
                        MyRoutine routine = new MyRoutine();
                        routine.setRoutineName(txt_title.getText().toString());
                        routine.setHour(mHour);
                        routine.setMin(mMinute);
                        routine.setSelectedTime(timeSelected);
                        routine.setChoosenTime(timeSec);
                        long result = routineDbAdapter.addRoutine(routine);
                        if (result > 0) {
                            Snackbar.make(v, R.string.RoutineAdded, BaseTransientBottomBar.LENGTH_SHORT).show();
                            txt_title.getText().clear();
                            btn_time.setText("مقدار زمان");
                            iRecycler.hi();

                        } else {
                            Snackbar.make(v, R.string.Error, BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });
        alert.show();
    }
}
