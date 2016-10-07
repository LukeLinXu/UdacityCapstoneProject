package com.example.lukelin.udacitycapstoneproject;

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
        @GET("publicXMLFeed")
        Call<AgencyList> command(@Query("command") String command);
    }
}
