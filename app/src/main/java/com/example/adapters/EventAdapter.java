package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnEventClickListener;
import com.example.databinding.ActivityNewsfeedCardBinding;
import com.example.models.Event;

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