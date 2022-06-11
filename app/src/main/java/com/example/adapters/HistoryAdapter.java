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

import com.example.OnRVClickListener;
import com.example.R;
import com.example.databinding.ActivityHistoryCardBinding;
import com.example.models.Volunteer;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private final String TAG = "HistoryAdapter";
    private final Context context;
    private final ArrayList<Volunteer> volunteerArrayList;
    private final OnRVClickListener clickListener;

    public HistoryAdapter(Context context, ArrayList<Volunteer> volunteerArrayList, OnRVClickListener clickListener) {
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

        public void bind(Context context, final Volunteer currentVolunteer, OnRVClickListener clickListener){
            binding.eventName.setText(currentVolunteer.getEvent().getName());
            binding.date.setText(currentVolunteer.getEvent().getDate());
            binding.hours.setText("" + currentVolunteer.getHours());

            if (currentVolunteer.getDone()) {
                binding.getRoot().setBackgroundColor(0xFF00FF00);
            }
            else {
                binding.getRoot().setBackgroundColor(0xFF000000);
            }

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("HistoryViewHolder", "onClick: Selected Volunteer : " + currentVolunteer.toString());
                    clickListener.onRVItemClicked(currentVolunteer);
                }
            });
        }
    }
}

//public class HistoryAdapter extends BaseAdapter {
//    private Context context;
//    private ArrayList<Volunteer> volunteered;
//    private LayoutInflater inflater;
//
//    public HistoryAdapter(Context context,  ArrayList<Volunteer> volunteered) {
//        this.context = context;
//        this.volunteered = volunteered;
//        this.inflater = (LayoutInflater.from(context));
//    }
//
//    @Override
//    public int getCount() {
//        return this.volunteered.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return this.volunteered.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = inflater.inflate(R.layout.activity_history_card, null);
//        TextView name = (TextView) view.findViewById(R.id.event_name);
//        TextView date = (TextView) view.findViewById(R.id.event_date);
//        TextView hours = (TextView) view.findViewById(R.id.user_hours);
//        name.setText(this.volunteered.get(i).getEvent().getName());
//        date.setText(this.volunteered.get(i).getEvent().getDate());
//        hours.setText(this.volunteered.get(i).getHours()+"");
//
//        if (this.volunteered.get(i).getDone()) {
//            view.setBackgroundColor(0xFF00FF00);
//        }
//        else {
//            view.setBackgroundColor(0xFF000000);
//        }
//
//        return view;
//    }
//}
