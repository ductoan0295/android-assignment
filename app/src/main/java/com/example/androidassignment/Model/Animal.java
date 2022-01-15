package com.example.androidassignment.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };
    public String name;
    public int icon_drawable;
    public int background_drawable;
    public String description;
    public boolean isLiked;
    public String type;

    public Animal(String name, int icon_drawable, int background_drawable, String description, boolean isLiked, String type) {
        this.name = name;
        this.icon_drawable = icon_drawable;
        this.background_drawable = background_drawable;
        this.description = description;
        this.isLiked = isLiked;
        this.type = type;
    }

    protected Animal(Parcel in) {
        name = in.readString();
        icon_drawable = in.readInt();
        background_drawable = in.readInt();
        description = in.readString();
        isLiked = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(icon_drawable);
        parcel.writeInt(background_drawable);
        parcel.writeString(description);
        parcel.writeBoolean(isLiked);
    }
}
