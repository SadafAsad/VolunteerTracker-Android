package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnEventClickListener;
import com.example.OnVolunteerClickListener;
import com.example.R;
import com.example.databinding.ActivityHistoryCardBinding;
import com.example.databinding.ActivityNewsfeedCardBinding;
import com.example.models.Event;
import com.example.models.Volunteer;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final String TAG = "EventAdapter";
    private final Context context;
    private final ArrayList<Event> eventArrayList;
    private final OnEventClickListener clickListener;

    public EventAdapter(Context context, ArrayList<Event> eventArrayList, OnEventClickListener clickListener) {
        this.context = context;
        this.eventArrayList = eventArrayList;
        this.clickListener = clickListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(ActivityNewsfeedCardBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event currentEvent = eventArrayList.get(position);
        holder.bind(context, currentEvent, clickListener);
    }

    @Override
    public int getItemCount() {
        return this.eventArrayList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        ActivityNewsfeedCardBinding binding;

        public EventViewHolder(ActivityNewsfeedCardBinding b){
            super(b.getRoot());
            this.binding = b;
        }

        public void bind(Context context, final Event currentEvent, OnEventClickListener clickListener){
            binding.eventName.setText(currentEvent.getName());
            binding.eventDate.setText(currentEvent.getDate());
            binding.eventLocation.setText(currentEvent.getLocation());
            binding.eventTime.setText(currentEvent.getStart_time()+"-"+currentEvent.getFinish_time());

//            Search in user.volunteered for the events
//            if any events in there matches this event
//            then view's background color should be yellow

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("EventViewHolder", "onClick: Selected Event : " + currentEvent.toString());
                    clickListener.onEventItemClicked(currentEvent);
                }
            });
        }
    }
}

//public class EventAdapter extends BaseAdapter {
//    private Context context;
//    private ArrayList<Event> events;
//    private LayoutInflater inflater;
//
//    public EventAdapter(Context context,  ArrayList<Event> events) {
//        this.context = context;
//        this.events = events;
//        this.inflater = (LayoutInflater.from(context));
//    }
//
//    @Override
//    public int getCount() {
//        return this.events.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return this.events.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = inflater.inflate(R.layout.activity_newsfeed_card, null);
//        TextView name = (TextView) view.findViewById(R.id.event_name);
//        TextView date = (TextView) view.findViewById(R.id.event_date);
//        TextView time = (TextView) view.findViewById(R.id.event_time);
//        TextView location = (TextView) view.findViewById(R.id.event_location);
//        name.setText(this.events.get(i).getName());
//        date.setText(this.events.get(i).getDate());
//        time.setText(this.events.get(i).getStart_time()+"-"+this.events.get(i).getFinish_time());
//        location.setText(this.events.get(i).getLocation());
//
////        Search in user.volunteered for the events
////        if any events in there matches this event
////        then view's background color should be yellow
//
//        return view;
//    }
//}
