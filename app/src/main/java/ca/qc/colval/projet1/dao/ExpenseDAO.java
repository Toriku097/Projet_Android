package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.Expense;

public class ExpenseDAO implements IExpenseDAO {


        Singleton singleton;
        public ExpenseDAO(Context context) {
            this.singleton = Singleton.getSingleInstance(context);
        }

        @Override
        public List<Expense> getAllExpenses() {
            SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
            String request = "SELECT * FROM Expenses";
            Cursor cursor = db.rawQuery(request, null);
            if(cursor != null) {
                cursor.moveToFirst();
                List<Expense> expenses = new ArrayList<>();
                while(!cursor.isAfterLast()){
                    Expense expense = new Expense();
                    expense.setExpenseId(cursor.getInt(0));
                    expense.setExpenseType(cursor.getString(1));
                    expense.setAmount(cursor.getDouble(2));
                    expense.setPaymentMethod(cursor.getString(3));
                    expense.setBankAccountId(cursor.getInt(4));
                    expense.setSupplierId(cursor.getInt(5));

                    expenses.add(expense);
                    cursor.moveToNext();
                }
                db.close();
                cursor.close();
                return expenses;
            }
            return null;
        }

        @Override
        public Expense getExpensebyId(int id) {
            SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
            String request = "SELECT * FROM BankAccounts WHERE idd = " + id;
            Cursor cursor = db.rawQuery(request, null);
            if(cursor != null) {
                cursor.moveToFirst();
                Expense expense = new Expense();
                expense.setExpenseId(cursor.getInt(0));
                expense.setExpenseType(cursor.getString(1));
                expense.setAmount(cursor.getDouble(2));
                expense.setPaymentMethod(cursor.getString(3));
                expense.setBankAccountId(cursor.getInt(4));
                expense.setSupplierId(cursor.getInt(5));

                db.close();
                cursor.close();
                return expense;
            }
            return null;
        }

        @Override
        public Expense addExpense(Expense expense) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("expense_type", expense.getExpenseType());
            values.put("amount", expense.getAmount());
            values.put("payment_method", expense.getPaymentMethod());
            values.put("idcb", expense.getBankAccountId());
            values.put("idf", expense.getSupplierId());

            int id = (int) db.insert("Expenses", null, values);
            return getExpensebyId(id);
        }


        @Override
        public Expense updateExpensebyId(int id, Expense expense) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("expense_type", expense.getExpenseType());
            values.put("amount", expense.getAmount());
            values.put("payment_method", expense.getPaymentMethod());
            values.put("idcb", expense.getBankAccountId());
            values.put("idf", expense.getSupplierId());

            db.update("Expenses", values, "idd = ?", new String[]{ id+""});
            return getExpensebyId(id);
        }

        @Override
        public Expense deleteExpensebyId(int id) {
            Expense expense = getExpensebyId(id);
            if(expense != null) {
                SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
                db.delete("Expenses","idd = ?", new String[]{id + ""});
            }
            return expense;
        }
}
