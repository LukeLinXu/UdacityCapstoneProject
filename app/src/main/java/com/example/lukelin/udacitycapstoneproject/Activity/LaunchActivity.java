package com.example.lukelin.udacitycapstoneproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.example.lukelin.udacitycapstoneproject.util.Utils;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        String read = Extras.sharedPrefPersistence.read(Extras.IS_LOADED);
        if(read != null && Boolean.valueOf(read)){
            Utils.startMainActivity(this);
        }else {
            Utils.firstLoad(this);
        }
//        FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//
//
//            }
//        });
    }

}
