package com.alston.cuteweatherapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alston.cuteweatherapp.data.CreateWeatherDataMap;
import com.alston.cuteweatherapp.data.sevenDaysPrediction.weatherData.predictWeatherData;
import com.alston.cuteweatherapp.network.RetrofitQuery;
import com.alston.cuteweatherapp.utils.CheckWeatherStatus;
import com.alston.cuteweatherapp.utils.CuteWeatherDebugTree;
import com.alston.cuteweatherapp.utils.MapData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.alston.cuteweatherapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// import from internal app
import com.alston.cuteweatherapp.utils.BuildDataMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class NewLocationFrag extends Fragment {
    private final String TAG = "NewLocationFrag";

    private Context mCtx;
    private String mLoc;
    private ArrayList<String> locationArray;
    private Map<String,String> twEngCountyMap;
//    private RecyclerView SevenDaysPredictRecyclerView;
    private Drawable drawable;

    private RecyclerViewAdapter recyclerViewAdapter;
    private ScrollView scrollView;
    private  ViewGroup rootView=null;

    public void setmLocTitle(TextView mLocTitle) {
        this.mLocTitle = mLocTitle;
    }

    private TextView mLocTitle;

    // Empty constructor is required in Fragment according to document
    public NewLocationFrag(){}

    // butterknife bindview
    @BindView(R.id.recyclerview) RecyclerView SevenDaysPredictRecyclerView;
    @BindView(R.id.lowest_tmp_tv) TextView lowest_tmp_tv;
    @BindView(R.id.highest_tmp_tv) TextView highest_tmp_tv;
    @BindView(R.id.weather_status) TextView wx;
    @BindView(R.id.ci_val) TextView ci;
    @BindView(R.id.pop_value) TextView popVal;
    @BindView(R.id.current_tmp) TextView currentTmp;
    @BindView(R.id.humidity) TextView humidity;
    @BindView(R.id.windInfo) TextView windInfo;
    @BindView(R.id.uvi) TextView uviTv;
    @BindView(R.id.weather_icon) ImageView weatherState;
    @BindView(R.id.weather_detail_icon) ImageView weatherDetailState;
    @BindView(R.id.default_loc) TextView defaultLoc;
    @BindView(R.id.wind_turbine) ImageView gifWindTurbine;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create timber tree
//        if (BuildConfig.DEBUG) {
////            Timber.plant(new Timber.DebugTree());
////        }
        if (BuildConfig.DEBUG) {
            Timber.plant(new CuteWeatherDebugTree());
        } else {
            //TODO plant the Production Tree
        }
        // first to create due to multiple use are required later.
        mCtx = getActivity();

        // generate Chinese to English county map which is required for resource
        //        identity
        twEngCountyMap = new HashMap<>();
        BuildDataMap buildDataMap = new BuildDataMap(mCtx);
        buildDataMap.buildMapFromAssets(twEngCountyMap,"countyEnglishMap");

        // retrieve the location array from main act
        Bundle bundle = this.getArguments();
        if(bundle!=null){
//            locationArray = bundle.getStringArrayList("locations");
            mLoc = bundle.getString("currentLocation");
            Timber.d("%s: onCreate %s", TAG, mLoc);
        }
        else {
            locationArray= null;
        }
        // default location
        if(mLoc ==null) mLoc ="宜蘭縣";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.scroll_view_base, container, false);
        ButterKnife.bind(this,rootView);

        //oom
//        SevenDaysPredictRecyclerView = rootView.findViewById(R.id.recyclerview);
        SevenDaysPredictRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx));
        recyclerViewAdapter = new RecyclerViewAdapter();
        SevenDaysPredictRecyclerView.setAdapter(recyclerViewAdapter);
        // scrollview
        scrollView = rootView.findViewById(R.id.scrollview);

        RetrofitQuery query = new RetrofitQuery(getActivity());
        Call<predictWeatherData> call2= query.GetWeatherData(mLoc);
        call2.enqueue(new Callback<predictWeatherData>(){
            @Override
            public void onResponse(@NonNull Call<predictWeatherData> call,@NonNull Response<predictWeatherData> response) {
                if(response.isSuccessful()){
                    predictWeatherData weatherStatus = response.body();
                    Map <String,String> weatherDataMap = CreateWeatherDataMap
                            .createWeatherData(weatherStatus);
                    ArrayList<ForecastData> oneWeekForecast= CreateWeatherDataMap.
                            createOneWeekWeatherData(weatherStatus);
                    weatherState.setImageResource(CheckWeatherStatus.checkBigIcon(weatherDataMap.get("Wx")));
                    weatherDetailState .setImageResource(CheckWeatherStatus.checkBigIcon(weatherDataMap.get("Wx")));
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
                    humidity.setText(RH + R.string.percent);
                    currentTmp.setText(temperature);
                    wx.setText(weatherDataMap.get("Wx"));
                    popVal.setText(weatherDataMap.get("PoP12h")+ R.string.percent);
                    uviTv.setText(weatherDataMap.get("UVI"));
                    windInfo.setText(windData);
                    Timber.d("retrofit query sucessess");

                    // access default location, it requires to be changed in following editions
                    String dLoc = (String) MapData.getMap("defaultLocMap").get(mLoc);
                    defaultLoc.setText(dLoc);
                    // one week predict data
                    recyclerViewAdapter = new RecyclerViewAdapter(oneWeekForecast);
                    recyclerViewAdapter.notifyDataSetChanged();
                    SevenDaysPredictRecyclerView.setAdapter(recyclerViewAdapter);

                    // The constants during fragments' lifecycle can be kept eleswhere.
                    //Try not to call getResouces (Required lots of mem)
//                    int defaultResId= ResourceOptimizer.getResId("sky.jpg",R.drawable.class);
//                    int resId = ResourceOptimizer.getResId(imgResrouceName,R.drawable.class);

                    HashMap<String, String> imgURLMap = new HashMap<>();
                    BuildDataMap buildDataMap = new BuildDataMap(mCtx);
                    buildDataMap.buildMapFromAssets(imgURLMap,"imgURL");

                    String imgPath = imgURLMap.get(mLoc);
                    if(mCtx!= null && isAdded()&& imgPath!=null){
// original working code
//                        drawable = getResources().getDrawable(resId);
//                        scrollView.setBackground(drawable);
                    Glide.with(mCtx)
                            .asBitmap()
//                            .load("https://i.imgur.com/L1KBeEv.jpg")
                            .load(imgPath)
                            .into(new CustomTarget<Bitmap>(480,800) {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    Drawable drawable = new BitmapDrawable(mCtx.getResources(), resource);
                                    scrollView.setBackground(drawable);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                }
                            });
                    }
                    rootView.findViewById(R.id.weather_layout).setVisibility(View.VISIBLE);
                    rootView.findViewById(R.id.not_found_dinasour_layout).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<predictWeatherData> call, Throwable t) {
                if(t instanceof IOException){   // connection error---> hide all the weather display
                    Timber.d("Retrofit: IOException %s", TAG);
                    Toast.makeText(mCtx, "Network unavailable", Toast.LENGTH_SHORT)
                            .show();
                }
                Log.d("main", "query error, start debugging");
                Timber.d("%s Retrofit: query failed: parsing error", TAG);

                rootView.findViewById(R.id.weather_layout).setVisibility(View.GONE);
                rootView.findViewById(R.id.not_found_dinasour_layout).setVisibility(View.VISIBLE);
            }
        });

//        ImageView gifWindTurbine = rootView.findViewById(R.id.wind_turbine);
        DrawableImageViewTarget imageViewTarget =
                new DrawableImageViewTarget(gifWindTurbine);

        Glide
        .with(this) // replace with 'this' if it's in activity
        .load(R.drawable.wind_turbine)  // load the gif file
//        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(imageViewTarget);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, mLoc + " onDestroy, setBackground(null)");
        scrollView.setBackground(null);

//        Drawable drawable = scrollView.getBackground();
//        if(drawable == null)Log.d("onDestory", "drawable is null");
////        if (drawable instanceof BitmapDrawable) {
//
//
//            if(drawable!=null){
////            if (bitmap!=null && !bitmap.isRecycled()) {
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                bitmap.recycle();
//                bitmap=null;
//            }
////        }
//        drawable=null;
//        scrollView=null;
    }

// MPAdnroid
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

//        public int[] x = new int[]{1, 2, 3};
//        public int[] y = new int[]{10, xxo, 300};
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
//                Log.d("VALUE", String.valueOf(value));Des
//                return xLabels.get((int)value);
//            }
//        });
//        rainfallChart.invalidate();
//    }//onCreate method
}


