package com.test.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.test.weatherapp.data.CreateWeatherDataMap;
import com.test.weatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

// import from internal app
import com.test.weatherapp.network.RetrofitQuery;

//3rd party
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class NewLocationFrag extends Fragment {
    private final String TAG = "NewLocationFrag";
    private ArrayAdapter adapter;
    private Context mCtx;
    private String mLoc;
    public NewLocationFrag(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = getActivity();
    }
    private Integer testId = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.scroll_view_base, container, false);
        rootView.setId(testId );
        testId++;
        String location=null;
        Bundle bundle = this.getArguments();
        final ArrayList<String> locationArray;
        if(bundle!=null){
//            location = bundle.getString("location");
//            location = bundle.getString("location");
            ArrayList<String> locs= new ArrayList<>();
            locationArray = bundle.getStringArrayList("locations");
            location = bundle.getString("currentLocation");
//            location = locationArray.get(locationArray.size()-1);
            Log.d("Fragment location", location);
        }
        else {
            locationArray= null;
            //error
        }

        // Right top plus corner binding event
        ImageButton imageButton = rootView.findViewById(R.id.select_location);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle selectLocationBundle = new Bundle();
                selectLocationBundle.putStringArrayList("locations",locationArray);
                Intent activityIntent = new Intent(getActivity(),
                        SelectLocation.class);
                activityIntent.putStringArrayListExtra("locations",locationArray);

                startActivity(activityIntent);
//                getActivity().finish();
            }
        });

        if(location==null) location="宜蘭縣";
        TextView location_title = rootView.findViewById(R.id.location_title);
        location_title.setText(location);

        RetrofitQuery query = new RetrofitQuery(getActivity());
        Call<predictWeatherData> call2= query.GetWeatherData(location);
//        Call<predictWeatherData> call2= query.GetWeatherData(mLoc);
        call2.enqueue(new Callback<predictWeatherData>(){
            @Override
            public void onResponse(Call<predictWeatherData> call, Response<predictWeatherData> response) {
                if(response.isSuccessful()){
                    predictWeatherData weatherStatus = response.body();
                    Map <String,String> weatherDataMap = CreateWeatherDataMap
                            .createWeatherData(weatherStatus);
                    ArrayList<ForecastData> oneWeekForecast= CreateWeatherDataMap.
                            createOneWeekWeatherData(weatherStatus);

                    TextView lowest_tmp_tv =rootView.findViewById(R.id.lowest_tmp_tv);
                    TextView highest_tmp_tv =rootView.findViewById(R.id.highest_tmp_tv );
                    TextView wx=rootView.findViewById(R.id.weather_status);
                    TextView ci = rootView.findViewById(R.id.ci_val);
                    TextView popVal = rootView.findViewById(R.id.pop_value);
                    TextView currentTmp = rootView.findViewById(R.id.current_tmp);
                    TextView humidity = rootView.findViewById(R.id.humidity);
                    TextView windInfo = rootView.findViewById(R.id.windInfo);
                    TextView uviTv = rootView.findViewById(R.id.uvi);
                    String minT = weatherDataMap.get("MinT");
                    String maxT = weatherDataMap.get("MaxT");
                    String temperature = weatherDataMap.get("T");
                    String minCI = weatherDataMap.get("MinCI");
                    String RH = weatherDataMap.get("RH");
                    String weatherDescription = weatherDataMap.get("WeatherDescription");
                    // the response doesn't contain UVI index and wind info
                    String[] parts = weatherDescription.split("。");
                    // workaround, since the response has no wind info.
                    String windData="";
                    if(parts!=null &&parts.length>=4){
                        windData= parts[4];
                    }

                    lowest_tmp_tv.setText(minT);
                    highest_tmp_tv.setText(maxT);
                    ci.setText(minCI);
                    humidity.setText(RH+'%');
                    currentTmp.setText(temperature);
                    wx.setText(weatherDataMap.get("Wx"));
                    popVal.setText(weatherDataMap.get("PoP12h")+"%");
                    uviTv.setText(weatherDataMap.get("UVI"));
                    windInfo.setText(windData);
                    Log.d("query", "Success...");

                    // one week predict data
                    RecyclerView SevenDaysPredictRecyclerView = rootView.findViewById(R.id.recyclerview);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(oneWeekForecast);
                    SevenDaysPredictRecyclerView.setAdapter(recyclerViewAdapter);
                    SevenDaysPredictRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));


                    rootView.findViewById(R.id.weather_layout).setVisibility(View.VISIBLE);
                    rootView.findViewById(R.id.not_found_dinasour_layout).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<predictWeatherData> call, Throwable t) {
                if(t instanceof IOException){   // connection error---> hide all the weather display
                    Log.d(TAG, "IOException");
                    Toast.makeText(mCtx, "Network unavailable", Toast.LENGTH_SHORT)
                            .show();
                }
                Log.d("main", "query error, start debugging");
//                rootView.findViewById(R.id.weather_layout).setVisibility(View.GONE);
            }
        });
//
//        // turbine gif
//        ImageView gifWindTurbine = rootView.findViewById(R.id.wind_turbine);
//        Glide
//        .with(this) // replace with 'this' if it's in activity
//        .load(R.drawable.wind_turbine)  // load the gif file
////        .error(R.drawable.not_found) // show error drawable if the image is not a gif
//        .into(gifWindTurbine);  // the ImageView
        return rootView;
    }


        // Rainfall line chart
//        BarChart rainfallChart = (BarChart) rootView.findViewById(R.id.rainfallchart);
//        List<BarEntry> entries = new ArrayList<BarEntry>();
//        int i = 0;
//        for (i = 0; i < x.length; i++) {
//            entries.add(new BarEntry(x[i], y[i]));
//        }
//        BarDataSet barDataSet = new BarDataSet(entries, "period");
//        BarData data = new BarData(barDataSet);
//        rainfallChart.setData(data);
//        rainfallChart.setFitBars(true);
////
//        public int[] x = new int[]{1, 2, 3};
//        public int[] y = new int[]{10, 100, 300};
//        private final ArrayList<String> xLabels = new ArrayList<>();
//        // XAxis
//        XAxis xAxis = rainfallChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelRotationAngle(3f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setGranularity(1f);
//        // xaxis label format
//        xLabels.add("?");
//        xLabels.add("10(m)");
//        xLabels.add("12(h)");
//        xLabels.add("24(h)");
//
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                Log.d("VALUE", String.valueOf(value));
//                return xLabels.get((int)value);
//            }
//        });
//        rainfallChart.invalidate();
//    }//onCreate method

}
