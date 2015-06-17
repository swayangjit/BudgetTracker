package com.skytopper.budgettracker;

import android.app.Application;
import android.content.Context;

/**
 * Created by swayangjit on 4/22/2015.
 */
public class BudgetTrackerApplication extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
