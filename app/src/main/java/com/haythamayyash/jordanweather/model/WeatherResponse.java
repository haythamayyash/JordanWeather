package com.haythamayyash.jordanweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherResponse extends RealmObject implements Parcelable {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private RealmList<FullWeatherDetails> fullWeatherDetails = null;
    @SerializedName("city")
    @Expose
    private City city;

    @PrimaryKey
    private Integer cityId;

    public WeatherResponse() {
    }

    protected WeatherResponse(Parcel in) {
        cod = in.readString();
        if (in.readByte() == 0) {
            message = null;
        } else {
            message = in.readDouble();
        }
        if (in.readByte() == 0) {
            cnt = null;
        } else {
            cnt = in.readInt();
        }
        if (in.readByte() == 0) {
            cityId = null;
        } else {
            cityId = in.readInt();
        }
        this.fullWeatherDetails = new RealmList<>();
        this.fullWeatherDetails.addAll(in.createTypedArrayList(FullWeatherDetails.CREATOR));

    }

    public static final Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };

    public Integer getCityId() {
        return city.getId();
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public RealmList<FullWeatherDetails> getFullWeatherDetails() {
        return fullWeatherDetails;
    }

    public WeatherResponse setFullWeatherDetails(RealmList<FullWeatherDetails> fullWeatherDetails) {
        this.fullWeatherDetails = new RealmList<>();
        this.fullWeatherDetails.addAll(fullWeatherDetails);
//        this.fullWeatherDetails = fullWeatherDetails;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod);
        if (message == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(message);
        }
        if (cnt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cnt);
        }
        if (cityId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cityId);
        }
        dest.writeInt(fullWeatherDetails != null ? 1 : 0);
        if (fullWeatherDetails != null) {
            dest.writeTypedList(fullWeatherDetails);

        }
    }
}