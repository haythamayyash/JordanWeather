package com.haythamayyash.jordanweather.di;

import com.haythamayyash.jordanweather.network.WeatherApi;
import com.haythamayyash.jordanweather.util.LocalDataBase;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.haythamayyash.jordanweather.common.Constants.BASE_URL;

/**
 * Implement Dependency Injection Arch. pattern,
 * this class work on app lifecycle.
 */
public class CompositionRoot {
    private Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    WeatherApi getWeatherApi() {
        return getRetrofit().create(WeatherApi.class);
    }

    LocalDataBase getLocalDataBase() {
        return new LocalDataBase();
    }
}
