package com.alston.cuteweatherapp.utils;

import android.graphics.drawable.Drawable;

import com.alston.cuteweatherapp.R;

public class CheckWeatherStatus {
    private static String weatherStatus;
    private int weatherDrawable;

    public static int check(String weatherStatus){
        weatherStatus = weatherStatus.replace('陰', '雲');
        if (weatherStatus.contains("晴")){
            if(weatherStatus.contains("雲")){
                if(weatherStatus.contains("雨")){
                    return R.drawable.sun_cloud_rain;
                }
                else
                {
                    return R.drawable.sun_cloud;
                }
            }
            else//no cloud
            {
                if(weatherStatus.contains("雨")){
                    return R.drawable.sun_rain;
                }
                else
                {
                    return R.drawable.sunny_icon;
                }

            }
        }
        else    //no sun
        {
            if(weatherStatus.contains("雲")){
                if(weatherStatus.contains("雨")){
                    return R.drawable.rain_icon;
                }
                else
                {
                    return R.drawable.cloudy;
                }

            }
            else    // no cloud
            {
                // no sun, cloud, rain doesn't exist. So here has only one condition.
                return R.drawable.rain_icon;
            }
        }
    }

    // workaround, duplicated the same method
    public static int checkBigIcon(String weatherStatus){
        weatherStatus = weatherStatus.replace('陰', '雲');
        if (weatherStatus.contains("晴")){
            if(weatherStatus.contains("雲")){
                if(weatherStatus.contains("雨")){
                    return R.drawable.sun_cloud_rain;
                }
                else
                {
                    return R.drawable.big_sun_with_cloud;
                }
            }
            else//no cloud
            {
                if(weatherStatus.contains("雨")){
                    return R.drawable.sun_rain;
                }
                else
                {
                    return R.drawable.sun_icon;
                }

            }
        }
        else    //no sun
        {
            if(weatherStatus.contains("雲")){
                if(weatherStatus.contains("雨")){
                    return R.drawable.big_rain;
                }
                else
                {
                    return R.drawable.big_cloudy;
                }

            }
            else    // no cloud
            {
                // no sun, cloud, rain doesn't exist. So here has only one condition.
                return R.drawable.rain_icon;
            }
        }
    }
}
