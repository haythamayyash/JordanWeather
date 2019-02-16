package com.haythamayyash.jordanweather.presentation.base;

import android.content.Context;
import android.view.View;

public class BaseViewMvp implements ViewMvp {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public View getView() {
        return view;
    }

    public Context getContext() {
        return getView().getContext();
    }

    public <T extends View> T findViewById(int id) {
        return getView().findViewById(id);
    }
}
