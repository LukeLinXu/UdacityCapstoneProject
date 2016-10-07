package com.example.lukelin.udacitycapstoneproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lukelin.udacitycapstoneproject.pojos.AgencyList;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.RouteList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                switch (time){
                    case 0:
                        RestClient.service.getAgencyList().enqueue(new Callback<AgencyList>() {
                            @Override
                            public void onResponse(Call<AgencyList> call, retrofit2.Response<AgencyList> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<AgencyList> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;

                    case 1:
                        RestClient.service.getRouteList("ttc").enqueue(new Callback<RouteList>() {
                            @Override
                            public void onResponse(Call<RouteList> call, retrofit2.Response<RouteList> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<RouteList> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;

                    case 2:
                        RestClient.service.getRouteDetail("ttc", "199").enqueue(new Callback<GetRouteResult>() {
                            @Override
                            public void onResponse(Call<GetRouteResult> call, retrofit2.Response<GetRouteResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<GetRouteResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;
                }
                time++;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
