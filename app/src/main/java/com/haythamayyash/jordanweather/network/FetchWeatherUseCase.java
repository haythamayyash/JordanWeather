package com.haythamayyash.jordanweather.network;

import com.haythamayyash.jordanweather.model.WeatherResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FetchWeatherUseCase {
    private WeatherApi weatherApi;

    public FetchWeatherUseCase(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Observable<WeatherResponse> fetchWeatherAndNotify(Integer cityId) {
        return weatherApi.getWeatherResponse(String.valueOf(cityId)).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
