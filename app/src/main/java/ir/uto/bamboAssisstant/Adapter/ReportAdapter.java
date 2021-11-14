package ir.uto.bamboAssisstant.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.data.BaseDataSet;

import java.util.List;

import ir.uto.bamboAssisstant.R;
import ir.uto.bamboAssisstant.Util.ColorUtil;
import ir.uto.bamboAssisstant.modele.Records;

public class ReportAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<Records> recordList;
    List<String> colorList;
    List<Integer> colorList1;


    public ReportAdapter(Context context, List<Records> recordList, BaseDataSet baseDataSet) {
        this.context = context;
        this.recordList = recordList;
        colorList = ColorUtil.getColorList();
        colorList1 = baseDataSet.getColors();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.report_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.view.setBackgroundColor(Color.parseColor(colorList.get(position)));
        holder.textView.setText(recordList.get(position).getTitle());
        holder.textView.setTextColor(colorList1.get(position));

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }
}


class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    View view;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.view);
        textView = itemView.findViewById(R.id.textView);


    }
}