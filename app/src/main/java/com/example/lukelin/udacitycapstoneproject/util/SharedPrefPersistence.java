package com.example.lukelin.udacitycapstoneproject.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by Luke Lin on 2016-10-13.
 */
public class SharedPrefPersistence {

    private final SharedPreferences prefs;


    public SharedPrefPersistence(Context context) {
        prefs = context.getSharedPreferences(SharedPrefPersistence.class.getSimpleName(), Context.MODE_PRIVATE);
    }

    public void save(@NonNull String key, @NonNull String value) {
        prefs.edit().putString(key, value).apply();
    }


    public String read(@NonNull String key) {
        return prefs.getString(key, null);
    }

    public void removeKey(String key) {
        prefs.edit().remove(key).apply();
    }
}
