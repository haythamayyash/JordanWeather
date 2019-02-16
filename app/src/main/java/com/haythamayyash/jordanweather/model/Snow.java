package com.haythamayyash.jordanweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Snow extends RealmObject implements Parcelable {

    @SerializedName("3h")
    @Expose
    private Double _3h;

    public Snow() {
    }

    protected Snow(Parcel in) {
        if (in.readByte() == 0) {
            _3h = null;
        } else {
            _3h = in.readDouble();
        }
    }

    public static final Creator<Snow> CREATOR = new Creator<Snow>() {
        @Override
        public Snow createFromParcel(Parcel in) {
            return new Snow(in);
        }

        @Override
        public Snow[] newArray(int size) {
            return new Snow[size];
        }
    };

    public Double get3h() {
        return _3h;
    }

    public void set3h(Double _3h) {
        this._3h = _3h;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_3h == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(_3h);
        }
    }
}