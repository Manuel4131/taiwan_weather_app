package com.test.weatherapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectLocationActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] locations = new String[]{
                "基隆市","臺北市","新北市","宜蘭縣","桃園市", "新竹縣","新竹市",
                "苗栗縣", "臺中市","彰化縣", "雲林縣","南投縣","嘉義縣", "嘉義市",
                "臺南市","高雄市","屏東縣","臺東縣","花蓮縣","澎湖縣","金門縣",
                "連江縣",
        };

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item_layout, locations);
        setListAdapter(adapter);
    }






    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {
        Intent intent = new Intent(this, MainActivity.class);
        String location = getListView().getItemAtPosition(position).toString();
        intent.putExtra("location", location);
        startActivity(intent);
    }
}
