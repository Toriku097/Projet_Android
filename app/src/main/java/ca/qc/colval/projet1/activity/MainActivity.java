package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.entities.Convention;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Database
        //Singleton connexion = Singleton.getSingleInstance(this);
    }
    public void toExpenseClick(View v){
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
    }
    public void toAccountClick(View v){
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
    public void toConventionClick(View v){
        Intent intent = new Intent(this, ConventionActivity.class);
        startActivity(intent);
    }
    public void toDeadlineClick(View v){
        Intent intent = new Intent(this, DeadlineActivity.class);
        startActivity(intent);
    }
}