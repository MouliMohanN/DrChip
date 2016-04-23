package com.drchip.android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohann on 23-04-2016.
 */
public class HomePageBundle implements Parcelable {

    private boolean showOs;
    private String type;

    public HomePageBundle() {
    }

    public HomePageBundle(boolean showOs, String type) {
        this.showOs = showOs;
        this.type = type;
    }

    public boolean isShowOs() {
        return showOs;
    }

    public void setShowOs(boolean showOs) {
        this.showOs = showOs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HomePageBundle{" +
                "showOs=" + showOs +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(showOs ? (byte) 1 : (byte) 0);
        dest.writeString(this.type);
    }

    protected HomePageBundle(Parcel in) {
        this.showOs = in.readByte() != 0;
        this.type = in.readString();
    }

    public static final Parcelable.Creator<HomePageBundle> CREATOR = new Parcelable.Creator<HomePageBundle>() {
        @Override
        public HomePageBundle createFromParcel(Parcel source) {
            return new HomePageBundle(source);
        }

        @Override
        public HomePageBundle[] newArray(int size) {
            return new HomePageBundle[size];
        }
    };
}
