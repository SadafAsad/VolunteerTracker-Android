package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnVolunteerClickListener;
import com.example.databinding.ActivityHistoryCardBinding;
import com.example.models.Volunteer;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final String TAG = "HistoryAdapter";
    private final Context context;
    private final ArrayList<Volunteer> volunteerArrayList;
    private final OnVolunteerClickListener clickListener;

    public HistoryAdapter(Context context, ArrayList<Volunteer> volunteerArrayList, OnVolunteerClickListener clickListener) {
        this.context = context;
        this.volunteerArrayList = volunteerArrayList;
        this.clickListener = clickListener;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(ActivityHistoryCardBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Volunteer currentVolunteer = volunteerArrayList.get(position);
        holder.bind(context, currentVolunteer, clickListener);
    }

    @Override
    public int getItemCount() {
        return this.volunteerArrayList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        ActivityHistoryCardBinding binding;

        public HistoryViewHolder(ActivityHistoryCardBinding b){
            super(b.getRoot());
            this.binding = b;
        }

        public void bind(Context context, final Volunteer currentVolunteer, OnVolunteerClickListener clickListener){
            binding.eventName.setText(currentVolunteer.getEvent());
            binding.date.setText(currentVolunteer.getDate());
            binding.hours.setText("" + currentVolunteer.getHours());

            if (currentVolunteer.isDone()) {
                binding.getRoot().setBackgroundColor(0xFF00FF00);
            }
            else {
                binding.getRoot().setBackgroundColor(0xFF000000);
            }

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("HistoryViewHolder", "onClick: Selected Volunteer : " + currentVolunteer.toString());
                    clickListener.onVolunteerItemClicked(currentVolunteer);
                }
            });
        }
    }
}
