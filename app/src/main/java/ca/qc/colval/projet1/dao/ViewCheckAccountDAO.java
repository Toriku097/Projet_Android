package ca.qc.colval.projet1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.ViewCheckAccount;

public class ViewCheckAccountDAO {
    Singleton singleton;
    public ViewCheckAccountDAO(Context context){
        this.singleton = Singleton.getSingleInstance(context);
    }
    public List<String> getCheckNumByAccount(String accNum) {

        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM viewCheck_Account WHERE isPaid = 0 AND acc_num = '" + accNum + "'";
        Cursor cursor = db.rawQuery(request,null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<String> list = new ArrayList<>();
            while (!cursor.isAfterLast()){
                list.add(cursor.getString(0));
                cursor.moveToNext();
            }
            return list;
        }
        return null;
    }
    public List<ViewCheckAccount> getCheckByAccount(String accNum) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM viewCheck_Account WHERE isPaid = 0 AND acc_num = '" + accNum + "'";
        Cursor cursor = db.rawQuery(request,null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<ViewCheckAccount> list = new ArrayList<>();
            while (!cursor.isAfterLast()){
                ViewCheckAccount vCA = new ViewCheckAccount();
                vCA.setCheckNum(cursor.getInt(0));
                vCA.setCheckID(cursor.getInt(1));


                list.add(vCA);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return list;
        }
        return null;
    }
}
