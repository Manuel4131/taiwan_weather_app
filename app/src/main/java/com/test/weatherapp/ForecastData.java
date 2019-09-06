package com.test.weatherapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.Resource;

public class ForecastData {
    public String mWeekDay;
    public int mWeatherStatus;
    public String mLowest_tmp;
    public String mHighest_tmp;

    public ForecastData(String weekDay, String weatherStatus, String lowest_tmp, String highest_tmp ){
        // mWeatherStatus looks like  "陰陣雨或雷雨",
        mWeekDay = weekDay;
        // Depending on the weather status, choose different image icons.
        if(weatherStatus.contains("陰")) // testing
        {
            mWeatherStatus = R.drawable.cloudy;
        }
        else if(weatherStatus.contains("雨")){
            mWeatherStatus = R.drawable.rain;
        }

        mLowest_tmp = lowest_tmp;
        mHighest_tmp = highest_tmp;
    }

}
