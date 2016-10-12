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

import com.example.lukelin.udacitycapstoneproject.pojos.AgencyListResult;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.PredictionsResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Route;
import com.example.lukelin.udacitycapstoneproject.pojos.RouteListResult;
import com.example.lukelin.udacitycapstoneproject.pojos.VehicleLocationResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        RestClient.service.getAgencyList().enqueue(new Callback<AgencyListResult>() {
                            @Override
                            public void onResponse(Call<AgencyListResult> call, retrofit2.Response<AgencyListResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<AgencyListResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;

                    case 1:
                        RestClient.service.getRouteList("ttc").enqueue(new Callback<RouteListResult>() {
                            @Override
                            public void onResponse(Call<RouteListResult> call, retrofit2.Response<RouteListResult> response) {
                                for(Route route : response.body().getRouteList()){
                                    RestClient.service.getRouteDetail("ttc", route.getTag()).enqueue(new Callback<GetRouteResult>() {
                                        @Override
                                        public void onResponse(Call<GetRouteResult> call, Response<GetRouteResult> response) {
                                            Log.d(TAG, "LukeonResponse: " + response.body().getRoute().getTag());
                                        }

                                        @Override
                                        public void onFailure(Call<GetRouteResult> call, Throwable t) {
                                            Log.d(TAG, "LukeonFaile: " + t.getMessage());
                                        }
                                    });
                                }
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<RouteListResult> call, Throwable t) {
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
                    case 3:
                        RestClient.service.getPredictions("ttc", "2488").enqueue(new Callback<PredictionsResult>() {
                            @Override
                            public void onResponse(Call<PredictionsResult> call, retrofit2.Response<PredictionsResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<PredictionsResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;
                    case 4:
                        RestClient.service.getPredictions("ttc", "2488", "199").enqueue(new Callback<PredictionsResult>() {
                            @Override
                            public void onResponse(Call<PredictionsResult> call, retrofit2.Response<PredictionsResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<PredictionsResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;
                    case 5:
                        RestClient.service.getPredictionsByTags("ttc", "199", "5464").enqueue(new Callback<PredictionsResult>() {
                            @Override
                            public void onResponse(Call<PredictionsResult> call, retrofit2.Response<PredictionsResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<PredictionsResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;
                    case 6:
                        RestClient.service.getPredictionsForMultiStops("ttc", new String[]{"199|5464", "39|5950"}).enqueue(new Callback<PredictionsResult>() {
                            @Override
                            public void onResponse(Call<PredictionsResult> call, retrofit2.Response<PredictionsResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<PredictionsResult> call, Throwable t) {
                                Log.d(TAG, "onFailure: ");
                            }
                        });
                        break;
                    case 7:
                        RestClient.service.getVehicleLocation("ttc", "199", 0).enqueue(new Callback<VehicleLocationResult>() {
                            @Override
                            public void onResponse(Call<VehicleLocationResult> call, retrofit2.Response<VehicleLocationResult> response) {
                                Log.d(TAG, "onResponse: ");
                            }

                            @Override
                            public void onFailure(Call<VehicleLocationResult> call, Throwable t) {
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
