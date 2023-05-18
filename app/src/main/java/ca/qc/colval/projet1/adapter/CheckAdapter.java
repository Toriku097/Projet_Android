package ca.qc.colval.projet1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.entities.Check;

public class CheckAdapter extends RecyclerView.Adapter<CheckViewHolder> {

    List<Check> checks;
    Context context;

    public CheckAdapter(List<Check> checks, Context context) {
        this.checks = checks;
        this.context = context;
    }
    @NonNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new CheckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckViewHolder holder, int position) {
        holder.checkNum.setText(checks.get(position).getCheckNum());
        holder.amount.setText(String.valueOf(checks.get(position).getAmount()));
        holder.date.setText(checks.get(position).getDeadlineDate());
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }
}
