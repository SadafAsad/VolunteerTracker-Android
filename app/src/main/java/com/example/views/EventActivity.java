package com.example.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.models.Event;

public class EventActivity extends AppCompatActivity {
    TextView name;
    TextView detail;
    TextView organization;
    TextView location;
    TextView date;
    TextView start_time;
    TextView finish_time;
    Button register;

    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Need Event

        findViews();
        loadViews();
        onClickListener();
    }

    public void loadViews() {
        name.setText(event.getName());
        detail.setText(event.getDetail());
        organization.setText(event.getOrganization());
        location.setText(event.getLocation());
        date.setText(event.getDate());
        start_time.setText(event.getStart_time());
        finish_time.setText(event.getFinish_time());
    }

    public void onClickListener() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Register for this event
            }
        });
    }

    public void findViews() {
        name = (TextView) findViewById(R.id.event_name);
        detail = (TextView) findViewById(R.id.event_detail);
        organization = (TextView) findViewById(R.id.event_organization);
        location = (TextView) findViewById(R.id.event_location);
        date = (TextView) findViewById(R.id.event_date);
        start_time = (TextView) findViewById(R.id.event_start);
        finish_time = (TextView) findViewById(R.id.event_finish);
        register = (Button) findViewById(R.id.register);
    }
}
