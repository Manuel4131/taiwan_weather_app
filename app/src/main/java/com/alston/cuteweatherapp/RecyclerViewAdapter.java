package com.alston.cuteweatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alston.cuteweatherapp.R;

//import ViewHolder;  // Viewholder can be definred in another class!

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<ForecastData> weatherPredicts = new ArrayList<ForecastData>();


    public RecyclerViewAdapter(){

    }

    public RecyclerViewAdapter(ArrayList<ForecastData> predicts){
        weatherPredicts = predicts;
    }

    public void updateItem(ForecastData newPredict){
        weatherPredicts.add(newPredict);
        this.notifyItemChanged(weatherPredicts.size()-1);
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.week_forecasting,viewGroup,false);
        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder binds the view holders to their data.
     * https://developer.android.com/guide/topics/ui/layout/recyclerview
     * @param viewHolder
     * @param pos
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int pos) {
        Log.d("onBindViewHolder", "BVH called");
        ForecastData predict = weatherPredicts.get(pos);
        viewHolder.predict_day.setText(predict.mWeekDay);
        viewHolder.predict_day_ws.setImageResource(predict.mWeatherStatus);
        viewHolder.predict_day_highest_tmp.setText(predict.mHighest_tmp);
        viewHolder.predict_day_lowest_tmp.setText(predict.mLowest_tmp);
    }

    @Override
    public int getItemCount() {
        return weatherPredicts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView predict_day;
        public ImageView predict_day_ws;
        public TextView predict_day_highest_tmp;
        public TextView predict_day_lowest_tmp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            predict_day=itemView.findViewById(R.id.predict_day);
            predict_day_ws= (ImageView) itemView.findViewById(R.id.predict_day_ws);
            predict_day_highest_tmp=itemView.findViewById(R.id.predict_day_highest_tmp);
            predict_day_lowest_tmp=itemView.findViewById(R.id.predict_day_lowest_tmp);
        }
    }
}
