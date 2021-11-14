package ir.uto.bamboAssisstant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.List;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.Util.ColorUtil;
import ir.uto.bamboAssisstant.database.RecordDbAdapter;
import ir.uto.bamboAssisstant.database.RoutineDbAdapter;
import ir.uto.bamboAssisstant.modele.MyRoutine;
import ir.uto.bamboAssisstant.modele.Records;
import ir.uto.bamboAssisstant.services.StopWatchService;


public class RecyclerRoutineAdapter extends RecyclerView.Adapter<RoutineVh> {
    Context context;
    List<MyRoutine> routines;
    RoutineDbAdapter routineDbAdapter;
    RecordDbAdapter recordDbAdapter;
    List<StopWatchService> services;
    List<Boolean> stStop;
    PersianCalendar persianCalendar;
    long recordedSec;
    int recordSize;
    int currentI;
    int recordMin1;

    public RecyclerRoutineAdapter(Context context, List<MyRoutine> routines,
                                  List<StopWatchService> services,
                                  List<Boolean> stStop
    ) {
        this.context = context;
        routineDbAdapter = new RoutineDbAdapter(context);
        recordDbAdapter = new RecordDbAdapter(context);
        this.routines = routines;
        this.services = services;
        this.stStop = stStop;
        persianCalendar = new PersianCalendar();


    }

    @NonNull
    @Override
    public RoutineVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        recordSize = recordDbAdapter.getDayRecord(persianCalendar.getPersianShortDate()).size();
        recordMin1 = recordSize - 1;
        View view = LayoutInflater.from(context).inflate(R.layout.routin_row, null);
        return new RoutineVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineVh holder, int position) {
        holder.img_done.setImageResource(R.drawable.ic_circle_dot_record_round_icon);
        MyRoutine myRoutineList = routines.get(position);
        holder.txt_routine_name.setText(myRoutineList.getRoutineName());
        holder.txt_routine_duration.setText(myRoutineList.getSelectedTime());

            recordedSec = recordDbAdapter.getDayRecord(persianCalendar.getPersianShortDate()).get(currentI).getRecordedTime();



        if (currentI != recordSize - 1) {
            if (recordSize > currentI) {
                currentI = currentI + 1;
            }
        }


        long targetSec = myRoutineList.getChoosenTime();
        if (recordedSec >= targetSec) {
            holder.img_done.setColorFilter(ColorUtil.getColorsRes().get(6));
            Log.e("", "");

        } else if (recordedSec > 0 && recordedSec < targetSec) {
            holder.img_done.setColorFilter(ColorUtil.getColorsRes().get(5));
            Log.e("", "");

        }
        List<Records> daysRecord = recordDbAdapter.getDayRecord(persianCalendar.getPersianShortDate());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routineDbAdapter.deleteRoutine(myRoutineList.getId());
                routines.remove(holder.getAdapterPosition());
                //recordDbAdapter.deleteRecord(myRoutineList.getId());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), routines.size());

            }
        });


        holder.img_menu.setOnClickListener(new View.OnClickListener() {
            Intent servIntent = new Intent(context, services.get(holder.getAdapterPosition()).getClass());

            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, holder.img_menu);

                popupMenu.inflate(R.menu.recycler_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_start:


                                servIntent.setAction("");
                                servIntent.putExtra("id", myRoutineList.getId());
                                servIntent.putExtra("title", holder.txt_routine_name.getText().toString());
                                servIntent.putExtra("hour", myRoutineList.getHour());
                                servIntent.putExtra("min", myRoutineList.getMin());
                                context.startService(servIntent);

                                break;

                            case R.id.item_stop:

                                context.stopService(servIntent);


                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void refreshList(List<MyRoutine> routineList) {
        routines = routineList;
        notifyDataSetChanged();
    }
}

class RoutineVh extends RecyclerView.ViewHolder {
    AppCompatTextView txt_routine_name;
    AppCompatTextView txt_routine_duration;
    AppCompatImageView btn_delete;
    AppCompatImageView img_menu;
    AppCompatImageView img_done;
    CardView cardView;

    public RoutineVh(@NonNull View itemView) {
        super(itemView);
        txt_routine_name = itemView.findViewById(R.id.txt_routine_name);
        txt_routine_duration = itemView.findViewById(R.id.txt_routine_duration);
        btn_delete = itemView.findViewById(R.id.btn_delete);
        img_menu = itemView.findViewById(R.id.img_menu);
        cardView = itemView.findViewById(R.id.cardView);
        img_done = itemView.findViewById(R.id.img_done);
    }
}