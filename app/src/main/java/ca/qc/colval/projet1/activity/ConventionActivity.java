package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
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

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.ContractPostAPI;
import ca.qc.colval.projet1.api.ExpenseRestAPI;
import ca.qc.colval.projet1.dao.SupplierConventionDAO;
import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.SupplierConvention;
import ca.qc.colval.projet1.utility.UtilityClass;

public class ConventionActivity extends AppCompatActivity {

    Spinner spn_supplier;
    TextView txt_desc,txt_amount;
    Button btn_add;

    //chart
    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention);

        //init
        spn_supplier = findViewById(R.id.convention_spn_supplier);
        txt_desc = findViewById(R.id.convention_txt_desc);
        txt_amount = findViewById(R.id.convention_txt_amount);
        btn_add = findViewById(R.id.convention_btn_add);
        chart = findViewById(R.id.chart1);

        createChart();
        //remplir le graphique avec les convention/contrat
        setData(5, 100);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_desc.getText().toString().equals("")||txt_amount.getText().toString().equals("")){
                    UtilityClass.Toast(getApplicationContext(),"Il faut remplir toutes les entrées de données");
                } else {
                    addConventionClick(v);
                }
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
    public void createChart(){
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setData(int count, float range) {
        float start = 1f;
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));
            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, getResources().getDrawable(com.google.android.material.R.drawable.abc_star_black_48dp)));
            } else {
                values.add(new BarEntry(i, val));
            }
        }
        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "The year 2017");
            set1.setDrawIcons(false);
            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
            List<GradientColor> gradientFills = new ArrayList<>();
            gradientFills.add(new GradientColor(startColor1, endColor1));
            gradientFills.add(new GradientColor(startColor2, endColor2));
            gradientFills.add(new GradientColor(startColor3, endColor3));
            gradientFills.add(new GradientColor(startColor4, endColor4));
            gradientFills.add(new GradientColor(startColor5, endColor5));
            set1.setGradientColors(gradientFills);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart.setData(data);
        }
    }
}