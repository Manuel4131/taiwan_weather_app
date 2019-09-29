package com.alston.cuteweatherapp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapData {

    private static HashMap<String,HashMap> applicationMap=new HashMap<>();

    public static HashMap getMap(String mapName){
        HashMap map = applicationMap.get(mapName);
        if(map !=null){
            return map;
        }
        return null;
    }

    public static void setMap(String key, HashMap map){
        applicationMap.put(key, map);
    }
}
