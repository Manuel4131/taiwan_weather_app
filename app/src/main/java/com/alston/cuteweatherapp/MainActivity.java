package com.alston.cuteweatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.v4.app.Fragment;
/*
 * For horizontal paging.
 * */
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alston.cuteweatherapp.R;
import com.alston.cuteweatherapp.utils.BuildDataMap;
import com.alston.cuteweatherapp.utils.MapData;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    /**
     * The pager widget, which handles animation and allows swiping
     * horizontally to access previous and next.
     */
    ViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget
     */
    private ScreenSlidePagerAdapter pagerAdapter;
    private final String TAG = "main";
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private Integer duplicatedPos;
    private ArrayAdapter<String> adapter;
    private String newLocation;
    private ArrayList<String> addedLocations = new ArrayList<>();
    private final Integer maxFrag = 7;
    private Context mCtx;
    String[] twCounties = new String[]{
            "基隆市", "臺北市", "新北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市",
            "苗栗縣", "臺中市", "彰化縣", "雲林縣", "南投縣", "嘉義縣", "嘉義市",
            "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣",
            "連江縣"
    };
//    public static HashMap<String,String> weatherIconMap = new HashMap<>();
    public static HashMap<String,String> defaultLocMap = new HashMap<>();

    private TextView locationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        mCtx = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // init ResourceOptimizer for later use in fragments

        // create maps
        CreateLocationCountyMap();

        ListView locations_lv = findViewById(R.id.locations_lv);
        EditText searchLocation = findViewById(R.id.search_location);
        Drawable editTextCancel = ContextCompat.getDrawable(this,R.drawable.cancel);
        editTextCancel.setBounds(0,0,
                                editTextCancel.getIntrinsicWidth(),
                                editTextCancel.getIntrinsicHeight());
        searchLocation.setCompoundDrawables(null, null, editTextCancel, null);


        adapter = new ArrayAdapter<String>(this, R.layout.list_item_layout, twCounties);
        locations_lv.setAdapter(adapter);

        //action bar
        ImageButton imageButton = findViewById(R.id.select_location);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "to select location");
                findViewById(R.id.viewpager).setVisibility(View.GONE);
                findViewById(R.id.search_view_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.action_bar).setVisibility(View.GONE);
            }
        });


        bindSearchBarEvent(searchLocation,locations_lv);
//        duplicatedPos = -1; //initial state: no duplicated county.

        mPager = findViewById(R.id.viewpager);
        locationTitle = findViewById(R.id.location_title);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),getBaseContext());
        mPager.setAdapter(pagerAdapter);
        mPager.setOffscreenPageLimit(1);
        newLocation ="宜蘭縣";
        creatNewFrag(newLocation);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }
            public void onPageSelected(int position) {
                updateTitleLocation(position, locationTitle);
            }
        });


        // add event to remove the current frag.
        ImageButton removeCurrentLoc = findViewById(R.id.remove_location);
        removeCurrentLoc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pagerAdapter.getCount()==1){
                            Toast.makeText(mCtx, "至少要有一個地點", Toast.LENGTH_SHORT).show();
                            return ;
                        }

                        int position = mPager.getCurrentItem();
                        Fragment afterFragExist = pagerAdapter.getItem(position+1);
                        if(afterFragExist !=null){
                            locationTitle.setText(pagerAdapter.getLocationName(position+1));
                        }
                        else{
                            locationTitle.setText(pagerAdapter.getLocationName(position-1));
                        }
                        pagerAdapter.removeFrag(position);
                    }
                }
        );
    }//onCreate

    private void creatNewFrag(String newLocation){

        if (newLocation==null ) return; //The user didn't select any location.
        Boolean duplicated = pagerAdapter.checkLocAlreadyExist(newLocation);
        if (!duplicated) { // null when user doesn't select any location and just return
             if(pagerAdapter.getCount()>maxFrag){
                 Toast.makeText(getBaseContext(),"區域已經最多了,請移除舊的再新增",Toast.LENGTH_LONG)
                                    .show();
                 return;
             }
            Fragment frag = new NewLocationFrag();
             ((NewLocationFrag) frag).setmLocTitle(locationTitle);
            Bundle fragBundle = new Bundle();
//        fragBundle.putStringArrayList("locations", locations);
            fragBundle.putString("currentLocation", newLocation);
            frag.setArguments(fragBundle);
            ((ScreenSlidePagerAdapter) pagerAdapter).addFrag(frag, newLocation);
            addedLocations.add(newLocation);
            mPager.setCurrentItem(pagerAdapter.getCount() -1);
            // Assume
            TextView location_title = findViewById(R.id.location_title);
            location_title.setText(newLocation);
        }
        else{
            mPager.setCurrentItem(pagerAdapter.getFragPosition(newLocation));
        }
    }

    private void updateTitleLocation(int position, final TextView locationTitle){
        Log.d("updateTitleLocation", String.valueOf(position));
        String curLoc = pagerAdapter.mLocations.get(position);
        Log.d("updateTitleLocation", "current loc is "+ curLoc );
        locationTitle.setText("");
        locationTitle.setText(curLoc);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void bindSearchBarEvent(final EditText searchLocation, final ListView lv){
        searchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchLocation.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.performClick();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=(searchLocation.getWidth()-
                        searchLocation.getCompoundPaddingRight())){
                        searchLocation.setText("");
                        findViewById(R.id.viewpager).setVisibility(View.VISIBLE);
                        findViewById(R.id.search_view_layout).setVisibility(View.GONE);
                        findViewById(R.id.action_bar).setVisibility(View.VISIBLE);
                        return true;
                    }
                }
                return false;
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", "onItemClick");
                String selectedLoc = parent.getItemAtPosition(position).toString();
                creatNewFrag(selectedLoc);
                findViewById(R.id.viewpager).setVisibility(View.VISIBLE);
                findViewById(R.id.search_view_layout).setVisibility(View.GONE);
                findViewById(R.id.action_bar).setVisibility(View.VISIBLE);
            }
        });
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

    private void CreateLocationCountyMap(){
        BuildDataMap buildDataMap = new BuildDataMap(mCtx);
        buildDataMap.buildMapFromAssets(defaultLocMap,"defaultLocMap");
        MapData.setMap("defaultLocMap", defaultLocMap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "activity destoryed");
    }
    // Called before onStop()

}