package ca.qc.colval.projet1.dao;

import java.util.List;

import ca.qc.colval.projet1.entities.Expense;

public interface IExpenseDAO {

    List<Expense> getAllExpenses();
    Expense getExpensebyId(int id);
    Expense addExpense(Expense expense);
    Expense updateExpensebyId(int id, Expense expense);
    Expense deleteExpensebyId(int id);
}
