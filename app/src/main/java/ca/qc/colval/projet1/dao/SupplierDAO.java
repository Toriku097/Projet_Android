package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.Supplier;

public class SupplierDAO implements ISupplierDAO {


    Singleton singleton;
    public SupplierDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Suppilers";
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<Supplier> suppliers = new ArrayList<>();
            while(!cursor.isAfterLast()){
                Supplier supplier = new Supplier();
                supplier.setSupplierID(cursor.getInt(0));
                supplier.setName(cursor.getString(1));


                suppliers.add(supplier);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return suppliers;
        }
        return null;
    }

    @Override
    public Supplier getSupplierbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Suppliers WHERE idf = " + id;
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            Supplier supplier = new Supplier();
            supplier.setSupplierID(cursor.getInt(0));
            supplier.setName(cursor.getString(1));


            db.close();
            cursor.close();
            return supplier;
        }
        return null;
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", supplier.getName());

        int id = (int) db.insert("Suppliers", null, values);
        return getSupplierbyId(id);
    }


    @Override
    public Supplier updateSupplierbyId(int id, Supplier supplier) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", supplier.getName());

        db.update("Suppliers", values, "idf = ?", new String[]{ id+""});
        return getSupplierbyId(id);
    }

    @Override
    public Supplier deleteSupplierbyId(int id) {
        Supplier supplier = getSupplierbyId(id);
        if(supplier != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("Suppliers","idf = ?", new String[]{id + ""});
        }
        return supplier;
    }
}
