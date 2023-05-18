package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.Contract;

public class ConventionDAO implements IConventionDAO{

    Singleton singleton;
    public ConventionDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

//    @Override
//    public List<Convention> getAllConventions() {
//        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
//        String request = "SELECT * FROM Conventions";
//        Cursor cursor = db.rawQuery(request, null);
//
//        if(cursor != null) {
//            cursor.moveToFirst();
//            List<Convention> conventions = new ArrayList<>();
//            while(!cursor.isAfterLast()) {
//                Convention convention = new Convention();
//                convention.setConventionId(cursor.getInt(0));
//                convention.setName(cursor.getString(1));
//
//                conventions.add(convention);
//                cursor.moveToNext();
//            }
//            db.close();
//            cursor.close();
//            return conventions;
//        }
//        return null;
//    }

    @Override
    public Contract getContractbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Conventions WHERE idc = " + id;
        Cursor cursor = db.rawQuery(request, null);

        if(cursor != null) {
            cursor.moveToFirst();
            Contract contract = new Contract();
            contract.setContractId(cursor.getInt(0));
            contract.setName(cursor.getString(1));

            db.close();
            cursor.close();
            return contract;
        }
        return null;
    }

//    public Convention addConvention(Convention convention) {
//        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("idc", convention.getConventionId());
//        values.put("name", convention.getName());
//
//        db.insert("Conventions", null, values);
//        return getConventionbyId(convention.getConventionId());
//    }
//
//    public Convention updateConventionbyId(int id, Convention convention) {
//        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", convention.getName());
//
//        db.update("Conventions", values, "idc = ?", new String[]{id + ""});
//        return getConventionbyId(id);
//    }
//
//    public Convention deleteConventionbyId(int id) {
//        Convention convention = getConventionbyId(id);
//        if(convention != null) {
//            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
//            db.delete("Conventions", "idc = ?", new String[]{id + ""});
//        }
//        return convention;
//    }
}
