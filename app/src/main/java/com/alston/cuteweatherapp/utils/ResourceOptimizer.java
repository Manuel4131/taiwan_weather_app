package com.alston.cuteweatherapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

public class ResourceOptimizer {

// Caller:
// int defaultResId= ResourceOptimizer.getResId("sky.jpg",R.drawable.class);
    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
