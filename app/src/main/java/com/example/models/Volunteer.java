package com.example.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Volunteer implements Parcelable, Serializable {
    private String id;
    private String event;
    private String location;
    private Double hours;
    private String date;
    private boolean done;

    public Volunteer() {}

    public Volunteer(String event, String location, Double hours, String date, boolean done) {
        this.event = event;
        this.location = location;
        this.hours = hours;
        this.date = date;
        this.done = done;
    }

    protected Volunteer(Parcel in) {
        id = in.readString();
        event = in.readString();
        location = in.readString();
        hours = in.readDouble();
        date = in.readString();
        done = in.readBoolean();
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id='" + id + '\'' +
                ", event='" + event + '\'' +
                ", location='" + location + '\'' +
                ", hours='" + hours + '\'' +
                ", date='" + date + '\'' +
                ", done='" + done + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(event);
        dest.writeString(location);
        dest.writeDouble(hours);
        dest.writeString(date);
        dest.writeBoolean(done);
    }
}