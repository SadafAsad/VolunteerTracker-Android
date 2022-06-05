package com.example.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Volunteer implements Parcelable {
    private String id;
    private Boolean done;
    private String location;
    private Double hours;

    public Volunteer() {}

    public Volunteer(Boolean done, String location, Double hours) {
        this.done = done;
        this.location = location;
        this.hours = hours;
    }

    protected Volunteer(Parcel in) {
        id = in.readString();
        //done = in.readBoolean();
        location = in.readString();
        hours = in.readDouble();
    }

    public static final Creator<Volunteer> CREATOR = new Creator<Volunteer>() {
        @Override
        public Volunteer createFromParcel(Parcel in) {
            return new Volunteer(in);
        }

        @Override
        public Volunteer[] newArray(int size) {
            return new Volunteer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id='" + id + '\'' +
                ", done='" + done + '\'' +
                ", location='" + location + '\'' +
                ", hours='" + hours + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        //dest.writeBoolean(done);
        dest.writeString(location);
        dest.writeDouble(hours);
    }
}