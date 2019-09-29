package com.alston.cuteweatherapp;

import android.content.Context;

import java.util.HashMap;

public class WeatherIconMap {

    public static HashMap<String,String> weatherIconMap;
    private Context mCtx;
    public static HashMap<String, String> getWeatherIconMap() {
        return weatherIconMap;
    }

    public WeatherIconMap(HashMap map){
        weatherIconMap = map;
    }
}
