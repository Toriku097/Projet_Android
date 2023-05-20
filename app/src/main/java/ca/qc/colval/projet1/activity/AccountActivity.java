package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.api.IsPaidPutAPI;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.dao.ViewCheckAccountDAO;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.ViewCheckAccount;
import ca.qc.colval.projet1.utility.UtilityClass;

public class AccountActivity extends AppCompatActivity implements CheckGetAPI.CommunicationChannel, AdapterView.OnItemSelectedListener {
    TextView lbl_amount;
    Spinner spn_account,spn_check;
    Button btnPay;
    List<Expense> expenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //init
        lbl_amount = findViewById(R.id.account_lbl_amount);
        spn_account = findViewById(R.id.account_spn_account);
        spn_check = findViewById(R.id.account_spn_check);
        btnPay = findViewById(R.id.account_btn_pay);

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CheckGetAPI(this));
        service.shutdown();


        spn_check.setOnItemSelectedListener(this);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payCheckClick(v);
            }
        });
    }


    public void payCheckClick (View v) {
        Expense update = expenses.stream().filter(expense -> expense.getExpenseType() == spn_check.getSelectedItem().toString()).findFirst().get();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new IsPaidPutAPI(update,this,this));
        service.shutdown();

        UtilityClass.Toast(this,update.getExpenseType()+" a été payé");
    }
    @Override
    public void loadData(List<Expense> expenses) {
        //keep expenses for the update
        this.expenses = expenses;
        //load data to spinner
        List<String> arraySpinner = expenses.stream().filter(expense -> !expense.isPaid()).map(Expense::getExpenseType).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_check.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Expense selected = expenses.stream().filter(expense -> expense.getExpenseType() == spn_check.getSelectedItem().toString()).findFirst().get();
        lbl_amount.setText(String.valueOf(selected.getAmount())+'$');
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}