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

public class ExpenseActivity extends AppCompatActivity {

    TextView txt_amount,txt_expenseType,txt_date;
    Spinner spn_project,spn_paymentMethod,spn_Account,spn_supplier;

    ExpenseDAO expenseDAO;
    CheckDAO checkDAO;
    List<Expense> expenses;
    List<Check> checks;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        //init
        try {
            expenses = expenseDAO.getAllExpenses();
        }
        catch (java.lang.NullPointerException e){System.out.println(e);}
        finally {
            expenses = new ArrayList<>();
        }
        try {
            checks = checkDAO.getAllChecks();
        }
        catch (java.lang.NullPointerException e){System.out.println(e);}
        finally {
            checks = new ArrayList<>();
        }

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
    }

    public void addExpenseClick(View v) {
        //rajouté la dépense aux tables expense (+chèque)
        Expense tempExpense;
        int expenseId = 1;
        String expenseType = txt_expenseType.getText().toString();
        Double amount = Double.parseDouble(txt_amount.getText().toString());
        String payment = spn_paymentMethod.getSelectedItem().toString();
        int[] id = getId();
        int checkId = 1;
        String date = txt_date.getText().toString();

        if(expenses.isEmpty()==false) {
            expenseId++;
            for (Expense expense : expenses) {
                if (expense.getExpenseId() != expenseId) {
                    tempExpense = new Expense(expenseId,expenseType,amount, payment,id[0], id[1], id[2]);
                    expenseDAO.addExpense(tempExpense);
                    expenses.add(tempExpense);
                    showToast("Dépense ajouté");
                    if(payment.equals("Chèque")){
                        checkId++;
                        Check tempCheck = new Check(checkId,expenseId, random.nextInt(666),amount,id[1],id[0],date,0);
                        checkDAO.addCheck(tempCheck);
                        checks.add(tempCheck);
                        showToast("Cheque ajouté");
                    }
                    break;
                }
                showToast("++");
                expenseId++;
            }
        }else {
            tempExpense = new Expense(expenseId,expenseType,amount, payment,id[0], id[1], id[2]);
            expenseDAO.addExpense(tempExpense);
            expenses.add(tempExpense);
            showToast("Dépense ajouté");
            if(payment.equals("Chèque")){
                Check tempCheck = new Check(checkId,expenseId, random.nextInt(666),amount,id[1],id[0],date,0);
                showToast("objet cree");
                checkDAO.addCheck(tempCheck);
                showToast("dao");
                checks.add(tempCheck);
                showToast("list");
                showToast("Cheque ajouté");
            }
        }
//(int checkId, int expenseId, int checkNum, double amount, int accountId, int projectId, String deadlineDate,int isPaid)
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
                case "Buildmate":id[2]=1;
                case "Constructronix":id[2]=2;
                case "SteelScape":id[2]=3;
                case "Concretopia":id[2]=4;
                case "LumberLink":id[2]=5;
                case "PlumbingPro":id[2]=6;
            }

            return id;
        }
        private void showToast(String msg){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        }
}