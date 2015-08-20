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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (id != character.id) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;
        if (description != null ? !description.equals(character.description) : character.description != null)
            return false;
        if (thumbnail != null ? !thumbnail.equals(character.thumbnail) : character.thumbnail != null)
            return false;
        if (comics != null ? !comics.equals(character.comics) : character.comics != null)
            return false;
        if (series != null ? !series.equals(character.series) : character.series != null)
            return false;
        if (stories != null ? !stories.equals(character.stories) : character.stories != null)
            return false;
        return !(events != null ? !events.equals(character.events) : character.events != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (comics != null ? comics.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (stories != null ? stories.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        return result;
    }


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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Thumbnail thumbnail = (Thumbnail) o;

            if (path != null ? !path.equals(thumbnail.path) : thumbnail.path != null) return false;
            return !(extension != null ? !extension.equals(thumbnail.extension) : thumbnail.extension != null);

        }

        @Override
        public int hashCode() {
            int result = path != null ? path.hashCode() : 0;
            result = 31 * result + (extension != null ? extension.hashCode() : 0);
            return result;
        }
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
