package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.api.ContractGetAPI;
import ca.qc.colval.projet1.api.ContractPostAPI;
import ca.qc.colval.projet1.api.ContractRestAPI;
import ca.qc.colval.projet1.api.ExpenseRestAPI;
import ca.qc.colval.projet1.dao.SupplierConventionDAO;
import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.SupplierConvention;
import ca.qc.colval.projet1.utility.UtilityClass;

public class ConventionActivity extends AppCompatActivity implements ContractGetAPI.CommunicationChannel, AdapterView.OnItemSelectedListener {

    Spinner spn_supplier, spn_delete;
    TextView txt_desc, txt_amount, txt_delete;
    Button btn_add, btn_delete;
    List<Contract> contracts;
    Contract selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention);

        //init
        spn_supplier = findViewById(R.id.convention_spn_supplier);
        txt_desc = findViewById(R.id.convention_txt_desc);
        txt_amount = findViewById(R.id.convention_txt_amount);
        btn_add = findViewById(R.id.convention_btn_add);
        btn_delete = findViewById(R.id.delete_btn_delete);
        spn_delete = findViewById(R.id.delete_spn_supplier);
        txt_delete = findViewById(R.id.delete_lbl_why);

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new ContractGetAPI(this));
        service.shutdown();


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_desc.getText().toString().equals("") || txt_amount.getText().toString().equals("")) {
                    UtilityClass.Toast(getApplicationContext(), "Il faut remplir toutes les entrées de données");
                } else {
                    addConventionClick(v);
                }
            }
        });

        //spn_delete.setOnItemSelectedListener(this);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContract(v);
            }
        });
    }

    public void addConventionClick(View v) {
        //link ui to variable
        String desc = txt_desc.getText().toString();
        String supplier = spn_supplier.getSelectedItem().toString();
        double amount = Double.parseDouble(txt_amount.getText().toString());
        //initiate new temporary expense
        Contract tempContract = new Contract(desc, supplier, amount);
        //post new expense to database
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new ContractPostAPI(this, tempContract, this));
        service.shutdown();
    }

    public void deleteContract(View v){
        ContractRestAPI contractRestAPI = new ContractRestAPI();
        contractRestAPI.deleteContract(selected.get_id());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = contracts.stream().filter(contract -> contract.getSupplier() == spn_delete.getSelectedItem().toString()).findFirst().get();
        txt_delete.setText(String.valueOf(selected.getSupplier()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void loadData(List<Contract> contracts) {
        this.contracts = contracts;
        List<String> arraySpinner = contracts.stream().map(Contract::getDesc).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_delete.setAdapter(adapter);
    }
}