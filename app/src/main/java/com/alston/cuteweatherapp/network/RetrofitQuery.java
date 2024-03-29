package com.alston.cuteweatherapp.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alston.cuteweatherapp.config.Constant;
import com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;
import com.alston.cuteweatherapp.data.thirtySixHoursPrediction.instantWeatherStatus;
import com.alston.cuteweatherapp.utils.BuildDataMap;
import com.alston.cuteweatherapp.utils.accessProperty;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RetrofitQuery {
    private final String TAG = "RetrofitQuery";
    private GetWeatherForecast client;
    private Context ctx;
    private String[] elementName = new String[]{"MinCI","MinT",
                                    "UVI","MaxT","Wind","PoP12h",
                                    "T","RH","Wx","WeatherDescription"};
    // create retrofit instance
    public RetrofitQuery(Context ctx){
        this.ctx = ctx;
        Retrofit.Builder retrofit= RetrofitClientInstance.getRetrofitInstance();
        client = retrofit.build().
                    create(GetWeatherForecast.class);
    }

    public Call<predictWeatherData> GetWeatherData(@NonNull String location) {
        LocalDateTime ldt = new LocalDateTime();
        DateTimeFormatter dateObj = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter timeObj = DateTimeFormat.forPattern("HH-mm-ss");

        // generate query time string
        String dayStr = dateObj.print(ldt);
        String timeStr = timeObj.print(ldt);
        String startTime = dayStr+"T"+timeStr;
        final String endOfTime="23:59:59";
        String endTime = dayStr + "T"+endOfTime;
        Log.d("startTime", startTime);
        Log.d("endTime", endTime);

//        String api_key = accessProperty.get(ctx,"CWB_API_TOKEN");
        String api_key = Constant.cwbOpenApiToken;
        Map<String, String> locationIdMap = new HashMap<String, String>();
        BuildDataMap buildDataMap = new BuildDataMap(ctx);
        Map<String,String> countyTownMap = new HashMap<>();

        buildDataMap.buildMapFromAssets(locationIdMap,"dataidmap");
        buildDataMap.buildMapFromAssets(countyTownMap, "countyTown");
        String dataid = locationIdMap.get(location);
        String defaultTown= countyTownMap.get(location);
        Log.d(TAG, String.valueOf(location));
        // location to dataid mapping is required here
        // test use, the default location is set to be 宜蘭縣
        Call<predictWeatherData> call = client.getWeatherData(
                dataid,
                api_key,
                defaultTown,
                elementName,
                startTime,
                endOfTime
        );

        return call;

    }

    // depreacted
    public Call<instantWeatherStatus> WxStatus(@NonNull String location){
        LocalDateTime ldt = new LocalDateTime();
        DateTimeFormatter dateObj = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter timeObj = DateTimeFormat.forPattern("hh-mm-ss");


        // generate query time string
        String dayStr = dateObj.print(ldt);
        String timeStr = timeObj.print(ldt);
        String startTime = dayStr+"T"+timeStr;
        final String endOfTime="23:59:59";
        String endTime = dayStr + "T"+endOfTime;
        Log.d("startTime", startTime);
        Log.d("endTime", endTime);
        String api_key = accessProperty.get(ctx,"CWB_API_TOKEN");

        // after 7 days
        String after7days = dateObj.print(ldt.plusDays(7) )+ "T" + "23:59:59";
        endTime = after7days;
        // test use, the default location is set to be 宜蘭縣
        Call<instantWeatherStatus> call = client.getInstantMsg(
                api_key,
                location,
                startTime,
                endOfTime
        );

        return call;

    }
}
