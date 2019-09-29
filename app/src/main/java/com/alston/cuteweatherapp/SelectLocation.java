package com.alston.cuteweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.alston.cuteweatherapp.R;

import java.util.ArrayList;

public class SelectLocation extends AppCompatActivity {
    private ArrayAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<String> locArrayList;
        locArrayList = getIntent().getStringArrayListExtra("locations");
        assert (locArrayList==null);
        String[] locations = new String[]{
                "基隆市", "臺北市", "新北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市",
                "苗栗縣", "臺中市", "彰化縣", "雲林縣", "南投縣", "嘉義縣", "嘉義市",
                "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣",
                "連江縣"
        };
        setContentView(R.layout.search_view_layout);
        ListView locations_lv = findViewById(R.id.locations_lv);
        EditText searchLocation = findViewById(R.id.search_location);

        adapter = new ArrayAdapter(this, R.layout.list_item_layout, locations);
        locations_lv.setAdapter(adapter);

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

        locations_lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick", "onItemClick");

                Intent intent = new Intent(SelectLocation.this, MainActivity.class);
                String location = parent.getItemAtPosition(position).toString();
                locArrayList.add(location);
                intent.putStringArrayListExtra("locations", locArrayList);
                startActivity(intent);
                finish();
            }
        });
    }

}

