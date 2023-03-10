package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import ca.qc.colval.projet1.R;

public class ExpenseActivity extends AppCompatActivity {

    TextView txt_supplier,txt_amount,txt_expenseType,txt_date;
    Spinner spn_project,spn_paymentMethod,spn_Account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        //init
        txt_supplier = findViewById(R.id.depense_txt_supplier);
        txt_amount = findViewById(R.id.depense_txt_money);
        txt_expenseType = findViewById(R.id.depense_txt_expenseType);
        txt_date = findViewById(R.id.depense_txt_date);
        spn_project = findViewById(R.id.depense_spn_project);
        spn_paymentMethod = findViewById(R.id.depense_spn_paymentMethod);
        spn_Account = findViewById(R.id.depense_spn_account);
    }

    public void addExpenseClick(View v){
    }
}