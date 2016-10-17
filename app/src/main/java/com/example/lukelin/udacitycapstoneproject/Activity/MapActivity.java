package com.example.lukelin.udacitycapstoneproject.Activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.lukelin.udacitycapstoneproject.R;
import com.example.lukelin.udacitycapstoneproject.data.StopColumns;
import com.example.lukelin.udacitycapstoneproject.data.StopProvider;
import com.example.lukelin.udacitycapstoneproject.util.Extras;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

/**
 * Created by LukeLin on 2016-10-15.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CURSOR_LOADER_ID = 0;
    private double lat = Extras.location.getLatitude();
    private double lon = Extras.location.getLongitude();
    private double range = 0.005;
    private GoogleMap map;
    private HashMap<Marker, String> markers = new HashMap<>();
    private FloatingActionButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        button = (FloatingActionButton) findViewById(R.id.map_activity_button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.stops_map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15));
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String id = markers.get(marker);
                startActivity(new Intent(MapActivity.this, StopDetailActvity.class).putExtra(Extras.DATA, id));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraPosition cameraPosition = map.getCameraPosition();
                lat = cameraPosition.target.latitude;
                lon = cameraPosition.target.longitude;
                getLoaderManager().restartLoader(CURSOR_LOADER_ID, null, MapActivity.this);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String latRang = (lat - range) + " AND " + (lat + range);
        String lonRang = (lon - range) + " AND " + (lon + range);
        return new CursorLoader(this, StopProvider.Stops.CONTENT_URI,
                new String[]{StopColumns.TAG, StopColumns.TITLE, StopColumns.LAT, StopColumns.LON, StopColumns.ID},
                "( "+StopColumns.LAT+" BETWEEN "+latRang+")"+
                        " AND "+
                        "( "+StopColumns.LON+" BETWEEN "+lonRang+")",
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        map.clear();
        markers.clear();
        while(data.moveToNext()){
            double lat = data.getDouble(data.getColumnIndex(StopColumns.LAT));
            double lon = data.getDouble(data.getColumnIndex(StopColumns.LON));
            String id = data.getString(data.getColumnIndex(StopColumns.ID));
            String title = data.getString(data.getColumnIndex(StopColumns.TITLE));
            MarkerOptions title1 = new MarkerOptions().position(new LatLng(lat, lon)).title(title);
            Marker marker = map.addMarker(title1);
            markers.put(marker, id);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
