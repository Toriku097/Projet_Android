package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.ContractPostAPI;
import ca.qc.colval.projet1.api.ExpenseRestAPI;
import ca.qc.colval.projet1.dao.SupplierConventionDAO;
import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.SupplierConvention;

public class ConventionActivity extends AppCompatActivity {

    Spinner spn_supplier;
    TextView txt_desc,txt_amount;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention);

        //init
        spn_supplier = findViewById(R.id.convention_spn_supplier);
        txt_desc = findViewById(R.id.convention_txt_desc);
        txt_amount = findViewById(R.id.convention_txt_amount);
        btn_add = findViewById(R.id.convention_btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addConventionClick(v);
            }
        });
    }
    public void addConventionClick(View v) {
        //link ui to variable
        String desc = txt_desc.getText().toString();
        String supplier = spn_supplier.getSelectedItem().toString();
        double amount = Double.parseDouble(txt_amount.getText().toString());
        //initiate new temporary expense
        Contract tempContract = new Contract(desc,supplier,amount);
        //post new expense to database
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new ContractPostAPI(this, tempContract,this));
        service.shutdown();
    }
}