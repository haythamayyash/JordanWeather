package com.haythamayyash.jordanweather.presentation.weather_details.presenter;

import android.os.Bundle;

import com.haythamayyash.jordanweather.common.BaseActivity;
import com.haythamayyash.jordanweather.di.PresenterCompositionRoot;
import com.haythamayyash.jordanweather.presentation.weather_details.view.WeatherDetailsViewMvp;

public class WeatherDetailsActivity extends BaseActivity {
    private PresenterDetails presenterDetails;
    private WeatherDetailsViewMvp weatherDetailsViewMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterCompositionRoot compositionRoot = getCompositionRoot(this);
        weatherDetailsViewMvp = compositionRoot.getWeatherDetailsViewMvp(null);
        setContentView(weatherDetailsViewMvp.getView());

        presenterDetails = new PresenterDetails(compositionRoot.getLocalDataBase());
        presenterDetails.bindView(weatherDetailsViewMvp);
        presenterDetails.onCreate(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterDetails.onDestroy();
    }

}

