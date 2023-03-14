package ca.qc.colval.projet1.entities;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ca.qc.colval.projet1.R;

public class CheckViewHolder extends RecyclerView.ViewHolder {
    TextView checkNum;
    TextView amount;
    TextView date;
    View container;

    public CheckViewHolder(View itemView) {
        super(itemView);
        checkNum = (TextView) itemView.findViewById(R.id.row_lbl_check);
        amount = (TextView) itemView.findViewById(R.id.row_lbl_amount);
        date = (TextView) itemView.findViewById(R.id.row_lbl_date);
        container = itemView;
    }
}
