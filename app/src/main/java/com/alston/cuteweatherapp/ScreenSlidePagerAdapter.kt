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

class ScreenSlidePagerAdapter(fm: FragmentManager?, behavior: Int, private val mCtx: Context) : FragmentStatePagerAdapter(fm!!, behavior) {
    val locationFrag = ArrayList<Fragment>()
    var mLocations = ArrayList<String>()
    private val TAG = "ScreenSlidePagerAdapter"
    private val fragmentManager: FragmentManager? = null
    fun checkLocAlreadyExist(newLocation: String): Boolean {
        // remember to store another ArryaList recording the location name
        //only for easier comparison

        if (mLocations.contains(newLocation)) {
            Log.d(TAG, "location already exsist")
            return true
        }
        return false
    }

    /**
     * Actually it created new fragment rather than just get an itme.
     */
    override fun getItem(position: Int): Fragment {
        Log.d(TAG, "Pos$position")
        return if (position >= 0 && position < locationFrag.size) {
            locationFrag[position]
        } else {
            Timber.d("%d item not exist", position)
            null
        }
    }

    fun getLocationName(pos: Int): String {
        return mLocations[pos]
    }

    fun getFragPosition(locName: String): Int {
        val index = mLocations.indexOf(locName)
        return if (index == -1) {
            PagerAdapter.POSITION_NONE
        } else index
    }

    fun addFrag(frag: Fragment, newLocation: String): Int {
        if (checkLocAlreadyExist(newLocation)) return -1
        locationFrag.add(frag)
        Timber.d("fragment size %d", locationFrag.size)
        mLocations.add(newLocation)
        notifyDataSetChanged()
        Log.d(TAG, "A new fragment is created")
        return -1
    }

    fun removeFrag(pos: Int): Boolean {
        if (count == 1) {
            Toast.makeText(mCtx, "至少要有一個地點", Toast.LENGTH_LONG).show()
            return false
        }
        locationFrag.removeAt(pos)   // caution: Integer is not the correct type.But the warning

        // msg won't be given. Nothing happens.


        Log.d(TAG, mLocations[pos] + " to be removed from mLoc")
        mLocations.removeAt(pos)
        Log.d(TAG, "left")
        for (s in mLocations) {
            Log.d(TAG, s)
        }
        notifyDataSetChanged()
        return true
    }

    override fun getItemPosition(`object`: Any): Int {
//        if (index == -1)

        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return locationFrag.size
    }

}//class
