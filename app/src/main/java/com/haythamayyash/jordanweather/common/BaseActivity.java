package com.haythamayyash.jordanweather.common;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.haythamayyash.jordanweather.di.PresenterCompositionRoot;

public class BaseActivity extends AppCompatActivity {
    protected PresenterCompositionRoot getCompositionRoot(Activity activity) {
        return new PresenterCompositionRoot(((App) getApplication()).getCompositionRoot(), activity);
    }
}
