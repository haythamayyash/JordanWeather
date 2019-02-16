package com.haythamayyash.jordanweather.presentation.weather_main.view;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.haythamayyash.jordanweather.MainWeatherAdapter;
import com.haythamayyash.jordanweather.R;
import com.haythamayyash.jordanweather.network.FetchWeatherUseCase;
import com.haythamayyash.jordanweather.presentation.base.BaseObservabelViewMvp;

import java.util.List;

public class WeatherMainViewMvpImpl extends BaseObservabelViewMvp<WeatherMainViewMvp.Listener> implements WeatherMainViewMvp, MainWeatherAdapter.Listener {
    private RecyclerView recyclerViewWeather;
    private ProgressBar progressBarMain;

    public WeatherMainViewMvpImpl(LayoutInflater inflater, ViewGroup parent) {
        setView(inflater.inflate(R.layout.activity_main, parent, false));
        recyclerViewWeather = findViewById(R.id.recyclerView_weather);
        progressBarMain = findViewById(R.id.progressBar_main);
    }

    @Override
    public void bindCityIdListAndPrepareAdapter(List<Integer> cityIdList, FetchWeatherUseCase fetchWeatherUseCase) {
        recyclerViewWeather.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewWeather.setAdapter(new MainWeatherAdapter(getContext(), this, cityIdList, fetchWeatherUseCase));
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show)
            progressBarMain.setVisibility(View.VISIBLE);
        else
            progressBarMain.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(boolean show) {
        if (show)
            recyclerViewWeather.setVisibility(View.VISIBLE);
        else
            recyclerViewWeather.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                .setActionTextColor(getContext().getResources()
                        .getColor(android.R.color.holo_blue_light)).show();
    }

    @Override
    public void dataBaseHasData() {
        for (Listener listener : getListeners()) {
            listener.dataBaseHasData();
        }
    }

    private void animateRecyclerView() {
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_recyclerview_animation);
        recyclerViewWeather.setLayoutAnimation(animation);
    }
}
