package com.test.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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


public class MainActivity extends AppCompatActivity {
    public int[] x = new int[]{1, 2, 3};
    public int[] y = new int[]{10, 100, 300};
    private final ArrayList<String> xLabels = new ArrayList<>();
    private final String TAG = "main";

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_base);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        // Fragment (failed) incorrect use case
        ImageButton imageButton = findViewById(R.id.select_location);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this,
                                            SelectLocationActivity.class);
                startActivity(activityIntent);
            }
        });

        if(location==null) location="宜蘭縣";
        TextView location_title = findViewById(R.id.location_title);
        location_title.setText(location);

        RetrofitQuery query2 = new RetrofitQuery(getBaseContext());

        Call<predictWeatherData> call2= query2.GetWeatherData(location);
        call2.enqueue(new Callback<predictWeatherData>(){
            @Override
            public void onResponse(Call<predictWeatherData> call, Response<predictWeatherData> response) {
                if(response.isSuccessful()){
                    predictWeatherData weatherStatus = response.body();
                    Map <String,String> weatherDataMap = CreateWeatherDataMap
                            .createWeatherData(weatherStatus);
                    TextView lowest_tmp_tv =findViewById(R.id.lowest_tmp_tv);
                    TextView highest_tmp_tv =findViewById(R.id.highest_tmp_tv );
                    TextView wx=findViewById(R.id.weather_status);
                    TextView ci = findViewById(R.id.ci_val);
                    TextView popVal = findViewById(R.id.pop_value);
                    TextView currentTmp = findViewById(R.id.current_tmp);
                    TextView humidity = findViewById(R.id.humidity);
                    TextView windInfo = findViewById(R.id.windInfo);
                    TextView uviTv = findViewById(R.id.uvi);
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
                    findViewById(R.id.weather_layout).setVisibility(View.VISIBLE);
                    findViewById(R.id.not_found_dinasour_layout).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<predictWeatherData> call, Throwable t) {
                if(t instanceof IOException){   // connection error---> hide all the weather display
                    Log.d(TAG, "IOException");
                    Toast.makeText(getBaseContext(), "Network unavailable", Toast.LENGTH_SHORT)
                            .show();
                }
                Log.d("main", "query error, start debugging");
//                findViewById(R.id.weather_layout).setVisibility(View.GONE);
            }
        });
//
//        // turbine gif
        ImageView gifWindTurbine = findViewById(R.id.wind_turbine);
        Glide
        .with(this) // replace with 'this' if it's in activity
        .load(R.drawable.wind_turbine)  // load the gif file
//        .error(R.drawable.not_found) // show error drawable if the image is not a gif
        .into(gifWindTurbine);  // the ImageView


        // Rainfall line chart
//        BarChart rainfallChart = (BarChart) findViewById(R.id.rainfallchart);
//        List<BarEntry> entries = new ArrayList<BarEntry>();
//        int i = 0;
//        for (i = 0; i < x.length; i++) {
//            entries.add(new BarEntry(x[i], y[i]));
//        }
//        BarDataSet barDataSet = new BarDataSet(entries, "period");
//        BarData data = new BarData(barDataSet);
//        rainfallChart.setData(data);
//        rainfallChart.setFitBars(true);
//
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
    }//onCreate method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_view, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

//        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
//
//        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
//        }
//        return super.onCreateOptionsMenu(menu);
        return true;
    }
}// MainActivity
