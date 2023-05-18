package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.adapter.CheckAdapter;

public class DeadlineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckAdapter adapter;
    CheckDAO dao;
    List<Check> checks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echeancier);
        //init
        dao = new CheckDAO(this);
        recyclerView = findViewById(R.id.recyclerCheck);
        checks = new ArrayList<>();
        checks.add(new Check(
                1,
                1,
                1,
                54,
                1,
                1,
                "12/12/2012",
                0));
        adapter = new CheckAdapter(checks);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

}