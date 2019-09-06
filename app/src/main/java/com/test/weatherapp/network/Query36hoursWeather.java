//package com.test.weatherapp.network;
//
//import android.util.Log;
//import android.widget.TextView;
//
//import com.test.weatherapp.R;
//import com.test.weatherapp.data.thirtySixHoursPrediction.instantWeatherStatus;
//import com.test.weatherapp.data.CreateWeatherDataMap;
//
//import org.joda.time.LocalDateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
//
//import java.util.Map;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//
//public class Query36hoursWeather {
//
//    public Query36hoursWeather(String location){
//
//    GetWeatherForecast client = retrofit.build().create(GetWeatherForecast.class);
//
//    LocalDateTime ldt = new LocalDateTime();
//    DateTimeFormatter dateObj = DateTimeFormat.forPattern("yyyy-MM-dd");
//    DateTimeFormatter timeObj = DateTimeFormat.forPattern("hh-mm-ss");
//
//    String dayStr = dateObj.print(ldt);
//    String timeStr = timeObj.print(ldt);
//    String startTime = dayStr+"T"+timeStr;
//    final String endOfTime="23:59:59";
//    String endTime = dayStr + "T"+endOfTime;
//        Log.d("startTime", startTime);
//        Log.d("endTime", endTime);
//
//
//
//    Call<instantWeatherStatus> call = client.getInstantMsg(
//            "CWB-9EDAA29C-A9C4-4B6F-A05B-F2AA78FB03B6",
//            "宜蘭縣",
////                "2019-08-26T15:20:00",
////                "2019-08-26T23:59:59"
//            startTime,
//            endOfTime
//    );
//
//        call.enqueue(new Callback<instantWeatherStatus>(){
//        @Override
//        public void onResponse(Call<instantWeatherStatus> call, Response<instantWeatherStatus> response) {
//            if(response.isSuccessful()){
//                instantWeatherStatus weatherStatus = response.body();
//                Map<String,String> weatherDataMap = CreateWeatherDataMap.create(weatherStatus);
//
//                TextView lowest_tmp_tv =findViewById(R.id.lowest_tmp_tv);
//                TextView highest_tmp_tv =findViewById(R.id.highest_tmp_tv );
//                TextView wx=findViewById(R.id.weather_status);
//                TextView feelsLike = findViewById(R.id.ci_val);
//                TextView popVal = findViewById(R.id.pop_value);
////                    String s = weatherDataMap.get("MinT");
//                lowest_tmp_tv.setText(weatherDataMap.get("MinT"));
//                highest_tmp_tv.setText(weatherDataMap.get("MaxT"));
//                wx.setText(weatherDataMap.get("Wx"));
//                feelsLike.setText(weatherDataMap.get("CI"));
//                popVal.setText(weatherDataMap.get("PoP")+"%");
//
//
//                Log.d("query", "Success...");
//            }
//        }
//
//        @Override
//        public void onFailure(Call<instantWeatherStatus> call, Throwable t) {
//            Log.d("query", "query error, start debugging");
//        }
//    });
//}
