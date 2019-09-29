package com.alston.cuteweatherapp.utils;

import com.alston.cuteweatherapp.R;

import java.util.ArrayList;

public class ParseWxStatus {
    public static ArrayList<String> mWxStatusArr;
    // looks like ["多雲","陰雨","晴朗"]
    private static ArrayList<Boolean> wxStatus = new ArrayList<>();
    private static String[] wxState = new String[]{"晴","雲","雨"};
    private static ArrayList<Integer> weatherIconsId = new ArrayList<>();

    public ParseWxStatus(){}

    public static ArrayList<Integer> parse(ArrayList<String> wxStatusArr) {
        mWxStatusArr = wxStatusArr;

        for(String wStatus:mWxStatusArr){
            StringBuilder wxStr=new StringBuilder();
            for (String s : wxState) {
                if (wStatus.contains(s)) {
                    wxStr.append("1");
                } else {
                    wxStr.append("0");
                }
            }// result looks like oox,xoo,xxx
            // Map oox to to get res id.
            int resId= ResourceOptimizer.
                    getResId(wxStr.toString(), R.drawable.class);
            weatherIconsId.add(resId);
        }
        // Change to ArrayList<String>!!!
        return weatherIconsId;
    }
}
