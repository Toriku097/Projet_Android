package ca.qc.colval.projet1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.entities.BankAccount;

public class BankAccountDAO implements IBankAccountDAO {
    Singleton singleton;
    public BankAccountDAO(Context context) {
        this.singleton = Singleton.getSingleInstance(context);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM BankAccounts";
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            List<BankAccount> bankAccounts = new ArrayList<>();
            while(!cursor.isAfterLast()){
                BankAccount account = new BankAccount();
                account.setAccountId(cursor.getInt(0)); //id
                account.setNoAccount(cursor.getString(1)); //acc num
                account.setBank(cursor.getString(2)); //bank
                //add to list
                bankAccounts.add(account);
                cursor.moveToNext();
            }
            db.close();
            cursor.close();
            return bankAccounts;
        }
        return null;
    }

    @Override
    public BankAccount getBankAccountbyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM BankAccounts WHERE idcb = " + id;
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            BankAccount account = new BankAccount();
            account.setAccountId(cursor.getInt(0));
            account.setNoAccount(cursor.getString(1));
            account.setBank(cursor.getString(2));


            db.close();
            cursor.close();
            return account;
        }
        return null;
    }

    public String getBankAccountNobyId(int id) {
        SQLiteDatabase db = this.singleton.helper.getReadableDatabase();
        String request = "SELECT * FROM BankAccounts WHERE idcb = " + id;
        Cursor cursor = db.rawQuery(request, null);
        if(cursor != null) {
            cursor.moveToFirst();
            BankAccount account = new BankAccount();
            account.setAccountId(cursor.getInt(0));
            account.setNoAccount(cursor.getString(1));
            account.setBank(cursor.getString(2));


            db.close();
            cursor.close();
            return account.getNoAccount();
        }
        return null;

    }

    @Override
    public BankAccount addBankAccount(BankAccount account) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idcb", account.getAccountId());
        values.put("acc_num", account.getNoAccount());
        values.put("bank", account.getBank());


        int id = (int) db.insert("BankAccounts", null, values);
        return getBankAccountbyId(id);
    }


    @Override
    public BankAccount updateBankAccountbyId(int id, BankAccount account) {
        SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("acc_num", account.getNoAccount());
        values.put("bank", account.getBank());


        db.update("BankAccounts", values, "idcb = ?", new String[]{ id+""});
        return getBankAccountbyId(id);
    }

    @Override
    public BankAccount deleteBankAccountbyId(int id) {
        BankAccount account = getBankAccountbyId(id);
        if(account != null) {
            SQLiteDatabase db = this.singleton.helper.getWritableDatabase();
            db.delete("BankAccount","idcb = ?", new String[]{id + ""});
        }
        return account;
    }

}
