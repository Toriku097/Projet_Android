package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.entities.CheckAdapter;

public class DeadlineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView emptyView;
    CheckAdapter adapter;
    CheckDAO dao;
    List<Check> checks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echeancier);

        try {
            recyclerView = findViewById(R.id.recyclerCheck);
            emptyView = findViewById(R.id.empty_view);
            checks = dao.getAllChecks();

            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);

            adapter = new CheckAdapter(checks);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        } catch(NullPointerException e) {
            recyclerView = findViewById(R.id.recyclerCheck);
            emptyView = findViewById(R.id.empty_view);

            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

}