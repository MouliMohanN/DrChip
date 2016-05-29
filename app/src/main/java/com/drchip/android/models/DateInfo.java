package com.drchip.android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohann on 29-05-2016.
 */
public class DateInfo implements Parcelable {
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
    }

    public DateInfo() {
    }

    protected DateInfo(Parcel in) {
        this.date = in.readString();
    }

    public static final Parcelable.Creator<DateInfo> CREATOR = new Parcelable.Creator<DateInfo>() {
        @Override
        public DateInfo createFromParcel(Parcel source) {
            return new DateInfo(source);
        }

        @Override
        public DateInfo[] newArray(int size) {
            return new DateInfo[size];
        }
    };
}
