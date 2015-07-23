package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class Ribot implements Parcelable {

    @SerializedName("_id")
    public String id;
    public String hexCode;
    public Info info;

    public Ribot() {
    }

    public static String[] getIds(Collection<Ribot> ribots) {
        String[] ids = new String[ribots.size()];
        int i = 0;
        for (Ribot ribot : ribots) {
            ids[i] = ribot.id;
            i++;
        }
        return ids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ribot ribot = (Ribot) o;

        if (id != null ? !id.equals(ribot.id) : ribot.id != null) return false;
        if (hexCode != null ? !hexCode.equals(ribot.hexCode) : ribot.hexCode != null) return false;
        return !(info != null ? !info.equals(ribot.info) : ribot.info != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (hexCode != null ? hexCode.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.hexCode);
        dest.writeParcelable(this.info, flags);
    }

    private Ribot(Parcel in) {
        this.id = in.readString();
        this.hexCode = in.readString();
        this.info = in.readParcelable(Info.class.getClassLoader());
    }

    public static final Parcelable.Creator<Ribot> CREATOR = new Parcelable.Creator<Ribot>() {
        public Ribot createFromParcel(Parcel source) {
            return new Ribot(source);
        }

        public Ribot[] newArray(int size) {
            return new Ribot[size];
        }
    };

    public static class Info implements Parcelable {
        public String firstName;
        public String lastName;
        public String role;

        public Info() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Info info = (Info) o;

            if (firstName != null ? !firstName.equals(info.firstName) : info.firstName != null)
                return false;
            if (lastName != null ? !lastName.equals(info.lastName) : info.lastName != null)
                return false;
            return !(role != null ? !role.equals(info.role) : info.role != null);

        }

        @Override
        public int hashCode() {
            int result = firstName != null ? firstName.hashCode() : 0;
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (role != null ? role.hashCode() : 0);
            return result;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.firstName);
            dest.writeString(this.lastName);
            dest.writeString(this.role);
        }

        private Info(Parcel in) {
            this.firstName = in.readString();
            this.lastName = in.readString();
            this.role = in.readString();
        }

        public static final Creator<Info> CREATOR = new Creator<Info>() {
            public Info createFromParcel(Parcel source) {
                return new Info(source);
            }

            public Info[] newArray(int size) {
                return new Info[size];
            }
        };
    }
}
