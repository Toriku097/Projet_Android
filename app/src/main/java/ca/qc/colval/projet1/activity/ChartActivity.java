package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.entities.Expense;

public class ChartActivity extends AppCompatActivity implements CheckGetAPI.CommunicationChannel {

    private PieChart chart1;
    private HorizontalBarChart chart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        //init
        chart1 = findViewById(R.id.chart_chart_supplier);
        chart2 = findViewById(R.id.chart_chart_mounth);

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CheckGetAPI(this));
        service.shutdown();
    }

    private void createPieChart(){
        chart1.setUsePercentValues(true);
        chart1.getDescription().setEnabled(false);
        chart1.setExtraOffsets(5, 10, 5, 5);

        chart1.setDragDecelerationFrictionCoef(0.95f);

//        chart1.setCenterTextTypeface(tfLight);
//        chart1.setCenterText(generateCenterSpannableText());

        chart1.setDrawHoleEnabled(true);
        chart1.setHoleColor(Color.WHITE);

        chart1.setTransparentCircleColor(Color.WHITE);
        chart1.setTransparentCircleAlpha(110);

        chart1.setHoleRadius(58f);
        chart1.setTransparentCircleRadius(61f);

        chart1.setDrawCenterText(true);

        chart1.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart1.setRotationEnabled(true);
        chart1.setHighlightPerTapEnabled(true);

        //chart1.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        //chart1.setOnChartValueSelectedListener(this);
    }
     private void setPieData(List<Expense> expenses) {


        // Create the entries for the PieChart
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Expense expense : expenses) {
            float amount =(float) expense.getAmount();
            String supplier = expense.getSupplier();

            entries.add(new PieEntry(amount, supplier));
        }

        // Create a PieDataSet with the entries
        PieDataSet dataSet = new PieDataSet(entries, "Dépenses par fournisseur");

        // Customize the appearance of the PieDataSet
        dataSet.setSliceSpace(3f);

        // Set colors for the PieDataSet
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        // Add more colors if needed

        dataSet.setColors(colors);

        // Create a PieData object with the PieDataSet
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        // Set the PieData to the PieChart
        chart1.setData(data);

        // Invalidate the PieChart to redraw it with the updated data
        chart1.invalidate();
    }
    private void createHorizChart(){
        chart2.setDrawBarShadow(false);
        chart2.getDescription().setEnabled(false);
        chart2.setExtraOffsets(5, 10, 5, 5);

        chart2.setDragDecelerationFrictionCoef(0.95f);

        chart2.setTouchEnabled(true);
        chart2.setPinchZoom(true);

        chart2.setBackgroundColor(Color.WHITE);
        chart2.setDrawGridBackground(false);

        XAxis xAxis = chart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = chart2.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMinimum(0f);
        chart2.getAxisRight().setEnabled(false);

        chart2.animateY(1000);
        chart2.setDoubleTapToZoomEnabled(false);
        chart2.setHighlightPerTapEnabled(false);
        chart2.setHighlightPerDragEnabled(false);

        Legend legend = chart2.getLegend();
        legend.setEnabled(false);

        chart2.invalidate();
    }
    private void setHorizData(List<Expense> expenses){
        // Create an ArrayList of BarEntry objects to store the data entries for the BarChart
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        BarDataSet dataSet;
        BarData data;
        // Calculate the expenses per month
        float[] expensesPerMonth = calculateExpensesPerMonth(expenses);

        // Populate the barEntries with the expenses per month
        for (int i = 0; i < expensesPerMonth.length; i++) {
            barEntries.add(new BarEntry(i, expensesPerMonth[i]));
        }

        // Create a BarDataSet with the barEntries
        dataSet = new BarDataSet(barEntries, "Expenses per Month");

        // Customize the appearance of the BarDataSet
        dataSet.setColor(Color.BLUE);

        // Create an ArrayList of BarDataSet objects
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        // Create a BarData object with the BarDataSet
        data = new BarData(dataSets);

        // Set the BarData to the BarChart
        chart2.setData(data);

        // Refresh the BarChart
        chart2.invalidate();
    }

    /*@Override
     public void loadData(List<Expense> expenses) {
        //Load data from API to pie chart
        int pieCount = (int) expenses.stream().count();
        float pieRange = (float) expenses.stream().mapToDouble(Expense::getAmount).sum();
        setPieData(pieCount, pieRange);

        //Load data from API to horizontal bar chart
        //setHorizData();
    } */

    private float[] calculateExpensesPerMonth(List<Expense> expenses) {
        // Initialize an array to store the expenses per month
        float[] expensesPerMonth = new float[12];

        // Iterate over the expenses and accumulate the expenses per month
        for (Expense expense : expenses) {
            Date date = expense.getDateObject();
            if (date != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int month = calendar.get(Calendar.MONTH);

                float amount = (float) expense.getAmount();

                if (month >= 0 && month < 12) {
                    expensesPerMonth[month] += amount;
                }
            }
        }

        return expensesPerMonth;
    }

    public void loadData(List<Expense> expenses) {
        // Filter expenses or perform any necessary data manipulation
        List<Expense> filteredExpenses = expenses.stream().collect(Collectors.toList());

        setPieData(filteredExpenses);
        setHorizData(filteredExpenses);

    }
}