package com.haythamayyash.jordanweather.presentation.weather_details.view;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haythamayyash.jordanweather.DetailsWeatherAdapter;
import com.haythamayyash.jordanweather.R;
import com.haythamayyash.jordanweather.common.Constants;
import com.haythamayyash.jordanweather.model.Main;
import com.haythamayyash.jordanweather.model.Weather;
import com.haythamayyash.jordanweather.model.WeatherResponse;
import com.haythamayyash.jordanweather.presentation.base.BaseViewMvp;
import com.haythamayyash.jordanweather.util.WeatherColor;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Objects;


public class WeatherDetailsViewMvpImpl extends BaseViewMvp implements WeatherDetailsViewMvp {
    private ImageView imageViewWeather;
    private TextView textViewDescription;
    private TextView textViewTemparature;
    private ConstraintLayout activityLayout;
    private TextView textViewCity;
    private RecyclerView recyclerViewWeather;


    public WeatherDetailsViewMvpImpl(LayoutInflater inflater, ViewGroup parent) {
        setView(inflater.inflate(R.layout.activity_weather_details, parent, false));
        imageViewWeather = findViewById(R.id.imageView_weatherDetails);
        textViewDescription = findViewById(R.id.textView_weatherDescription);
        textViewTemparature = findViewById(R.id.textView_temperature);
        activityLayout = findViewById(R.id.layout_activityWeatherDetails);
        textViewCity = findViewById(R.id.textView_cityName);
        recyclerViewWeather = findViewById(R.id.recyclerView_weatherDetails);
    }

    @Override
    public void bindWeather(WeatherResponse weatherResponse) {
        Weather weather = Objects.requireNonNull(weatherResponse.getFullWeatherDetails()
                .get(0)).getWeather().get(0);

        String weatherStatus = weather.getMain();

        Picasso.get().load(Constants.BASE_IMAGE_URL + weather.getIcon() + ".png")
                .placeholder(R.drawable.image_place_holder).into(imageViewWeather);

        activityLayout.setBackgroundColor(WeatherColor.getWeatherResourceColor(getContext(), weatherStatus));


        Main main = Objects.requireNonNull(weatherResponse.getFullWeatherDetails()
                .get(0)).getMain();

        textViewCity.setText(weatherResponse.getCity().getName());
        textViewDescription.setText(weather.getDescription());
        textViewTemparature.setText(String.format(Locale.getDefault()
                , getContext().getString(R.string.temperatureForamt), main.getTemp() - 273.15));

        DetailsWeatherAdapter detailsWeatherAdapter = new DetailsWeatherAdapter(getContext(),
                weatherResponse.getFullWeatherDetails());
        recyclerViewWeather.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        recyclerViewWeather.setAdapter(detailsWeatherAdapter);

        //focusable is false to avoid focus on recyclerview when activity starting
        recyclerViewWeather.setFocusable(false);
    }

}
