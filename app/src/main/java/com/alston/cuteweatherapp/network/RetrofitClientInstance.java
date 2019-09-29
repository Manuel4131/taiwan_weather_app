package com.alston.cuteweatherapp.network;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.alston.cuteweatherapp.config.Constant;
import com.alston.cuteweatherapp.utils.App;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient
                .Builder().connectTimeout(Constant.connectionTimeout, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(loggingInterceptor);
        okHttpClientBuilder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(@NonNull  Chain chain) throws IOException {
                Request request = chain.request();

                // try the request
                Response response = chain.proceed(request);

                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 3) {
                    response.close();// close the response since previous one is still alive.
                    Timber.d("retry times %s", tryCount);
                    tryCount++;

                    // retry the request
                    response = chain.proceed(request);
                }

                // otherwise just pass the original response on
                return response;
            }
        });


        if(retrofitInstance==null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofitInstance.client(okHttpClientBuilder.build());
        }
        return retrofitInstance;
    }
}
