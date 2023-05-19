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
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.utility.UtilityClass;

public class CheckAdapter extends RecyclerView.Adapter<CheckViewHolder> {

    List<Expense> expenses;
    Context context;

    public CheckAdapter(List<Expense> checks, Context context) {
        this.expenses = checks;
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
        holder.checkNum.setText(expenses.get(position).getExpenseType());
        holder.amount.setText(String.valueOf(expenses.get(position).getAmount()));
        holder.date.setText(expenses.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }
}
