package com.haythamayyash.jordanweather.presentation.weather_main.view;

import com.haythamayyash.jordanweather.network.FetchWeatherUseCase;
import com.haythamayyash.jordanweather.presentation.base.ObservableViewMvp;

import java.util.List;

public interface WeatherMainViewMvp extends ObservableViewMvp<WeatherMainViewMvp.Listener> {
    interface Listener {
        void dataBaseHasData();
    }

    void bindCityIdListAndPrepareAdapter(List<Integer> cityIdList, FetchWeatherUseCase fetchWeatherUseCase);

    void showProgressBar(boolean show);

    void showRecyclerView(boolean show);


}
