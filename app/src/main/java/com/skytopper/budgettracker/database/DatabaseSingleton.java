package com.skytopper.budgettracker.database;

import android.content.Context;

import com.skytopper.budgettracker.BudgetTrackerApplication;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    public DatabaseHelper helper;

    private DatabaseSingleton(Context context) {
        helper = new DatabaseHelper(context);
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton(BudgetTrackerApplication.context);
        }
        return instance;
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

}
