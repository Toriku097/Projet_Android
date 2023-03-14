package ca.qc.colval.projet1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewCheckAccountDAO {
    Singleton singleton;
    public ViewCheckAccountDAO(Context context){
        this.singleton = Singleton.getSingleInstance(context);
    }
    public List<Integer> getCheckByAccount(String accNum) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT checks_num FROM viewCheck_Account WHERE acc_num = " + accNum;
        Cursor cursor = db.rawQuery(request,null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<Integer> list = new ArrayList<>();
            while (!cursor.isAfterLast()){
                list.add(cursor.getInt(1));
                cursor.moveToNext();
            }
            return list;
        }
        return null;
    }
}
