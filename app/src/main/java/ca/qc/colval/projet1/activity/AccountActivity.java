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
    }
    public void payCheckClick (View v) {}
}