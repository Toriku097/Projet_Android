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
        db.execSQL("CREATE TABLE IF NOT EXISTS Expenses (idd INTEGER PRIMARY KEY, expense_type TEXT, amount REAL, payment_method TEXT, idcb INTEGER, idf INTEGER, FOREIGN KEY(idcb) REFERENCES BankAccount(idcb), FOREIGN KEY(idf) REFERENCES Suppliers(idf))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Projects (idp INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Spending_History (idh INTEGER PRIMARY KEY, idd INTEGER, idp INTEGER, date TEXT, FOREIGN KEY(idd) REFERENCES Expenses(idd), FOREIGN KEY(idp) REFERENCES Projects(idp))");
        db.execSQL("INSERT INTO Conventions (idc, name) VALUES (1, 'Maconnerie'), (2, 'Plomberie'), (3, 'Electricite'), (4, 'Materiaux de construction')");
        db.execSQL("INSERT INTO BankAccounts (idcb, acc_num, bank) VALUES (1, 'BC1_RBC Scott', 'RBC'), (2, 'BC2_BN', 'BN')");
        db.execSQL("INSERT INTO Projects (idp, name) VALUES (1, 'Les Jardins Mercier'), (2, 'YUL Condominiums')");
    }

    /* public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ComptesBancaires (idcb INTEGER PRIMARY KEY, no_compte TEXT, banque TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Conventions (idc INTEGER PRIMARY KEY, nom TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Fournisseurs (idf INTEGER PRIMARY KEY, nom TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Fournisseurs_Conventions (idcnf INTEGER PRIMARY KEY, idc INTEGER, idf INTEGER, FOREIGN KEY(idc) REFERENCES Convention(idc), FOREIGN KEY (idf) REFERENCES Fournisseurs(idf))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Depenses (idd INTEGER PRIMARY KEY, type_depense TEXT, montant REAL, mode_paiement TEXT, idcb INTEGER, idf INTEGER, FOREIGN KEY(idcb) REFERENCES ComptesBancaires(idcb), FOREIGN KEY(idf) REFERENCES Fournisseurs(idf))");
        db.execSQL("CREATE TABLE IF NOT EXISTS Projets (idp INTEGER PRIMARY KEY, nom TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS HistoriqueDepenses (idh INTEGER PRIMARY KEY, idd INTEGER, idp INTEGER, date TEXT, FOREIGN KEY(idd) REFERENCES Depenses(idd), FOREIGN KEY(idp) REFERENCES Projets(idp))");
        db.execSQL("INSERT INTO Conventions (idc, nom) VALUES (1, 'Maconnerie'), (2, 'Plomberie'), (3, 'Electricite'), (4, 'Materiaux de construction')");
        db.execSQL("INSERT INTO ComptesBancaires (idcb, no_compte, banque) VALUES (1, 'BC1_RBC Scott', 'RBC'), (2, 'BC2_BN', 'BN')");
        db.execSQL("INSERT INTO Projets (idp, nom) VALUES (1, 'Les Jardins Mercier'), (2, 'YUL Condominiums')");
    } */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
