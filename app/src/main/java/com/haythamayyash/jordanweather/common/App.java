package com.haythamayyash.jordanweather.common;

import android.app.Application;

import com.haythamayyash.jordanweather.di.CompositionRoot;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    CompositionRoot compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        compositionRoot = new CompositionRoot();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm realm = Realm.getInstance(config); // Automatically run migration if needed
        Realm.setDefaultConfiguration(config);
        realm.close();


    }

    public CompositionRoot getCompositionRoot() {
        return compositionRoot;
    }
}
