package com.alston.cuteweatherapp.utils;

import android.support.annotation.NonNull;
import android.widget.TextView;

public class CheckNull {
    public static String checkNull(String value){
        if(value == null)
            return "";
        else return value;
    }
}
