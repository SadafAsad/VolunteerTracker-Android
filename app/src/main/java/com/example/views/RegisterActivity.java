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
import com.example.viewmodels.VolunteerViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityRegisterBinding binding;
    private VolunteerViewModel volunteerViewModel;

    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.event = (Event) args.getSerializable("EVENT");

        loadViews();
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

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.register:{
                    Volunteer v = new Volunteer();
                    v.setEvent(event.getName());
                    v.setDone(false);
                    v.setHours(0.0);
                    v.setLocation("");
                    v.setDate(event.getDate());
                    this.volunteerViewModel.addVolunteer(v);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }
}
