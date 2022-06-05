package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.models.Volunteer;

public class HistoryDetailActivity extends AppCompatActivity {
    TextView name;
    TextView detail;
    TextView organization;
    TextView location;
    TextView date;
    TextView start_time;
    TextView finish_time;
    TextView user_location;
    TextView hours;

    Volunteer volunteered;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteered);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.volunteered = (Volunteer) args.getSerializable("VOLUNTEERED");

        findViews();
        loadViews();
    }

    public void loadViews() {
        name.setText(volunteered.getEvent().getName());
        detail.setText(volunteered.getEvent().getDetail());
        organization.setText(volunteered.getEvent().getOrganization());
        location.setText(volunteered.getEvent().getLocation());
        date.setText(volunteered.getEvent().getDate());
        start_time.setText(volunteered.getEvent().getStart_time());
        finish_time.setText(volunteered.getEvent().getFinish_time());
        user_location.setText(volunteered.getLocation());
        hours.setText(volunteered.getHours()+"");
    }

    public void findViews() {
        name = (TextView) findViewById(R.id.event_name);
        detail = (TextView) findViewById(R.id.event_detail);
        organization = (TextView) findViewById(R.id.event_organization);
        location = (TextView) findViewById(R.id.event_location);
        date = (TextView) findViewById(R.id.event_date);
        start_time = (TextView) findViewById(R.id.event_start);
        finish_time = (TextView) findViewById(R.id.event_finish);
        user_location = (TextView) findViewById(R.id.user_location_holder);
        hours = (TextView) findViewById(R.id.user_hours);
    }
}
