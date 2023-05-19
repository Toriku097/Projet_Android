package ca.qc.colval.projet1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.qc.colval.projet1.R;
import ca.qc.colval.projet1.dao.CheckDAO;
import ca.qc.colval.projet1.dao.ExpenseDAO;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.utility.UtilityClass;

public class ExpenseActivity extends AppCompatActivity {

    TextView txt_amount,txt_expenseType,txt_date;
    Spinner spn_project,spn_paymentMethod,spn_Account,spn_supplier;

    ExpenseDAO expenseDAO;
    CheckDAO checkDAO;
    List<Expense> expenses;
    List<Check> checks;
    Random random;
    int expenseId, checkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        //init
        try {expenses = expenseDAO.getAllExpenses();}
        catch (java.lang.NullPointerException e){System.out.println(e);}
        finally {expenses = new ArrayList<>();}
        try {checks = checkDAO.getAllChecks();}
        catch (java.lang.NullPointerException e){System.out.println(e);}
        finally {checks = new ArrayList<>();}

        expenseDAO = new ExpenseDAO(this);
        checkDAO = new CheckDAO(this);

        spn_supplier = findViewById(R.id.expense_spn_supplier);
        txt_amount = findViewById(R.id.depense_txt_money);
        txt_expenseType = findViewById(R.id.depense_txt_expenseType);
        txt_date = findViewById(R.id.depense_txt_date);
        spn_project = findViewById(R.id.depense_spn_project);
        spn_paymentMethod = findViewById(R.id.depense_spn_paymentMethod);
        spn_Account = findViewById(R.id.depense_spn_account);

        random = new Random();
        expenseId = 1;
        checkId = 1;
    }

    @Override
    protected void onStart() {
        super.onStart();

        //initialisation au retour
        expenses = expenseDAO.getAllExpenses();
        expenseId = expenses.size()+1;
        checks = checkDAO.getAllChecks();
        checkId = checks.size()+1;
    }

    public void addExpenseClick(View v) {
        //link ui to variable
        String expenseType = txt_expenseType.getText().toString();
        Double amount = Double.parseDouble(txt_amount.getText().toString());
        String payment = spn_paymentMethod.getSelectedItem().toString();
        String bank = spn_Account.getSelectedItem().toString();
        String supplier = spn_supplier.getSelectedItem().toString();
        String project = spn_project.getSelectedItem().toString();
        String date = txt_date.getText().toString();
        //initiate new temporary expense
        Expense tempExpense = new Expense(expenseType,amount, payment,bank,supplier,project,date);
        //post new expense to database



        UtilityClass.Toast(this,"Dépense ajouté");


        /*if(!expenses.isEmpty()) {
            for (Expense expense : expenses) {
                if (expense.getExpenseId() != expenseId) {
                    addExpense();
                    break;
                }
            }
        } else {
            UtilityClass.Toast(this,"Premiere donnée ajouté");
            addExpense();
        }*/
    }/*
    private void addExpense(){
        Expense tempExpense;
        String expenseType = txt_expenseType.getText().toString();
        Double amount = Double.parseDouble(txt_amount.getText().toString());
        String payment = spn_paymentMethod.getSelectedItem().toString();
        //int[] id = getId();

        tempExpense = new Expense(expenseId,expenseType,amount, payment,id[0], id[1], id[2]);
        expenseDAO.addExpense(tempExpense);
        expenses.add(tempExpense);
        UtilityClass.Toast(this,"Dépense ajouté");

        expenseId++;
        if(payment.equals("Chèque")){
            addCheck();
        }
    }
    private void addCheck(){
        Double amount = Double.parseDouble(txt_amount.getText().toString());
        int[] id = getId();
        String date = txt_date.getText().toString();

        Check tempCheck = new Check(checkId,expenseId, random.nextInt(666),amount,id[1],id[0],date,0);
        checkDAO.addCheck(tempCheck);
        checks.add(tempCheck);
        UtilityClass.Toast(this,"Cheque ajouté");

        checkId++;
    }

    private int[] getId(){
            int[] id = new int[3];
            if (spn_project.getSelectedItem().toString().equals("Les Jardins Mercier"))
                id[0] = 1;
            else id[0] = 2;

            if (spn_Account.getSelectedItem().toString().equals("BC1_RBC Scott"))
                id[1] = 1;
            else id[1] = 2;

            switch (spn_supplier.getSelectedItem().toString()) {
                case "Buildmate":
                    id[2] = 1;
                case "Constructronix":
                    id[2] = 2;
                case "SteelScape":
                    id[2] = 3;
                case "Concretopia":
                    id[2] = 4;
                case "LumberLink":
                    id[2] = 5;
                case "PlumbingPro":
                    id[2] = 6;
            }

            return id;
    }*/
}