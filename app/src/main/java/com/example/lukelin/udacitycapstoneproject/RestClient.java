package com.example.lukelin.udacitycapstoneproject;

import com.example.lukelin.udacitycapstoneproject.pojos.AgencyList;
import com.example.lukelin.udacitycapstoneproject.pojos.GetRouteResult;
import com.example.lukelin.udacitycapstoneproject.pojos.RouteList;

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
        Call<AgencyList> getAgencyList();

        @GET("publicXMLFeed?command=routeList")
        Call<RouteList> getRouteList(@Query("a") String agency);

        @GET("publicXMLFeed?command=routeConfig")
        Call<GetRouteResult> getRouteDetail(@Query("a") String agency, @Query("r") String route);
    }
}
