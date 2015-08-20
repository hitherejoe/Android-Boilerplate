package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return !(name != null ? !name.equals(item.name) : item.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
