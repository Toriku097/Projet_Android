package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.Check;

public class CheckDAO implements ICheckDAO {

    Singleton singleton;

    public CheckDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<Check> getAllChecks() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Checks";
        Cursor cursor = db.rawQuery(request, null);

        if(cursor != null) {
            cursor.moveToFirst();
            List<Check> checks = new ArrayList<>();
            while(!cursor.isAfterLast()) {
                Check check = new Check();
                check.setCheckId(cursor.getInt(0));
                check.setExpenseId(cursor.getInt(1));
                check.setCheckNum(cursor.getInt(2));
                check.setAmount(cursor.getDouble(3));
                check.setAccountId(cursor.getInt(4));
                check.setProjectId(cursor.getInt(5));
                check.setDeadlineDate(cursor.getString(6));

                checks.add(check);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return checks;
        }
        return null;
    }

    @Override
    public Check getCheckbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Checks WHERE idch = " + id;
        Cursor cursor = db.rawQuery(request, null);

        if(cursor != null) {
            cursor.moveToFirst();
            Check check = new Check();
            check.setCheckId(cursor.getInt(0));
            check.setExpenseId(cursor.getInt(1));
            check.setCheckNum(cursor.getInt(2));
            check.setAmount(cursor.getDouble(3));
            check.setAccountId(cursor.getInt(4));
            check.setProjectId(cursor.getInt(5));
            check.setDeadlineDate(cursor.getString(6));

            db.close();
            cursor.close();
            return check;
        }
        return null;
    }

    public Check addCheck(Check check) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idch", check.getCheckId());
        values.put("idd", check.getExpenseId());
        values.put("check_num", check.getCheckNum());
        values.put("amount", check.getAmount());
        values.put("idcb", check.getAccountId());
        values.put("idp", check.getProjectId());
        values.put("date", check.getDeadlineDate());
        values.put("isPaid",check.getIsPaid());
//(int checkId, int expenseId, int checkNum, double amount, int accountId, int projectId, String deadlineDate,int isPaid)
        db.insert("Checks", null, values);
        return getCheckbyId(check.getCheckId());
    }

    public Check updateCheckbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isPaid", 1);

        db.update("Checks", values, "idch = ?", new String[]{id + ""});
        return getCheckbyId(id);
    }

    public Check deleteCheckbyId(int id) {
        Check check = getCheckbyId(id);
        if(check != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("Checks", "idch = ?", new String[]{id + ""});
        }
        return check;
    }
}
