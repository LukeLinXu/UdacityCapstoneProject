package com.example.lukelin.udacitycapstoneproject.Activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lukelin.udacitycapstoneproject.Fragment.FavoriteListFragment;
import com.example.lukelin.udacitycapstoneproject.Fragment.RouteListFragment;
import com.example.lukelin.udacitycapstoneproject.Fragment.SurroundingStopFragment;
import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SimpleAdapter adapter;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Extras.location = location;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                500, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 5000,
                500, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000,
                500, mLocationListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager();
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
        AdView mAdView = (AdView) findViewById(R.id.adView);
        if(mAdView != null){
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
//                startActivity(new Intent(MainActivity.this, SearchActivity.class).putExtra(Extras.SORT_OPTIONS,
//                        currentSortOption));
                break;
            case R.id.sort:
                startActivity(new Intent(this, MapActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        adapter = new SimpleAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoriteListFragment(), "Favorite");
        adapter.addFragment(new SurroundingStopFragment(), "Surround");
        adapter.addFragment(new RouteListFragment(), "RouteList");
        viewPager.setAdapter(adapter);
    }

    class SimpleAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SimpleAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}