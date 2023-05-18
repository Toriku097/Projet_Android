package ca.qc.colval.projet1.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ca.qc.colval.projet1.R;

public class CheckViewHolder extends RecyclerView.ViewHolder {
    TextView checkNum,amount, date;

    public CheckViewHolder(View itemView) {
        super(itemView);
        checkNum = itemView.findViewById(R.id.row_lbl_check);
        amount = itemView.findViewById(R.id.row_lbl_amount);
        date = itemView.findViewById(R.id.row_lbl_date);
    }
}
