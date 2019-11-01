package com.alston.cuteweatherapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


//public class SelectLocation_: AppCompatActivity(){
//    private var times :Int = 0;
//    public override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val locArrayList: ArrayList<String>?
//        locArrayList = intent.getStringArrayListExtra("locations")
//        requireNotNull(locArrayList)
//        val locations = arrayOf<String?>(
//                "基隆市", "臺北市", "新北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市",
//                "苗栗縣", "臺中市", "彰化縣", "雲林縣", "南投縣", "嘉義縣", "嘉義市",
//                "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣",
//                "連江縣"
//        )
//        setContentView(R.layout.search_view_layout);
//
//
//    }
//
//
//}

class SelectLocation : AppCompatActivity() {
    private var adapter: ArrayAdapter<Any?>? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locArrayList: ArrayList<String>?
        locArrayList = intent.getStringArrayListExtra("locations")
        assert(locArrayList == null)
        val locations = arrayOf<String?>(
                "基隆市", "臺北市", "新北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市",
                "苗栗縣", "臺中市", "彰化縣", "雲林縣", "南投縣", "嘉義縣", "嘉義市",
                "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣",
                "連江縣"
        )
        setContentView(R.layout.search_view_layout)
        Log.d("SelectLocation", "hi, kotlin ")
        val locations_lv: ListView = findViewById(R.id.locations_lv)
        val searchLocation: EditText = findViewById(R.id.search_location)
        adapter = ArrayAdapter<Any?>(this, R.layout.list_item_layout, locations)
        locations_lv.adapter = adapter
        searchLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                adapter!!.getFilter().filter(charSequence)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        locations_lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.d("onItemClick", "kotlin kotlin kotlin")
            val intent = Intent(this@SelectLocation, MainActivity::class.java)
            val location = parent.getItemAtPosition(position).toString()
            locArrayList.add(location)
            intent.putStringArrayListExtra("locations", locArrayList)
            startActivity(intent)
            finish()
        }
    }
}