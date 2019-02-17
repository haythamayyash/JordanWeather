package com.haythamayyash.jordanweather.presentation.weather_main.presenter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.haythamayyash.jordanweather.common.BaseActivity;
import com.haythamayyash.jordanweather.di.PresenterCompositionRoot;
import com.haythamayyash.jordanweather.presentation.weather_main.view.WeatherMainViewMvpImpl;

public class MainActivity extends BaseActivity {

    private MainPresneter mainPresneter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterCompositionRoot compositionRoot = getCompositionRoot(this);
        WeatherMainViewMvpImpl weatherMainViewMvp = compositionRoot.getWeatherMainViewMvp(null);

        mainPresneter = compositionRoot.getMainPresenter();
        mainPresneter.bindView(weatherMainViewMvp);
        mainPresneter.onCreate();
        setContentView(weatherMainViewMvp.getView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresneter.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresneter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresneter.onStop();
    }
}

