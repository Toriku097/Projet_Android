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

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.api.CheckGetAPI;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.adapter.CheckAdapter;
import ca.qc.colval.projet1.utility.UtilityClass;

public class DeadlineActivity extends AppCompatActivity {

    RecyclerView rv;
    CheckAdapter adapter;
    List<Check> checks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echeancier);
        //init
        rv = findViewById(R.id.recyclerCheck);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        checks = new ArrayList<>();
        checks.add(new Check(1,1,101,50,1,1,"12/12/2012",0));

        for (Check c : checks){
            UtilityClass.Toast(this,c.toString());
        }

        adapter = new CheckAdapter(checks,this);
        rv.setAdapter(adapter);



//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(new CheckGetAPI(this,rv));
//        service.shutdown();
    }

}