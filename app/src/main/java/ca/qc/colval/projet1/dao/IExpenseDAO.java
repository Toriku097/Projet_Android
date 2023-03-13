package ca.qc.colval.projet1.dao;

import java.util.List;

import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.entities.Project;

public interface IExpenseDAO {

    List<Expense> getAllExpenses();
    Expense getExpensebyId(int id);
    void addExpense(Expense expense);
    Expense updateExpensebyId(int id, Expense expense);
    Expense deleteExpensebyId(int id);
}
