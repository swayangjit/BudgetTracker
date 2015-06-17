package com.skytopper.budgettracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "budgettracker.db";
    private static final int VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DatabaseTable.init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
