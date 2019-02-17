package com.haythamayyash.jordanweather.presentation.weather_main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haythamayyash.jordanweather.R;
import com.haythamayyash.jordanweather.common.Constants;
import com.haythamayyash.jordanweather.model.Main;
import com.haythamayyash.jordanweather.model.Weather;
import com.haythamayyash.jordanweather.model.WeatherResponse;
import com.haythamayyash.jordanweather.network.FetchWeatherUseCase;
import com.haythamayyash.jordanweather.presentation.weather_details.presenter.WeatherDetailsActivity;
import com.haythamayyash.jordanweather.util.LocalDataBase;
import com.haythamayyash.jordanweather.util.WeatherColor;
import com.haythamayyash.jordanweather.viewholder.MainWeatherViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

public class MainWeatherAdapter extends RecyclerView.Adapter<MainWeatherViewHolder> {
    private final Listener listener;
    private Context context;
    private List<Integer> cityIdList;
    private Intent intent;
    private LocalDataBase dataBase;
    private final FetchWeatherUseCase fetchWeatherUseCase;

    public interface Listener {
        void showSnackBar(String message);

        void dataBaseHasData();
    }

    public MainWeatherAdapter(Context context, Listener listener, List<Integer> cityIdList, FetchWeatherUseCase fetchWeatherUseCase) {
        this.context = context;
        this.cityIdList = cityIdList;
        this.listener = listener;
        this.fetchWeatherUseCase = fetchWeatherUseCase;
        dataBase = new LocalDataBase();
    }

    @Override
    public MainWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_weather_row_item, parent, false);
        return new MainWeatherViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(MainWeatherViewHolder holder, int position) {
        RealmResults<WeatherResponse> weatherResponseRealmResults = dataBase.readFromDB(cityIdList.get(position));

        if (!weatherResponseRealmResults.isEmpty() && weatherResponseRealmResults.first() != null)
            updateView(holder, weatherResponseRealmResults.first(), position);

        fetchWeatherUseCase.fetchWeatherAndNotify(cityIdList.get(position))
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (dataBase.isDBEmpty())
                            holder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        Log.d("myInfo", weatherResponse.getCity().getName());
                        holder.progressBar.setVisibility(View.GONE);
                        weatherResponse.setCityId(weatherResponse.getCity().getId());
                        dataBase.saveToDB(weatherResponse);
                        updateView(holder, weatherResponse, position);
                        if (!dataBase.isDBEmpty())
                            listener.dataBaseHasData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        holder.progressBar.setVisibility(View.GONE);
                        listener.showSnackBar(context.getString(R.string.connection_error_message));
                    }

                    @Override
                    public void onComplete() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void updateView(MainWeatherViewHolder viewHolder, WeatherResponse weatherResponse, int position) {
        Weather weather = Objects.requireNonNull(weatherResponse.getFullWeatherDetails()
                .get(0)).getWeather().get(0);
        Main main = weatherResponse.getFullWeatherDetails().get(0).getMain();
        String weatherStatus = weather.getMain();
        Picasso.get().load(Constants.BASE_IMAGE_URL + weather.getIcon() + ".png")
                .placeholder(R.drawable.image_place_holder).into(viewHolder.imageViewWeather);
        viewHolder.cardView.setCardBackgroundColor(WeatherColor.getWeatherResourceColor(context, weatherStatus));
        viewHolder.textViewCityName.setText(weatherResponse.getCity().getName());
        viewHolder.textViewDescription.setText(weather.getDescription());
        viewHolder.textViewTemperature.setText(String.format(Locale.getDefault()
                , context.getString(R.string.temperatureForamt), main.getTemp() - 273.15));
        viewHolder.cardView.setOnClickListener(v -> {
            intent = new Intent(context, WeatherDetailsActivity.class);
            intent.putExtra(Constants.INTENT_CITY_ID, cityIdList.get(position));
            animateOpenningDetailsActivity(viewHolder);
        });

    }

    /**
     * implement shared transition when open details activity.
     * this condition because there is a bug in android lollipop version when perform pair transition
     *
     * @param viewHolder
     */
    private void animateOpenningDetailsActivity(MainWeatherViewHolder viewHolder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Pair<View, String> p1 = Pair.create(viewHolder.cardView, context.getString(R.string.transitionBackground));
            Pair<View, String> p2 = Pair.create(viewHolder.imageViewWeather, context.getString(R.string.transitionImageViewWeather));
            Pair<View, String> p3 = Pair.create(viewHolder.textViewCityName, context.getString(R.string.transitionCityNameWeather));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, p1, p2, p3);
            context.startActivity(intent, options.toBundle());
        } else {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, viewHolder.cardView
                            , context.getString(R.string.transitionBackground));
            context.startActivity(intent, options.toBundle());
        }
    }


    @Override
    public int getItemCount() {
        return cityIdList.size();
    }

}
