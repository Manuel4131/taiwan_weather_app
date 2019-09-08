package com.test.weatherapp.data;

import android.util.Log;

import com.test.weatherapp.ForecastData;
import com.test.weatherapp.SevenDaysWeatherData;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.*;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.Location;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.WeatherElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateWeatherDataMap {
    private static Map<String, String> weatherDataMaps = new HashMap<String, String>();
    private static String[] attributes = new String[]{"Wx","PoP","MinT","CI","MaxT" };

    private static Map<String, String> weatherDataMap= new HashMap<String, String>();
    private static String[] attrs = new String[]{"PoP12h",
            "T","RH","MinCI","Wx","MinT","UVI", "WeatherDescription","MaxT"};
    private static Map<String, Object> sevenDaysPredict = new HashMap<>();
    private static ArrayList<String> sevenDaysAttr = new ArrayList<String>();

    static {
        sevenDaysAttr.add("Wx");
        sevenDaysAttr.add("MinT");
        sevenDaysAttr.add("MaxT");
    }

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

    public static ArrayList<ForecastData> createOneWeekWeatherData(predictWeatherData wStatus){
        List<WeatherElement> wlist = wStatus.getRecords().
                getLocations().get(0).
                getLocation().get(0).getWeatherElement();
        for(WeatherElement wl: wlist ){
            // extract wx, minT, maxT wether element
            if(sevenDaysAttr.contains(wl.getElementName())){
                // check startTime day is the same as the endTime day
                ArrayList<String> values = new ArrayList<>();
                for (Time t:wl.getTime()){
                    if(t.getStartTime().split(" ")[0].equals(t.getEndTime().split(" ")[0])
                        ){
                        values.add(t.getElementValue().get(0).getValue());
                    }
                }
                SevenDaysWeatherData.constructData(values);
            }

        }
        return SevenDaysWeatherData.getOneWeekForecast();
    }


}
