package com.test.weatherapp.data;

import com.test.weatherapp.data.sevenDaysPrediction.weatherData.*;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.Location;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.WeatherElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateWeatherDataMap {
    private static Map<String, String> weatherDataMaps = new HashMap<String, String>();
    private static String[] attributes = new String[]{"Wx","PoP","MinT","CI","MaxT" };

    private static Map<String, String> weatherDataMap= new HashMap<String, String>();
    private static String[] attrs = new String[]{"PoP12h",
            "T","RH","MinCI","Wx","MinT","UVI", "WeatherDescription","MaxT"};

    public static Map createWeatherData(predictWeatherData wStatus){
        // check null decorators are required to wrap every getter!
        for(int i=0;i<attrs.length;i++)
        {
            WeatherElement wl = wStatus.getRecords().
                    getLocations().get(0).
                    getLocation().get(0).getWeatherElement().get(i);

            String value = wl.getTime().get(0).
                                getElementValue().
                                get(0).getValue();
            weatherDataMap.put(attrs[i],value );
        }
    return weatherDataMap;
    }
}