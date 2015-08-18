package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Character implements Parcelable {

    public int id;
    public String name;
    public String description;
    public Thumbnail thumbnail;
    public Collection comics;
    public Collection series;
    public Collection stories;
    public Collection events;


    public static class Thumbnail implements Parcelable {
        public String path;
        public String extension;

        public String getImageUrl() {
            return path + "." + extension;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.path);
            dest.writeString(this.extension);
        }

        public Thumbnail() { }

        protected Thumbnail(Parcel in) {
            this.path = in.readString();
            this.extension = in.readString();
        }

        public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
            public Thumbnail createFromParcel(Parcel source) {
                return new Thumbnail(source);
            }

            public Thumbnail[] newArray(int size) {
                return new Thumbnail[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeParcelable(this.comics, flags);
        dest.writeParcelable(this.series, flags);
        dest.writeParcelable(this.stories, flags);
        dest.writeParcelable(this.events, flags);
    }

    public Character() { }

    protected Character(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        this.comics = in.readParcelable(Collection.class.getClassLoader());
        this.series = in.readParcelable(Collection.class.getClassLoader());
        this.stories = in.readParcelable(Collection.class.getClassLoader());
        this.events = in.readParcelable(Collection.class.getClassLoader());
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
