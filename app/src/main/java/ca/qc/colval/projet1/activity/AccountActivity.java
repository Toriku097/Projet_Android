package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import ca.qc.colval.projet1.R;

public class AccountActivity extends AppCompatActivity {

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
    }
    public void payCheckClick (View v) {}
}