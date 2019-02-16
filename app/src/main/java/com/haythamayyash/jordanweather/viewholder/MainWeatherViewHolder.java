package com.haythamayyash.jordanweather.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haythamayyash.jordanweather.R;

public class MainWeatherViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewTemperature;
    public TextView textViewDescription;
    public TextView textViewCityName;
    public ImageView imageViewWeather;
    public ProgressBar progressBar;
    public CardView cardView;


    public MainWeatherViewHolder(View itemView) {
        super(itemView);
        textViewTemperature = itemView.findViewById(R.id.textView_temperature);
        textViewDescription = itemView.findViewById(R.id.textView_weatherDescription);
        textViewCityName = itemView.findViewById(R.id.textView_cityName);
        imageViewWeather = itemView.findViewById(R.id.imageView_weather);
        progressBar = itemView.findViewById(R.id.progressBar_rowItem);
        cardView = itemView.findViewById(R.id.cardView_mainWeatherRowItem);
    }
}
