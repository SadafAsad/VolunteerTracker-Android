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
        this.binding.eventName.setText(volunteered.getEvent());
        this.binding.eventDetail.setText("");
        this.binding.eventOrganization.setText("");
        this.binding.eventLocation.setText("");
        this.binding.eventDate.setText(volunteered.getDate());
        this.binding.eventStart.setText("");
        this.binding.eventFinish.setText("");
        this.binding.userLocationHolder.setText(volunteered.getLocation());
        this.binding.userHours.setText(volunteered.getHours()+"");
    }
}
