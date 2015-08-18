package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Collection implements Parcelable {
    public List<Item> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.items);
    }

    public Collection() {
    }

    protected Collection(Parcel in) {
        this.items = new ArrayList<>();
        in.readList(this.items, getClass().getClassLoader());
    }

    public static final Parcelable.Creator<Collection> CREATOR = new Parcelable.Creator<Collection>() {
        public Collection createFromParcel(Parcel source) {
            return new Collection(source);
        }

        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };
}
