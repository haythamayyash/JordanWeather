package com.haythamayyash.jordanweather.util;

import android.content.Context;

import com.haythamayyash.jordanweather.R;

public class WeatherColor {

    public static int getWeatherResourceColor(Context context, String weatherStatus) {
        switch (weatherStatus) {
            case "Clear":
                return context.getResources().getColor(R.color.clearSkyWeatherColor);
            case "Rain":
            case "Snow":
            case "Drizzle":
                return context.getResources().getColor(R.color.rainWeatherColor);
            case "Atmosphere":
                return context.getResources().getColor(R.color.atmosphereWeatherColor);
            case "Clouds":
                return context.getResources().getColor(R.color.cloudWeatherColor);
            default:
                return context.getResources().getColor(R.color.clearSkyWeatherColor);
        }

    }
}
