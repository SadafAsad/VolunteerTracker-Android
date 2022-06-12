package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private String TAG = this.getClass().getCanonicalName();
    private Volunteer v;

    Event event;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.binding.register.setOnClickListener(this::onClick);
        this.v = new Volunteer();
        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.event = (Event) args.getSerializable("EVENT");
        Log.e(TAG,"event: " + event.toString());

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
                    this.v.setEvent(event.getName());
                    this.v.setDone(false);
                    this.v.setHours(0.0);
                    this.v.setLocation("");
                    this.v.setDate(event.getDate());
                    Log.e(TAG, "volunteer: " + this.v.toString());
                    this.volunteerViewModel.addVolunteer(this.v);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully registered.", Toast.LENGTH_LONG);
                    toast.show();
                    this.binding.register.setText("Registered");
                    this.binding.register.setEnabled(false);
                }
            }
        }
    }
}
