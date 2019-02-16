package com.haythamayyash.jordanweather.presentation.weather_details.view;

import com.haythamayyash.jordanweather.model.WeatherResponse;
import com.haythamayyash.jordanweather.presentation.base.ViewMvp;

public interface WeatherDetailsViewMvp extends ViewMvp {
    void bindWeather(WeatherResponse weatherResponse);
}
