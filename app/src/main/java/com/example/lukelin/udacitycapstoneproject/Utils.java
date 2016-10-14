package com.example.lukelin.udacitycapstoneproject;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;

import com.example.lukelin.udacitycapstoneproject.data.RouteColumns;
import com.example.lukelin.udacitycapstoneproject.data.RouteProvider;
import com.example.lukelin.udacitycapstoneproject.data.StopColumns;
import com.example.lukelin.udacitycapstoneproject.data.StopProvider;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Route;
import com.example.lukelin.udacitycapstoneproject.pojos.RouteListResult;
import com.example.lukelin.udacitycapstoneproject.pojos.Stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LukeLin on 2016-10-12.
 */

public class Utils {

    private static ContentProviderOperation buildBatchOperation(Route route){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                RouteProvider.Routes.CONTENT_URI);
        builder.withValue(RouteColumns.TAG, route.getTag());
        builder.withValue(RouteColumns.TITLE, route.getTitle());
        return builder.build();
    }

    private static ContentProviderOperation buildBatchOperation(Stop stop){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                StopProvider.Stops.CONTENT_URI);
        builder.withValue(StopColumns.TAG, stop.getTag());
        builder.withValue(StopColumns.TITLE, stop.getTitle());
        builder.withValue(StopColumns.LAT, stop.getLat());
        builder.withValue(StopColumns.LON, stop.getLon());
        return builder.build();
    }

    public static void firstLoad(final Context context){
        final ConcurrentHashMap<String, List<Stop>> stopHashMap = new ConcurrentHashMap<>();

        RestClient.service.getRouteList("ttc").enqueue(new Callback<RouteListResult>() {
            @Override
            public void onResponse(Call<RouteListResult> call, retrofit2.Response<RouteListResult> response) {
                ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
                final List<Route> routeList = response.body().getRouteList();
                for(final Route route : routeList){
                    batchOperations.add(Utils.buildBatchOperation(route));
                    RestClient.service.getRouteDetail("ttc", route.getTag()).enqueue(new Callback<GetRouteResult>() {
                        @Override
                        public void onResponse(Call<GetRouteResult> call, Response<GetRouteResult> response) {
                            stopHashMap.put(route.getTag(), response.body().getRoute().getStopList());
                            if(stopHashMap.size() == routeList.size()){
                                ArrayList<ContentProviderOperation> batchOperations1 = new ArrayList<>();
                                HashMap<String, Stop> hashMap = new HashMap<String, Stop>();
                                for(List<Stop> stopList : stopHashMap.values()){
                                    for(Stop stop : stopList){
                                        hashMap.put(stop.getTag().replace("_ar", ""), stop);
                                    }
                                }
                                for(Stop stop : hashMap.values()){
                                    batchOperations1.add(Utils.buildBatchOperation(stop));
                                }
                                try {
                                    context.getContentResolver().applyBatch(StopProvider.AUTHORITY,
                                            batchOperations1);
                                    Extras.sharedPrefPersistence.save(Extras.IS_LOADED, String.valueOf(true));
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                    Extras.sharedPrefPersistence.save(Extras.IS_LOADED, String.valueOf(false));
                                } catch (OperationApplicationException e) {
                                    Extras.sharedPrefPersistence.save(Extras.IS_LOADED, String.valueOf(false));
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<GetRouteResult> call, Throwable t) {

                        }
                    });
                }

                try {
                    context.getContentResolver().applyBatch(RouteProvider.AUTHORITY,
                            batchOperations);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RouteListResult> call, Throwable t) {

            }
        });
    }
}
