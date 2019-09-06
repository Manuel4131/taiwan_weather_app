package com.test.weatherapp.data;

import android.util.Log;

import com.test.weatherapp.data.thirtySixHoursPrediction.WBIntervals;
import com.test.weatherapp.data.thirtySixHoursPrediction.WeatherElement;

import java.util.List;

public class weatherModel {

    public String location;
    public String attribute;
    public String startTime;
    public String endTime;
    public String value;

    public weatherModel(WeatherElement wl){
        attribute = wl.getElementName();      // Wx, Pop12,MinT,
        List<WBIntervals> WBIntervalsList = wl.getTime();
        WBIntervals currentInterval= WBIntervalsList.get(0);    // Current time interval
        startTime= currentInterval.getStartTime();      // 2019-08-28 12:00:00
        endTime= currentInterval.getEndTime();          //2019-08-28 18:00:00
        value = currentInterval.parameter.getParameterName(); //晴時多雲
    }

    public void PrintInfo(){
        Log.d("display",
                attribute + " ["+
                        startTime + "-" + endTime +"] "+value);

    }

}
