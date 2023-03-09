package ca.qc.colval.projet1.dao;

import android.content.Context;

public class Singleton {
    private static Singleton singleInstance;
    public DBHelper helper;
    private Singleton(Context context) {
        helper = new DBHelper(context);
    }

    public static Singleton getSingleInstance(Context context) {
        if (singleInstance == null){
            singleInstance = new Singleton(context);
        }
        return singleInstance;
    }
}
