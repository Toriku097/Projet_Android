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
    List<Check> checks;

    Check tempCheck = new Check(2, 12345, 90.75, 2, 2, "2022-12-22", 1);

    //change to deadline instead of check please jesu schrist fucking my god i want tofucking kill someone

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echeancier);

        try {
            recyclerView = findViewById(R.id.recyclerCheck);
            emptyView = findViewById(R.id.empty_view);
            checks = new ArrayList<>();
            checks.add(tempCheck);

            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);

            adapter = new CheckAdapter(checks);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        } catch (NullPointerException e) {
            recyclerView = findViewById(R.id.recyclerCheck);
            emptyView = findViewById(R.id.empty_view);

            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    /* @Override
    protected void onStart() {
        super.onStart();
        checks = dao.getAllChecks();


        adapter = new CheckAdapter(checks);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    } */
}