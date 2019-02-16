package com.haythamayyash.jordanweather.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haythamayyash.jordanweather.R;

public class DetailsWeatherViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTime;
    public TextView textViewTemparature;
    public ImageView imageViewWeather;

    public DetailsWeatherViewHolder(View itemView) {
        super(itemView);
        textViewTime = itemView.findViewById(R.id.textView_time);
        textViewTemparature = itemView.findViewById(R.id.textView_temp);
        imageViewWeather = itemView.findViewById(R.id.imageView_weather);
    }
}
