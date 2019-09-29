package com.alston.cuteweatherapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.nfc.Tag;
import android.util.Log;

import com.alston.cuteweatherapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class accessProperty {
    private final static String TAG = "accessProperty";

    public static String get(Context ctx, String prop){
        Resources resources= ctx.getResources();
        try{
            InputStream rawResources = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResources);
            return properties.getProperty(prop);
        }
        catch (Resources.NotFoundException e){
            Log.e(TAG, "Unable to find configure file" + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,"Failed to open the config file" + e.getMessage());
        }
        return null;
    }
}
