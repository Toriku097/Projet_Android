package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.BankAccountDAO;
import ca.qc.colval.projet1.dao.CheckDAO;

public class AccountActivity extends AppCompatActivity {

    TextView lbl_amount;
    Spinner spn_account,spn_check;

    BankAccountDAO bankAccountDAO;
    CheckDAO checkDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //init
        bankAccountDAO = new BankAccountDAO(this);
        checkDAO = new CheckDAO(this);

        lbl_amount = findViewById(R.id.account_lbl_amount);
        spn_account = findViewById(R.id.account_spn_account);
        spn_check = findViewById(R.id.account_spn_check);
    }

    @Override
    protected void onStart() {
        super.onStart();
        
    }

    //il faut afficher les chèques qui sont en lien avec le compte bancaire sélectionner

    public void payCheckClick (View v) {
        //simplement faire disparaitre la facture une fois payés


    }
}