package com.example.lukelin.udacitycapstoneproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.Utils;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        String read = Extras.sharedPrefPersistence.read(Extras.IS_LOADED);
        if(read != null && Boolean.valueOf(read)){
            Utils.startMainActivity(this);
        }else {
            Utils.firstLoad(this);
        }
    }

}
