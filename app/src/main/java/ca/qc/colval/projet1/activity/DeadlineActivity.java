package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.adapter.CheckAdapter;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.utility.UtilityClass;

public class DeadlineActivity extends AppCompatActivity implements CheckGetAPI.CommunicationChannel {

    RecyclerView rv;
    CheckAdapter adapter;
    List<Expense> expenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echeancier);
        //init
        rv = findViewById(R.id.recyclerCheck);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new CheckGetAPI(this));
        service.shutdown();
    }

    @Override
    public void loadData(List<Expense> expenses) {
        //changer le filtre pour ispaid
        this.expenses = expenses.stream().filter(expense -> !expense.isPaid()).collect(Collectors.toList());
        this.adapter = new CheckAdapter(this.expenses, this);
        this.rv.setAdapter(this.adapter);
    }
}