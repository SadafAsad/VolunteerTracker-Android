package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityVolunteeredBinding;
import com.example.models.Volunteer;

public class HistoryDetailActivity extends AppCompatActivity {
    ActivityVolunteeredBinding binding;

    Volunteer volunteered;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVolunteeredBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.volunteered = (Volunteer) args.getSerializable("VOLUNTEERED");

        loadViews();
    }

    public void loadViews() {
        this.binding.eventName.setText(volunteered.getEvent().getName());
        this.binding.eventDetail.setText(volunteered.getEvent().getDetail());
        this.binding.eventOrganization.setText(volunteered.getEvent().getOrganization());
        this.binding.eventLocation.setText(volunteered.getEvent().getLocation());
        this.binding.eventDate.setText(volunteered.getEvent().getDate());
        this.binding.eventStart.setText(volunteered.getEvent().getStart_time());
        this.binding.eventFinish.setText(volunteered.getEvent().getFinish_time());
        this.binding.userLocationHolder.setText(volunteered.getLocation());
        this.binding.hours.setText(volunteered.getHours()+"");
    }
}
