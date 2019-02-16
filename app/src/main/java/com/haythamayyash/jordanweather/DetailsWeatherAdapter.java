package com.haythamayyash.jordanweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haythamayyash.jordanweather.common.Constants;
import com.haythamayyash.jordanweather.model.FullWeatherDetails;
import com.haythamayyash.jordanweather.model.Weather;
import com.haythamayyash.jordanweather.viewholder.DetailsWeatherViewHolder;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import io.realm.RealmList;

public class DetailsWeatherAdapter extends RecyclerView.Adapter<DetailsWeatherViewHolder> {
    private Context context;
    private RealmList<FullWeatherDetails> weatherList;

    public DetailsWeatherAdapter(Context context, RealmList weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }


    @Override
    public DetailsWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.details_weather_row_item, parent, false);
        return new DetailsWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsWeatherViewHolder holder, int position) {
        holder.textViewTemparature.setText(String.format(Locale.getDefault(), context.getString(R.string.temperatureForamt)
                , Objects.requireNonNull(weatherList.get(position)).getMain().getTemp() - 273.15));

        Weather weather = Objects.requireNonNull(weatherList.get(0)).getWeather().get(0);

        if (weather != null) {
            Picasso.get().load(Constants.BASE_IMAGE_URL + weather.getIcon() + ".png")
                    .placeholder(R.drawable.image_place_holder).into(holder.imageViewWeather);
        }
        DateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.dateForamt1), Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(Objects.requireNonNull(weatherList.get(position)).getDtTxt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat mDateFormat = new SimpleDateFormat(context.getString(R.string.dateFormat2), Locale.ENGLISH);

        String time = mDateFormat.format(date);
        holder.textViewTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return weatherList.size() <= 5 ? weatherList.size() : 5;
    }
}
