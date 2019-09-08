package com.test.weatherapp;

import android.util.Log;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public class SevenDaysWeatherData {
    public static ArrayList<String> wXSevenDays;
    public static ArrayList<String> minTSevenDays;
    public static ArrayList<String> maxTSevenDays;
    public static ArrayList<ArrayList<String>> tripleArray= new ArrayList<>();
    private static Integer count;
    private static final String TAG = "SevenDaysWeatherData";

    static {
//        tripleArray.add(wXSevenDays);
//        tripleArray.add(minTSevenDays);
//        tripleArray.add(maxTSevenDays);
        count = 0;
    }

    public static void constructData(ArrayList<String> arrayList) {
            tripleArray.add(arrayList);
    }

    public static ArrayList<ForecastData> getOneWeekForecast(){
        ArrayList<ForecastData> oneWeekForecast = new ArrayList<>();

        for(int i=0;i<7;i++){
            LocalDate ldt = new LocalDate().plusDays(i+1);
            String weekDay = ldt.toString(DateTimeFormat.forPattern("E"));//week day in text
            ForecastData forecastData = new ForecastData(
                weekDay,
                tripleArray.get(0).get(i),
                tripleArray.get(1).get(i),
                tripleArray.get(2).get(i));
            oneWeekForecast.add(forecastData);
        }
        return oneWeekForecast;
    }
}
