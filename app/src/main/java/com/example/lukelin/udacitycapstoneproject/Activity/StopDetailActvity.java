package com.example.lukelin.udacitycapstoneproject.Activity;


import android.support.v4.app.Fragment;

import com.example.lukelin.udacitycapstoneproject.Fragment.StopDetailFragment;

/**
 * Created by lukelin on 2016-10-14.
 */

public class StopDetailActvity extends BaseFragmentActivity{

    @Override
    Fragment getFragment() {
        StopDetailFragment stopDetailFragment = new StopDetailFragment();
        stopDetailFragment.setArguments(getIntent().getExtras());
        return stopDetailFragment;
    }
}
