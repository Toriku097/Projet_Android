package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.dao.ViewCheckAccountDAO;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.ViewCheckAccount;

public class AccountActivity extends AppCompatActivity implements CheckGetAPI.CommunicationChannel {
    TextView lbl_amount;
    Spinner spn_account,spn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //init
        lbl_amount = findViewById(R.id.account_lbl_amount);
        spn_account = findViewById(R.id.account_spn_account);
        spn_check = findViewById(R.id.account_spn_check);

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CheckGetAPI(this));
        service.shutdown();
    }

    public void payCheckClick (View v) {
        String id = spn_check.getSelectedItem().toString();
        showToast("1");

        //checkDAO.updateCheckbyId(Integer.parseInt(id));
        showToast("Payé");
    }
    private void showToast(String msg) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void loadData(List<Expense> expenses) {
        List<String> arraySpinner = expenses.stream().filter(expense -> !expense.isPaid()).map(Expense::getExpenseType).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_check.setAdapter(adapter);
    }
}