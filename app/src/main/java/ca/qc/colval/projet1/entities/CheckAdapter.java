package ca.qc.colval.projet1.entities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.BankAccountDAO;
import ca.qc.colval.projet1.dao.ProjectDAO;

public class CheckAdapter extends RecyclerView.Adapter<CheckViewHolder> {

    List<Check> checks;

    BankAccountDAO bankDAO;
    ProjectDAO projectDAO;

    public CheckAdapter(List<Check> checks) {
        this.checks = checks;
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
        holder.account.setText(bankDAO.getBankAccountNobyId(checks.get(position).getAccountId()));
        holder.amount.setText(String.valueOf(checks.get(position).getAmount()));
        holder.date.setText(checks.get(position).getDeadlineDate());
        holder.project.setText(projectDAO.getProjectNamebyId(checks.get(position).getProjectId()));
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }
}
