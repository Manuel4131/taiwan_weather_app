package com.alston.cuteweatherapp;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import timber.log.Timber;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


    private ArrayList<Fragment> locationFrag =new ArrayList<>();
    public ArrayList<String> mLocations = new ArrayList<>();

    private final String TAG ="ScreenSlidePagerAdapter";
    private Context mCtx;
    private FragmentManager fragmentManager;

    public ScreenSlidePagerAdapter(FragmentManager fm,int behavior, Context ctx) {
        super(fm,behavior);
        mCtx = ctx;
    }

    protected Boolean checkLocAlreadyExist(String newLocation){
        // remember to store another ArryaList recording the location name
        //only for easier comparison
        if (mLocations.contains(newLocation)) {
            Log.d(TAG, "location already exsist");
            return true;
        }
        return false;
    }
    public ArrayList<Fragment> getLocationFrag() {
        return locationFrag;
    }


    /**
     * Actually it created new fragment rather than just get an itme.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "Pos" + String.valueOf(position));
        if (position >= 0 && position < locationFrag.size()) {
            return locationFrag.get(position);
        } else {
            Timber.d("%d item not exist", position);
            return null;
        }
    }

    public String getLocationName(int pos){
        return mLocations.get(pos);
    }

    public Integer getFragPosition(String locName){
        int index  = mLocations.indexOf(locName);
        if(index == -1){
            return POSITION_NONE;
        }
        else
            return index;
    }

    public Integer addFrag(Fragment frag,String newLocation) {

        if(checkLocAlreadyExist(newLocation))
            return -1;

        locationFrag.add(frag);
        Timber.d("fragment size %d", locationFrag.size());
        mLocations.add(newLocation);
        notifyDataSetChanged();
        Log.d(TAG, "A new fragment is created");
        return -1;
    }


    public boolean removeFrag(int pos){
        if (getCount()==1) {
            Toast.makeText(mCtx, "至少要有一個地點", Toast.LENGTH_LONG).show();
            return false;
        }
        locationFrag.remove(pos);   // caution: Integer is not the correct type.But the warning
                                    // msg won't be given. Nothing happens.
        Log.d(TAG, mLocations.get(pos)+" to be removed from mLoc");

        mLocations.remove(pos);
        Log.d(TAG, "left");
        for(String s: mLocations){
            Log.d(TAG, s);
        }

        notifyDataSetChanged();
        return true;
    }

    @Override public int getItemPosition (Object object) {
//        int index = locationFrag.indexOf (object);
//        if (index == -1)


            return POSITION_NONE;
//        else return index;
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        fragmentManager.beginTransaction().remove((Fragment) object).commitNowAllowingStateLoss();
//    }


    @Override
    public int getCount(){
        return locationFrag.size();
    }


}//class
