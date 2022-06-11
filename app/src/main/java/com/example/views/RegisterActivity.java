package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.databinding.ActivityRegisterBinding;
import com.example.models.Event;
import com.example.models.Volunteer;

public class RegisterActivity extends AppCompatActivity {
    TextView name;
    TextView detail;
    TextView organization;
    TextView location;
    TextView date;
    TextView start_time;
    TextView finish_time;
    Button register;

    ActivityRegisterBinding binding;

    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityRegisterBinding.inflate(this.getLayoutInflater());
        setContentView(this.binding.getRoot());

//        Intent intent = getIntent();
//        Bundle args = intent.getBundleExtra("BUNDLE");
//        this.event = (Event) args.getSerializable("EVENT");
//
//        findViews();
//        loadViews();
//        onClickListener();
    }

    public void loadViews() {
        name.setText(event.getName());
        detail.setText(event.getDetail());
        organization.setText(event.getOrganization());
        location.setText(event.getLocation());
        date.setText(event.getDate());
        start_time.setText(event.getStart_time());
        finish_time.setText(event.getFinish_time());
//        Search in user.volunteered for the events
//        if any event matches this one
//        then the register button text should change to "Registered" and become disable
    }

    public void onClickListener() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Register for this event
//                Create a new Volunteer
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_LONG);
                toast.show();
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
