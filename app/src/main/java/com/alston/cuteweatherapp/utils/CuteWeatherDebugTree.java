package com.alston.cuteweatherapp.utils;

import timber.log.Timber;

public class CuteWeatherDebugTree extends Timber.DebugTree {
    @Override
    protected String createStackElementTag(StackTraceElement element) {
        return String.format("[C:%s] [M:%s]  [L:%s] ",
                super.createStackElementTag(element),
                element.getMethodName(),
                element.getLineNumber()
                );
    }
}