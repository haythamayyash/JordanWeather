package com.haythamayyash.jordanweather.util;

import com.haythamayyash.jordanweather.model.WeatherResponse;

import io.realm.Realm;
import io.realm.RealmResults;

public class LocalDataBase {
    private Realm realm;

    public LocalDataBase() {
        realm = Realm.getDefaultInstance();
    }

    public void saveToDB(WeatherResponse weatherResponse) {
        realm.beginTransaction();
        realm.insertOrUpdate(weatherResponse);
        realm.commitTransaction();
    }

    public RealmResults<WeatherResponse> readFromDB(int cityId) {
        RealmResults<WeatherResponse> weatherResponses = realm.where(WeatherResponse.class)
                .equalTo("cityId", cityId).findAll();
        weatherResponses.load();
        return weatherResponses;
    }

    public boolean isDBEmpty() {
        return realm.isEmpty();
    }

    public Realm getRealm() {
        return realm;
    }
}
