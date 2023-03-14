package ca.qc.colval.projet1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //placeholder name
    public DBHelper(@Nullable Context context) {
        super(context, "ProjetDB", null,  1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS BankAccounts (idcb INTEGER PRIMARY KEY, acc_num TEXT, bank TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Conventions (idc INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Suppliers (idf INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Suppliers_Conventions (idcnf INTEGER PRIMARY KEY, idc INTEGER, idf INTEGER, FOREIGN KEY(idc) REFERENCES Convention(idc), FOREIGN KEY (idf) REFERENCES Suppliers(idf))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Expenses (idd INTEGER PRIMARY KEY, expense_type TEXT, amount REAL, payment_method TEXT, idcb INTEGER, idf INTEGER, idp INTEGER, FOREIGN KEY(idcb) REFERENCES BankAccount(idcb), FOREIGN KEY(idf) REFERENCES Suppliers(idf), FOREIGN KEY(idp) REFERENCES Projects(idp))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Projects (idp INTEGER PRIMARY KEY, name TEXT, totalEx REAL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Checks (idch INTEGER PRIMARY KEY, check_num INTEGER, date TEXT, amount REAL, idd INTEGER, idp INTEGER, idcb INTEGER, isPaid INTEGER, FOREIGN KEY(idd) REFERENCES Expenses(idd), FOREIGN KEY(idp) REFERENCES Projects(idp), FOREIGN KEY(idch) REFERENCES BankAccounts(idch))");
        db.execSQL("INSERT INTO Conventions (name) VALUES ('Maconnerie'), ('Plomberie'), ('Electricite'), ('Materiaux de construction'), ('Systemes intelligents')");
        db.execSQL("INSERT INTO Suppliers (name) VALUES ('Buildmate'), ('Constructronix'), ('SteelScape'), ('Concretopia'), ('LumberLink'), ('PlumbingPro')");
        db.execSQL("INSERT INTO BankAccounts (acc_num, bank) VALUES ('BC1_RBC Scott', 'RBC'), ('BC2_BN', 'BN')");
        db.execSQL("INSERT INTO Projects (name) VALUES ('Les Jardins Mercier'), ('YUL Condominiums')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS BankAccounts");
        db.execSQL("DROP TABLE IF EXISTS Conventions");
        db.execSQL("DROP TABLE IF EXISTS Suppliers");
        db.execSQL("DROP TABLE IF EXISTS Suppliers_Conventions");
        db.execSQL("DROP TABLE IF EXISTS Expenses");
        db.execSQL("DROP TABLE IF EXISTS Projects");
        db.execSQL("DROP TABLE IF EXISTS Checks");
        onCreate(db);

    }


}
