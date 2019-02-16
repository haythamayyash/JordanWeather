package com.haythamayyash.jordanweather.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.haythamayyash.jordanweather.network.FetchWeatherUseCase;
import com.haythamayyash.jordanweather.network.WeatherApi;
import com.haythamayyash.jordanweather.presentation.weather_details.view.WeatherDetailsViewMvpImpl;
import com.haythamayyash.jordanweather.presentation.weather_main.view.WeatherMainViewMvpImpl;
import com.haythamayyash.jordanweather.util.LocalDataBase;

/**
 * Implement Dependency Injection Arch. pattern,
 * this class work on activity lifecycle.
 */
public class PresenterCompositionRoot {
    private CompositionRoot compositionRoot;
    private Activity activity;

    public PresenterCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    private Context getContext() {
        return activity;
    }

    private WeatherApi getWeatherApi() {
        return compositionRoot.getWeatherApi();
    }

    public FetchWeatherUseCase getFetchWeatherUseCase() {
        return new FetchWeatherUseCase(getWeatherApi());
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public WeatherMainViewMvpImpl getWeatherMainViewMvp(ViewGroup parent) {
        return new WeatherMainViewMvpImpl(getLayoutInflater(), parent);
    }

    public WeatherDetailsViewMvpImpl getWeatherDetailsViewMvp(ViewGroup parent) {
        return new WeatherDetailsViewMvpImpl(getLayoutInflater(), parent);
    }

    public LocalDataBase getLocalDataBase() {
        return compositionRoot.getLocalDataBase();
    }
}
