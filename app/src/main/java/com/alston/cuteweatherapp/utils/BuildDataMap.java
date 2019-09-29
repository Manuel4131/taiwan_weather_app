package com.alston.cuteweatherapp.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BuildDataMap {
    private final String TAG = "BuildDataMap";
    private Context mCtx;

    public BuildDataMap(Context ctx){
        mCtx= ctx;
    }

    public void buildMapFromAssets(Map map, String fileName){

        try {
            InputStream inputStream=mCtx.getAssets().open(fileName);//dataidmap
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String str;
            while((str = bufferedReader.readLine())!=null){
                String [] pair = str.split(",");
                map.put(pair[0],pair[1]);
            }
        }catch (IOException e){
            Log.e(TAG, "dataidmap file not exist or path incorrect");
        }
    }//


}
