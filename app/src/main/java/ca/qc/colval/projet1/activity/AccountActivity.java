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

    ViewCheckAccountDAO viewCheckAccountDAO;
    CheckDAO checkDAO;

    List<ViewCheckAccount> infoList;
    List<String> numList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //init
        viewCheckAccountDAO = new ViewCheckAccountDAO(this);
        checkDAO = new CheckDAO(this);

        lbl_amount = findViewById(R.id.account_lbl_amount);
        spn_account = findViewById(R.id.account_spn_account);
        spn_check = findViewById(R.id.account_spn_check);

        spn_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newItem = spn_account.getSelectedItem().toString();

                try {
                    infoList = viewCheckAccountDAO.getCheckByAccount(newItem);
                    numList = viewCheckAccountDAO.getCheckNumByAccount(newItem);
                }
                catch (java.lang.NullPointerException e) {
                    infoList = new ArrayList<>();
                    numList = new ArrayList<>();
                    numList.add("Empty");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AccountActivity.this, android.R.layout.simple_spinner_dropdown_item,numList);
                spn_check.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void payCheckClick (View v) {
        String id = spn_check.getSelectedItem().toString();
        showToast("1");

        checkDAO.updateCheckbyId(Integer.parseInt(id));
        showToast("Payé");
    }
    private void showToast(String msg) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void loadData(List<Expense> expenses) {
        List<String> arraySpinner = expenses.stream().filter(expense -> expense.getPaymentMethod() == "Chèque")
    }
}