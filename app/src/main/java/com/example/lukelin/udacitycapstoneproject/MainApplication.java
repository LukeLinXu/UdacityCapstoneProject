package com.example.lukelin.udacitycapstoneproject;

import android.app.Application;

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
