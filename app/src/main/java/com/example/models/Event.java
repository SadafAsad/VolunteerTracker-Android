package com.example.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Event implements Parcelable, Serializable {
    private String name;
    private String detail;
    private String organization;
    private String location;
    private String date;
    private String start_time;
    private String finish_time;

    public Event() {}

    public Event(String name, String detail, String organization, String location, String date, String start_time, String finish_time) {
        this.name = name;
        this.detail = detail;
        this.organization = organization;
        this.location = location;
        this.date = date;
        this.start_time = start_time;
        this.finish_time = finish_time;
    }

    protected Event(Parcel in) {
        name = in.readString();
        detail = in.readString();
        organization = in.readString();
        location = in.readString();
        date = in.readString();
        start_time = in.readString();
        finish_time = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", organization='" + organization + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeString(organization);
        dest.writeString(location);
        dest.writeString(date);
        dest.writeString(start_time);
        dest.writeString(finish_time);
    }
}