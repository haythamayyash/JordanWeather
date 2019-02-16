package com.haythamayyash.jordanweather.presentation.base;

public interface ObservableViewMvp<ListenerType> extends ViewMvp {
    void registerListener(ListenerType listenerType);

    void unRegisterListener(ListenerType listenerType);
}
