package com.alston.cuteweatherapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import timber.log.Timber
import java.util.*

class ScreenSlidePagerAdapter(fm: FragmentManager, behavior: Int, private val mCtx: Context) : FragmentStatePagerAdapter(fm!!, behavior) {
    private val locationFrag:ArrayList<Fragment> = ArrayList<Fragment>()
    var mLocations = ArrayList<String>()
    private val TAG:String = "ScreenSlidePagerAdapter"
    private val fragmentManager: FragmentManager = fm
    private var mAge:Int = 0

    protected fun checkLocAlreadyExist(newLocation:String):Boolean{
        return mLocations.contains(newLocation)
    }

    override public fun getItem(position: Int): Fragment {
        Log.d(TAG, "Pos$position")

        if (position >= 0 && position < locationFrag.size){
            return locationFrag.get(position)
        }else{
            throw Exception("Out of the index")
        }
    }

    public fun getFragPosition(locName:String):Int{
        val index:Int = mLocations.indexOf(locName)
        return if(index==-1) PagerAdapter.POSITION_NONE else index
    }

    public fun addFrag(frag:Fragment, newLocation: String):Int{

        if(checkLocAlreadyExist(newLocation))
            return -1;
        locationFrag.add(frag)
        mLocations.add(newLocation)
        notifyDataSetChanged()
        Log.d(TAG, "A new fragment is created");
        Log.d(TAG, "fragment size %d" + locationFrag.size);
        return 0
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE;
    }

    public fun removeFrag(pos:Int):Boolean{
        if (getCount()==1) {
            Toast.makeText(mCtx, "至少要有一個地點", Toast.LENGTH_LONG).show();
            return false;
        }
        locationFrag.removeAt(pos)
        Log.d(TAG, mLocations[pos] + " to be removed from mLoc")
        mLocations.removeAt(pos);
        notifyDataSetChanged()
        return true
    }

    override public fun getCount():Int{
        return locationFrag.size;
    }

    public fun getLocationName(pos:Int):String {
        return mLocations.get(pos)
    }
}//class
