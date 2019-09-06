package com.test.weatherapp.network;

import com.test.weatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;
import com.test.weatherapp.data.thirtySixHoursPrediction.instantWeatherStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetWeatherForecast {

    @GET("/api/v1/rest/datastore/F-C0032-001/")
    Call<instantWeatherStatus> getInstantMsg(
        @Query("Authorization") String authentication_key,
        @Query("locationName") String locationName,
        @Query("timeFrom") String timeForm,
        @Query("timeTo") String timeTo
    );


    //F-D0047-003 is for 宜蘭縣
    @GET("/api/v1/rest/datastore/{dataid}/")
    Call<predictWeatherData> getWeatherData(
            @Path("dataid") String dataid,
            @Query("Authorization") String authentication_key,
            @Query("locationName") String locationName,
            @Query("elementName") String[] elementName, //MinCI,MinT,UVI,MaxT,Wind,PoP12h,T,RH,Wx
            @Query("timeFrom") String timeForm,
            @Query("timeTo") String timeTo
    );
}
