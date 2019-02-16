package com.haythamayyash.jordanweather.presentation.weather_main.presenter;

import com.haythamayyash.jordanweather.common.Constants;
import com.haythamayyash.jordanweather.network.FetchWeatherUseCase;
import com.haythamayyash.jordanweather.presentation.weather_main.view.WeatherMainViewMvp;
import com.haythamayyash.jordanweather.util.LocalDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Since i will add unit test in the future, i extract another presenter Component
 * to this class, to be testable.
 */

public class MainPresneter implements WeatherMainViewMvp.Listener {
    private final LocalDataBase localDataBase;
    private List<Integer> cityIdList = new ArrayList<>();
    private final FetchWeatherUseCase fetchWeatherUseCase;
    private WeatherMainViewMvp weatherMainViewMvp;

    public MainPresneter(LocalDataBase localDataBase, FetchWeatherUseCase fetchWeatherUseCase) {
        this.localDataBase = localDataBase;
        this.fetchWeatherUseCase = fetchWeatherUseCase;
    }


    void onCreate() {
        prepareView();
    }

    void onDestroy() {
        localDataBase.getRealm().close();
    }

    void onStart() {
        weatherMainViewMvp.registerListener(this);
    }

    void onStop() {
        weatherMainViewMvp.unRegisterListener(this);
    }

    void bindView(WeatherMainViewMvp weatherMainViewMvp) {
        this.weatherMainViewMvp = weatherMainViewMvp;
    }

    private void prepareView() {
        prepareDefaultCityIdList();
        if (localDataBase.isDBEmpty()) {
            weatherMainViewMvp.showProgressBar(true);
            weatherMainViewMvp.showRecyclerView(false);
        }
        weatherMainViewMvp.bindCityIdListAndPrepareAdapter(cityIdList, fetchWeatherUseCase);
    }

    private void prepareDefaultCityIdList() {
        cityIdList.add(Constants.CITY_ID.AMMAN);
        cityIdList.add(Constants.CITY_ID.AQABA);
        cityIdList.add(Constants.CITY_ID.IRBID);
    }

    /**
     * if database not empty , hide progressbar and show recyclerview.
     * (progress bar for an activity displaying just first time when local db is empty,
     * and still doesn't get data from api)
     */
    @Override
    public void dataBaseHasData() {
        weatherMainViewMvp.showProgressBar(false);
        weatherMainViewMvp.showRecyclerView(true);

    }
}