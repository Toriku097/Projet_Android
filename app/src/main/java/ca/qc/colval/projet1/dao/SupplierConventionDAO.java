package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.SupplierConvention;

public class SupplierConventionDAO implements ISupplierConventionDAO {

    Singleton singleton;
    public SupplierConventionDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<SupplierConvention> getAllSupplierConventions() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM Supplier_Conventions";
        Cursor cursor = db.rawQuery(request, null);

        if(cursor != null) {
            cursor.moveToFirst();
            List<SupplierConvention> supplierConventions = new ArrayList<>();
            while(!cursor.isAfterLast()) {
                SupplierConvention supplierConvention = new SupplierConvention();
                supplierConvention.setSupplierConventionId(cursor.getInt(0));
                supplierConvention.setConventionId(cursor.getInt(1));
                supplierConvention.setSupplierId(cursor.getInt(2));

                supplierConventions.add(supplierConvention);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return supplierConventions;
        }
        return null;
    }

    @Override
    public SupplierConvention getSupplierConventionById(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM SupplierConventions WHERE idcnf = " + id;
        Cursor cursor = db.rawQuery(request, null);

        if(cursor != null) {
            cursor.moveToFirst();
            SupplierConvention supplierConvention = new SupplierConvention();
            supplierConvention.setSupplierConventionId(cursor.getInt(0));
            supplierConvention.setConventionId(cursor.getInt(1));
            supplierConvention.setSupplierId(cursor.getInt(2));

            db.close();
            cursor.close();
            return supplierConvention;
        }
        return null;
    }

    public SupplierConvention addSupplierConvention(SupplierConvention supplierConvention) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idcnf", supplierConvention.getSupplierConventionId());
        values.put("idc", supplierConvention.getConventionId());
        values.put("idf", supplierConvention.getSupplierId());

        db.insert("Supplier_Conventions", null, values);
        return getSupplierConventionById(supplierConvention.getSupplierConventionId());
    }

    public SupplierConvention updateSupplierConventionById(int id, SupplierConvention supplierConvention) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idc", supplierConvention.getConventionId());
        values.put("idf", supplierConvention.getSupplierId());

        db.update("Supplier_Conventions", values, "idcnf = ?", new String[]{id + ""});
        return getSupplierConventionById(id);
    }

    public SupplierConvention deleteSupplierConventionById(int id) {
        SupplierConvention supplierConvention = getSupplierConventionById(id);
        if(supplierConvention != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("Supplier_Conventions", "idcnf = ?", new String[]{id + ""});
        }
        return supplierConvention;
    }
}
