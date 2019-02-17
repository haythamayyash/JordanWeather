package com.haythamayyash.jordanweather.presentation.base;

import java.util.ArrayList;
import java.util.List;

public class BaseObservabelViewMvp<ListenerType> extends BaseViewMvp
        implements ObservableViewMvp<ListenerType> {
    private List<ListenerType> mListeners = new ArrayList<>();

    @Override
    public final void registerListener(ListenerType listener) {
        mListeners.add(listener);
    }

    @Override
    public final void unRegisterListener(ListenerType listener) {
        mListeners.remove(listener);
    }

    protected final List<ListenerType> getListeners() {
        return mListeners;
    }
}
