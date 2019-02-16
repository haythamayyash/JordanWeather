package com.haythamayyash.jordanweather.network;

import com.haythamayyash.jordanweather.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/forecast?appid=9a40ef2a4c35689b29978690445e03e0")
    Observable<WeatherResponse> getWeatherResponse(@Query("id") String cityId);

}
