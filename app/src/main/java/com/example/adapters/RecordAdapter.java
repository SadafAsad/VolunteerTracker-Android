package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnVolunteerClickListener;
import com.example.databinding.ActivityRecordCardBinding;
import com.example.models.Volunteer;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private final String TAG = "RecordAdapter";
    private final Context context;
    private final ArrayList<Volunteer> volunteerArrayList;
    private final OnVolunteerClickListener clickListener;

    public RecordAdapter(Context context, ArrayList<Volunteer> volunteerArrayList, OnVolunteerClickListener clickListener) {
        this.context = context;
        this.volunteerArrayList = volunteerArrayList;
        this.clickListener = clickListener;
    }

    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecordAdapter.RecordViewHolder(ActivityRecordCardBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecordViewHolder holder, int position) {
        Volunteer currentVolunteer = volunteerArrayList.get(position);
        holder.bind(context, currentVolunteer, clickListener);
    }

    @Override
    public int getItemCount() {
        return this.volunteerArrayList.size();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder{
        ActivityRecordCardBinding binding;

        public RecordViewHolder(ActivityRecordCardBinding b){
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
                binding.getRoot().setBackgroundColor(0xFFFFFFFF);
            }

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("RecordViewHolder", "onClick: Selected Volunteer : " + currentVolunteer.toString());
                    clickListener.onVolunteerItemClicked(currentVolunteer);
                }
            });
        }
    }
}
