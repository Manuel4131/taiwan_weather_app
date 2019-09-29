package com.alston.cuteweatherapp.utils;

import android.app.Application;
import android.content.Context;

import com.alston.cuteweatherapp.BuildConfig;

import timber.log.Timber;

public class App extends Application {

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        // Plant timber tree


    }
}