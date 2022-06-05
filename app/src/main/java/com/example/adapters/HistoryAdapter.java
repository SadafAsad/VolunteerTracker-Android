package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.models.Volunteer;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Volunteer> volunteered;
    private LayoutInflater inflater;

    public HistoryAdapter(Context context,  ArrayList<Volunteer> volunteered) {
        this.context = context;
        this.volunteered = volunteered;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.volunteered.size();
    }

    @Override
    public Object getItem(int i) {
        return this.volunteered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_history_card, null);
        TextView name = (TextView) view.findViewById(R.id.event_name);
        TextView date = (TextView) view.findViewById(R.id.event_date);
        TextView hours = (TextView) view.findViewById(R.id.user_hours);
        name.setText(this.volunteered.get(i).getEvent().getName());
        date.setText(this.volunteered.get(i).getEvent().getDate());
        hours.setText(this.volunteered.get(i).getHours()+"");

        if (this.volunteered.get(i).getDone()) {
            view.setBackgroundColor(0xFF00FF00);
        }
        else {
            view.setBackgroundColor(0xFF000000);
        }

        return view;
    }
}
