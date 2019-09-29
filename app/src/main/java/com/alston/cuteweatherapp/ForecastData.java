package com.alston.cuteweatherapp;

import android.util.Log;

import com.alston.cuteweatherapp.utils.CheckWeatherStatus;

public class ForecastData {
    public String mWeekDay;
    public int mWeatherStatus;
    public String mLowest_tmp;
    public String mHighest_tmp;
    private static String[] wxState = new String[]{"晴","雲","雨"};
//    private HashMap<String,String> weatherIconMap;

    public ForecastData(String weekDay, String weatherStatus, String lowest_tmp, String highest_tmp ){
        // mWeatherStatus looks like  "陰陣雨或雷雨",
        mWeekDay = weekDay;
//        weatherIconMap= WeatherIconMap.getWeatherIconMap();
        Log.d("printwx", weatherStatus);
        mWeatherStatus = CheckWeatherStatus.check(weatherStatus);
        mLowest_tmp = lowest_tmp;
        mHighest_tmp = highest_tmp;

        // Refactor to replace the duplicated if else logic.
        // Need Context var
// Depending on the weather status, choose different image icons.
//        StringBuilder wxStr=new StringBuilder();
//        for (String s : wxState) {
//            if (weatherStatus.contains(s)) {
//                wxStr.append("1");
//            } else {
//                wxStr.append("0");
//            }
//        }
//        wxStr.toString();//key
//        weatherIconMap.get(wxStr);
    }

}
