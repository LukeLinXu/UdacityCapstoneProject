package com.example.lukelin.udacitycapstoneproject.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.util.Utils;

/**
 * Created by lukelin on 2016-10-14.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular);
        Utils.switchFragment(getSupportFragmentManager(), getFragment(), R.id.fragment_container);
    }

    abstract Fragment getFragment();
}
