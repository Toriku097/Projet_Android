package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.BankAccountDAO;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.dao.ViewCheckAccountDAO;

public class AccountActivity extends AppCompatActivity {

    TextView lbl_amount;
    Spinner spn_account,spn_check;

    ViewCheckAccountDAO viewCheckAccountDAO;
    CheckDAO checkDAO;

    List<Integer> list;

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

                try { list = viewCheckAccountDAO.getCheckByAccount(newItem);}
                catch (java.lang.NullPointerException e) {list = new ArrayList<>();}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void payCheckClick (View v) {
        //simplement faire disparaitre la facture une fois payés


    }
}