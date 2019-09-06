package com.test.weatherapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit.Builder retrofitInstance = null;
    private static String baseUrl = "https://opendata.cwb.gov.tw/";
    //F-D0047-005 is for 桃園, which is currently for testing.

    private RetrofitClientInstance(){}

    //builder dp
    // ref: https://stackoverflow.com/questions/222214/managing-constructors-with-many-parameters-in-java/222295#222295
    public static Retrofit.Builder ChangeBaseUrl(String mBaseUrl){
        baseUrl = mBaseUrl;
        return retrofitInstance;
    }

    public static Retrofit.Builder getRetrofitInstance() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(loggingInterceptor);

        if(retrofitInstance==null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofitInstance.client(okHttpClientBuilder.build());
        }
        return retrofitInstance;
    }
}
