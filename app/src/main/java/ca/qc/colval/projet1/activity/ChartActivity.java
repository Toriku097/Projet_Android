package ca.qc.colval.projet1.activity;

import static java.util.stream.Collectors.toMap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        //chart1.setOnChartValueSelectedListener(this);
    }
    private void setPieData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//
//        for (int i = 0; i < count ; i++) {
//            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
//                    parties[i % parties.length],
//                    getResources().getDrawable(R.drawable.star)));
//        }

        PieDataSet dataSet = new PieDataSet(entries, "Dépenses par fournisseur ");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(tfLight);
        chart1.setData(data);

        // undo all highlights
        chart1.highlightValues(null);

        chart1.invalidate();
    }
    private void createHorizChart(){}
    private void setHorizData(){}

    @Override
    public void loadData(List<Expense> expenses) {
        //Load data from API to pie chart
        int pieCount = (int) expenses.stream().distinct().count();
        float pieRange = (float) expenses.stream().count();

        //setPieData();

        //Load data from API to horizontal bar chart
        //setHorizData();
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}