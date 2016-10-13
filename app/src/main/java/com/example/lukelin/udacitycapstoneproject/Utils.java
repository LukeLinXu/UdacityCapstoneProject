package com.example.lukelin.udacitycapstoneproject;

import android.content.ContentProviderOperation;

import com.example.lukelin.udacitycapstoneproject.data.RouteColumns;
import com.example.lukelin.udacitycapstoneproject.data.RouteProvider;
import com.example.lukelin.udacitycapstoneproject.data.StopColumns;
import com.example.lukelin.udacitycapstoneproject.data.StopProvider;
import com.example.lukelin.udacitycapstoneproject.pojos.Route;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;

/**
 * Created by LukeLin on 2016-10-12.
 */

public class Utils {

    public static ContentProviderOperation buildBatchOperation(Route route){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                RouteProvider.Routes.CONTENT_URI);
        builder.withValue(RouteColumns.TAG, route.getTag());
        builder.withValue(RouteColumns.TITLE, route.getTitle());
        return builder.build();
    }

    public static ContentProviderOperation buildBatchOperation(Stop stop){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                StopProvider.Stops.CONTENT_URI);
        builder.withValue(StopColumns.TAG, stop.getTag());
        builder.withValue(StopColumns.TITLE, stop.getTitle());
        builder.withValue(StopColumns.LAT, stop.getLat());
        builder.withValue(StopColumns.LON, stop.getLon());
        return builder.build();
    }
}
