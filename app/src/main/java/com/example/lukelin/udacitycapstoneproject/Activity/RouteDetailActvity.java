package com.example.lukelin.udacitycapstoneproject.Activity;

import android.app.Fragment;

import com.example.lukelin.udacitycapstoneproject.Fragment.RouteDetailFragment;

/**
 * Created by lukelin on 2016-10-14.
 */

public class RouteDetailActvity extends BaseFragmentActivity{

    @Override
    Fragment getFragment() {
        RouteDetailFragment routeDetailFragment = new RouteDetailFragment();
        routeDetailFragment.setArguments(getIntent().getExtras());
        return routeDetailFragment;
    }
}
