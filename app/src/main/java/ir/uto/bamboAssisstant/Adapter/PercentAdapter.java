package ir.uto.bamboAssisstant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.Util.ColorUtil;
import ir.uto.bamboAssisstant.modele.Records;

public class PercentAdapter extends RecyclerView.Adapter<PercentViewHolder> {
    Context context;
    List<Records> recordsList;

    public PercentAdapter(Context context, List<Records> recordsList) {
        this.context = context;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public PercentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.percent_row, null);
        return new PercentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PercentViewHolder holder, int position) {


        String percent;
        Records records = recordsList.get(position);

        percent = records.getStringTime();


        holder.txt_percent.setText(percent);
        holder.txt_routine_name.setText(records.getTitle());

    }
    public void refreshList() {

        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return recordsList.size();
    }
}

class PercentViewHolder extends RecyclerView.ViewHolder {
    TextView txt_routine_name;
    TextView txt_percent;

    public PercentViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_routine_name = itemView.findViewById(R.id.txt_routine_name);
        txt_percent = itemView.findViewById(R.id.txt_percent);
    }
}
