package com.test.weatherapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
    public ArrayList<Fragment> locationFrag =new ArrayList<>();

    private final String TAG ="ScreenSlidePagerAdapter";
    private Context mCtx;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Actually it created new fragment rather than just get an itme.
     */
    @Override
    public Fragment getItem(int position) {
//        Bundle bundle = new Bundle();
//        bundle.putString("location", locations.get(position));
//        weatherFrag.setArguments(bundle);
//        NewLocationFrag frg = new NewLocationFrag();
//        if (weatherFrag.isAdded()) {
//            return weatherFrag;
//        }
//        return null;
        Log.d(TAG, "Pos" + String.valueOf(position));
        if (position >= 0 && position < locationFrag.size()) {
            return locationFrag.get(position);
        } else {
            Log.e(TAG, "item not exist");
            return null;
        }
    }

    public Integer getFragPosition(Fragment frag){
        int index  = locationFrag.indexOf(frag);
        if(index == -1){
            return POSITION_NONE;
        }
        else
            return index;
    }

    public Integer addFrag(Fragment frag) {
//         when the city is already in the list, change view to the city frg
//         otherwise, create a new fragment
        if (locationFrag.contains(frag))// if not working, try string equals isntead
            return getFragPosition(frag);   //ViewPager will setCurrentItem to the exist frag
        else{
            locationFrag.add(frag);
            notifyDataSetChanged();
            return -1;
        }
    }

//    public void removeFrag(Fragment fragment, int position) {
//    //wait until other functions work.
//    }

    @Override
    public int getCount(){
        return locationFrag.size();
    }
}//class
