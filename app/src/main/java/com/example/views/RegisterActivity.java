package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityRegisterBinding;
import com.example.models.Event;
import com.example.models.Volunteer;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.event = (Event) args.getSerializable("EVENT");

        loadViews();
        onClickListener();
    }

    public void loadViews() {
        this.binding.eventName.setText(event.getName());
        this.binding.eventDetail.setText(event.getDetail());
        this.binding.eventOrganization.setText(event.getOrganization());
        this.binding.eventLocation.setText(event.getLocation());
        this.binding.eventDate.setText(event.getDate());
        this.binding.eventStart.setText(event.getStart_time());
        this.binding.eventFinish.setText(event.getFinish_time());
//        Search in user.volunteered for the events
//        if any event matches this one
//        then the register button text should change to "Registered" and become disable
    }

    public void onClickListener() {
        this.binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Register for this event
//                Create a new Volunteer
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
