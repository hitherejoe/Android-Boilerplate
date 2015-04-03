package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Boilerplate implements Parcelable {

    public int id;
    public String androidBoilerplate;

    public Boilerplate() { }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.androidBoilerplate);
    }

    private Boilerplate(Parcel in) {
        this.id = in.readInt();
        this.androidBoilerplate = in.readString();
    }

    public static final Parcelable.Creator<Boilerplate> CREATOR = new Parcelable.Creator<Boilerplate>() {
        public Boilerplate createFromParcel(Parcel source) {
            return new Boilerplate(source);
        }

        public Boilerplate[] newArray(int size) {
            return new Boilerplate[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Boilerplate that = (Boilerplate) o;

        if (id != that.id) return false;
        if (androidBoilerplate != null ? !androidBoilerplate.equals(that.androidBoilerplate) : that.androidBoilerplate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (androidBoilerplate != null ? androidBoilerplate.hashCode() : 0);
        return result;
    }
}
