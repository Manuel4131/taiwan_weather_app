package com.test.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectLocationFrag extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] locations = new String[]{
                "宜蘭縣","基隆市","嘉義市","台北市","嘉義縣","新北市","台南市","桃園縣","高雄市","新竹市",
                "屏東縣","新竹縣",
                "台東縣","苗栗縣","花蓮縣","台中市","彰化縣","澎湖縣","南投縣","金門縣","雲林縣","連江縣"
        };

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item_layout, locations);
        setListAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.location_lv, container, false);
    }


    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {
        Intent intent = new Intent(getActivity(),MainActivity.class);
        String location = getListView().getItemAtPosition(position).toString();
        intent.putExtra("location", location);
        startActivity(intent);
    }
}
