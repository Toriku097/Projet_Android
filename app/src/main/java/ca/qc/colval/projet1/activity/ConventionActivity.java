package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import ca.qc.colval.projet1.R;

public class ConventionActivity extends AppCompatActivity {

    TextView txt_supplier;
    Spinner spn_supplier,spn_convention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention);

        //init
        txt_supplier = findViewById(R.id.convention_txt_supplier);
        spn_supplier = findViewById(R.id.convention_spn_supplier);
        spn_convention = findViewById(R.id.convention_spn_convention);
    }
    public void addConventionClick(View v){}
}