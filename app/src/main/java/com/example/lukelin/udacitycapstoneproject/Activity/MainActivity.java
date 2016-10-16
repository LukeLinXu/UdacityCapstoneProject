package com.example.lukelin.udacitycapstoneproject.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lukelin.udacitycapstoneproject.Fragment.FavoriteListFragment;
import com.example.lukelin.udacitycapstoneproject.Fragment.RouteListFragment;
import com.example.lukelin.udacitycapstoneproject.Fragment.SurroundingStopFragment;
import com.example.lukelin.udacitycapstoneproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int SORT_OPTIONS_BEST = 0;
    public static final int SORT_ALPHA = 1;
    private int currentSortOption = SORT_OPTIONS_BEST;
    private ViewPager viewPager;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager();
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.search:
//                startActivity(new Intent(MainActivity.this, SearchActivity.class).putExtra(Extras.SORT_OPTIONS,
//                        currentSortOption));
//                break;
//            case R.id.sort:
//                showSortingOptions(MainActivity.this, currentSortOption);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
