package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.models.Event;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Event> events;
    private LayoutInflater inflater;

    public EventAdapter(Context context,  ArrayList<Event> events) {
        this.context = context;
        this.events = events;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.events.size();
    }

    @Override
    public Object getItem(int i) {
        return this.events.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_newsfeed_card, null);
        TextView name = (TextView) view.findViewById(R.id.event_name);
        TextView date = (TextView) view.findViewById(R.id.event_date);
        TextView time = (TextView) view.findViewById(R.id.event_time);
        TextView location = (TextView) view.findViewById(R.id.event_location);
        name.setText(this.events.get(i).getName());
        date.setText(this.events.get(i).getDate());
        time.setText(this.events.get(i).getStart_time()+"-"+this.events.get(i).getFinish_time());
        location.setText(this.events.get(i).getLocation());

//        if user has registered for this event before, background will be yellow

        return view;
    }
}
