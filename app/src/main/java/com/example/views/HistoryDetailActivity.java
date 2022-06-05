package com.example.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Volunteer volunteer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteered);

//        Need volunteer

        findViews();
        loadViews();
    }

    public void loadViews() {
//        name.setText(volunteer.event.getName());
//        detail.setText(volunteer.event.getDetail());
//        organization.setText(volunteer.event.getOrganization());
//        location.setText(volunteer.event.getLocation());
//        date.setText(volunteer.event.getDate());
//        start_time.setText(volunteer.event.getStart_time());
//        finish_time.setText(volunteer.event.getFinish_time());
        user_location.setText(volunteer.getLocation());
        hours.setText(volunteer.getHours()+"");
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
