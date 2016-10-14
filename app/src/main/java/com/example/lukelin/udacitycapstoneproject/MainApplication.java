package com.example.lukelin.udacitycapstoneproject;

import android.app.Application;

import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.SharedPrefPersistence;

/**
 * Created by LukeLin on 2016-10-13.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Extras.sharedPrefPersistence = new SharedPrefPersistence(this);
    }
}
