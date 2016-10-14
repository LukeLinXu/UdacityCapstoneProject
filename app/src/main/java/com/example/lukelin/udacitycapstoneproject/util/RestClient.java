package com.example.lukelin.udacitycapstoneproject.util;

import com.example.lukelin.udacitycapstoneproject.pojos.AgencyListResult;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.PredictionsResult;
import com.example.lukelin.udacitycapstoneproject.pojos.RouteListResult;
import com.example.lukelin.udacitycapstoneproject.pojos.VehicleLocationResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lukelin on 2016-10-07.
 */

public class RestClient {

    private static String BASE_URL = "http://webservices.nextbus.com/service/";

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    public static RestService service = retrofit.create(RestService.class);

    public interface RestService {
        @GET("publicXMLFeed?command=agencyList")
        Call<AgencyListResult> getAgencyList();

        @GET("publicXMLFeed?command=routeList")
        Call<RouteListResult> getRouteList(@Query("a") String agency);

        @GET("publicXMLFeed?command=routeConfig")
        Call<GetRouteResult> getRouteDetail(@Query("a") String agency, @Query("r") String route);

        @GET("publicXMLFeed?command=predictions")
        Call<PredictionsResult> getPredictions(@Query("a") String agency, @Query("stopId") String stopId);

        @GET("publicXMLFeed?command=predictions")
        Call<PredictionsResult> getPredictions(@Query("a") String agency, @Query("stopId") String stopId, @Query("routeTag") String routeTag);

        @GET("publicXMLFeed?command=predictions")
        Call<PredictionsResult> getPredictionsByTags(@Query("a") String agency, @Query("r") String routeTag, @Query("s") String stopTag);

        @GET("publicXMLFeed?command=predictionsForMultiStops")
        Call<PredictionsResult> getPredictionsForMultiStops(@Query("a") String agency, @Query("stops") String... stops);

        @GET("publicXMLFeed?command=vehicleLocations")
        Call<VehicleLocationResult> getVehicleLocation(@Query("a") String agency, @Query("r") String routeTag, @Query("t") long epochTime);
    }
}
