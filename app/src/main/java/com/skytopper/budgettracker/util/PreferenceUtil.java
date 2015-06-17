package com.skytopper.budgettracker.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by swayangjit on 6/11/2015.
 */
public class PreferenceUtil {

    public static final String	SHARED_PREF_NAME= "BudgetTracker";

    /**
     * Stores key value pair to shared preference
     * @param context
     * @param key
     * @param value
     */
    public static void storePreference(Context context, String key, String value)
    {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit();
        edit.putString(key, value);
        edit.commit();
    }

    /**
     * Get value for given key from shared preference
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getPreference(Context context, String key, String defValue)
    {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(key, defValue);
    }
    /**
     * Clears the Preference
     * @param context
     */
    public static void clearPrefrence(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
