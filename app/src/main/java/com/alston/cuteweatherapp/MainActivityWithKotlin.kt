package com.alston.cuteweatherapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.support.v4.app.Fragment
/*
 * For horizontal paging.
 * */
import android.support.v4.view.ViewPager
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.alston.cuteweatherapp.R
import com.alston.cuteweatherapp.utils.BuildDataMap
import com.alston.cuteweatherapp.utils.MapData

import java.util.ArrayList
import java.util.HashMap


class MainActivityWithKotlin : AppCompatActivity() {

    /**
     * The pager widget, which handles animation and allows swiping
     * horizontally to access previous and next.
     */
    internal lateinit var mPager: ViewPager
    /**
     * The pager adapter, which provides the pages to the view pager widget
     */
    private var pagerAdapter: ScreenSlidePagerAdapter? = null
    private val TAG = "main"
    private val fragmentArrayList = ArrayList<Fragment>()
    private val locations = ArrayList<String>()
    private val duplicatedPos: Int? = null
    private var adapter: ArrayAdapter<String>? = null
    private var newLocation: String? = null
    private val addedLocations = ArrayList<String>()
    private val maxFrag = 7
    private var mCtx: Context? = null
    internal var twCounties = arrayOf("基隆市", "臺北市", "新北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市", "苗栗縣", "臺中市", "彰化縣", "雲林縣", "南投縣", "嘉義縣", "嘉義市", "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣", "連江縣")

    private var locationTitle: TextView? = null // ?: secure access operation

    override fun onCreate(savedInstanceState: Bundle?) : Unit{
        Log.d(TAG, "onCreate")
        mCtx = baseContext
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // init ResourceOptimizer for later use in fragments

        // create maps
        CreateLocationCountyMap()

        val locations_lv = findViewById<ListView>(R.id.locations_lv)
        val searchLocation = findViewById<EditText>(R.id.search_location)
        val editTextCancel = ContextCompat.getDrawable(this, R.drawable.cancel)
        editTextCancel!!.setBounds(0, 0,
                editTextCancel.intrinsicWidth,
                editTextCancel.intrinsicHeight)
        searchLocation.setCompoundDrawables(null, null, editTextCancel, null)


        adapter = ArrayAdapter(this, R.layout.list_item_layout, twCounties)
        locations_lv.adapter = adapter

        //action bar
        val imageButton = findViewById<ImageButton>(R.id.select_location)
        imageButton.setOnClickListener {
            Log.d(TAG, "to select location")
            findViewById<View>(R.id.viewpager).visibility = View.GONE
            findViewById<View>(R.id.search_view_layout).visibility = View.VISIBLE
            findViewById<View>(R.id.action_bar).visibility = View.GONE
        }


        bindSearchBarEvent(searchLocation, locations_lv)
        //        duplicatedPos = -1; //initial state: no duplicated county.

        mPager = findViewById(R.id.viewpager)
        locationTitle = findViewById(R.id.location_title)
        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, baseContext)
        mPager.adapter = pagerAdapter
        mPager.offscreenPageLimit = 1
        newLocation = "宜蘭縣"
        creatNewFrag(newLocation)
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                updateTitleLocation(position, locationTitle!!)
            }
        })


        // add event to remove the current frag.
        val removeCurrentLoc = findViewById<ImageButton>(R.id.remove_location)
        removeCurrentLoc.setOnClickListener(
                View.OnClickListener {
                    if (pagerAdapter!!.count == 1) {
                        Toast.makeText(mCtx, "至少要有一個地點", Toast.LENGTH_SHORT).show()
                        return@OnClickListener
                    }

                    val position = mPager.currentItem
                    val afterFragExist = pagerAdapter!!.getItem(position + 1)
                    if (afterFragExist != null) {
                        locationTitle!!.text = pagerAdapter!!.getLocationName(position + 1)
                    } else {
                        locationTitle!!.text = pagerAdapter!!.getLocationName(position - 1)
                    }
                    pagerAdapter!!.removeFrag(position)
                }
        )
    }//onCreate

    private fun creatNewFrag(newLocation: String?) {

        if (newLocation == null) return  //The user didn't select any location.
        val duplicated = pagerAdapter!!.checkLocAlreadyExist(newLocation)
        if (!duplicated) { // null when user doesn't select any location and just return
            if (pagerAdapter!!.count > maxFrag) {
                Toast.makeText(baseContext, "區域已經最多了,請移除舊的再新增", Toast.LENGTH_LONG)
                        .show()
                return
            }
            val frag = NewLocationFrag()
            frag.setmLocTitle(locationTitle)
            val fragBundle = Bundle()
            //        fragBundle.putStringArrayList("locations", locations);
            fragBundle.putString("currentLocation", newLocation)
            frag.arguments = fragBundle
            (pagerAdapter as ScreenSlidePagerAdapter).addFrag(frag, newLocation)
            addedLocations.add(newLocation)
            mPager.currentItem = pagerAdapter!!.count - 1
            // Assume
            val location_title = findViewById<TextView>(R.id.location_title)
            location_title.text = newLocation
        } else {
            mPager.currentItem = pagerAdapter!!.getFragPosition(newLocation)!!
        }
    }

    private fun updateTitleLocation(position: Int, locationTitle: TextView) {
        Log.d("updateTitleLocation", position.toString())
        val curLoc = pagerAdapter!!.mLocations[position]
        Log.d("updateTitleLocation", "current loc is $curLoc")
        locationTitle.text = ""
        locationTitle.text = curLoc
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun bindSearchBarEvent(searchLocation: EditText, lv: ListView) {
        searchLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                adapter!!.filter.filter(charSequence)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        searchLocation.setOnTouchListener(View.OnTouchListener { v, event ->
            v.performClick()
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= searchLocation.width - searchLocation.compoundPaddingRight) {
                    searchLocation.setText("")
                    findViewById<View>(R.id.viewpager).visibility = View.VISIBLE
                    findViewById<View>(R.id.search_view_layout).visibility = View.GONE
                    findViewById<View>(R.id.action_bar).visibility = View.VISIBLE
                    return@OnTouchListener true
                }
            }
            false
        })


        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.d("onItemClick", "onItemClick")
            val selectedLoc = parent.getItemAtPosition(position).toString()
            creatNewFrag(selectedLoc)
            findViewById<View>(R.id.viewpager).visibility = View.VISIBLE
            findViewById<View>(R.id.search_view_layout).visibility = View.GONE
            findViewById<View>(R.id.action_bar).visibility = View.VISIBLE
        }
    }

    //    private void createMaps(){
    //        BuildDataMap buildDataMap = new BuildDataMap(mCtx);
    //        buildDataMap.buildMapFromAssets(weatherIconMap,"iconMap");
    //        if (weatherIconMap!=null) {
    //            WeatherIconMap wMap = new WeatherIconMap(weatherIconMap);
    //        }
    //        else{
    //            Log.e(TAG, "weatherIconMap is null");
    //        }
    //    }

    private fun CreateLocationCountyMap() {
        val buildDataMap = BuildDataMap(mCtx)
        buildDataMap.buildMapFromAssets(defaultLocMap, "defaultLocMap")
        MapData.setMap("defaultLocMap", defaultLocMap)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "activity destoryed")
    }

    companion object {
        //    public static HashMap<String,String> weatherIconMap = new HashMap<>();
        var defaultLocMap = HashMap<String, String>()
    }
    // Called before onStop()

}