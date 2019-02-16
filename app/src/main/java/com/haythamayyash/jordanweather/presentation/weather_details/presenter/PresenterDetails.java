package com.haythamayyash.jordanweather.presentation.weather_details.presenter;

import android.content.Intent;

import com.haythamayyash.jordanweather.common.Constants;
import com.haythamayyash.jordanweather.model.WeatherResponse;
import com.haythamayyash.jordanweather.presentation.weather_details.view.WeatherDetailsViewMvp;
import com.haythamayyash.jordanweather.util.LocalDataBase;

import io.realm.RealmResults;

/**
 * Since i will add unit test in the future, i extract another presenter Component
 * to this class, to be testable.
 */
public class PresenterDetails {
    private final LocalDataBase localDataBase;
    private WeatherDetailsViewMvp weatherDetailsViewMvpImpl;

    PresenterDetails(LocalDataBase localDataBase) {
        this.localDataBase = localDataBase;
    }

    void bindView(WeatherDetailsViewMvp weatherDetailsViewMvp) {
        this.weatherDetailsViewMvpImpl = weatherDetailsViewMvp;
    }

    void onCreate(Intent intent) {
        int cityId = intent.getIntExtra(Constants.INTENT_CITY_ID, Constants.CITY_ID.AMMAN);
        RealmResults<WeatherResponse> weatherResponseRealmResults = localDataBase.readFromDB(cityId);
        WeatherResponse weatherResponse = weatherResponseRealmResults.first();
        weatherDetailsViewMvpImpl.bindWeather(weatherResponse);
    }

    void onDestroy() {
        if (!localDataBase.getRealm().isClosed())
            localDataBase.getRealm().close();
    }
}
