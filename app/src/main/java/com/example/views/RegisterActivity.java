package com.example.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.R;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityRegisterBinding;
import com.example.models.Event;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityRegisterBinding binding;
    private VolunteerViewModel volunteerViewModel;
    private String TAG = this.getClass().getCanonicalName();
    private Volunteer v;

    Event event;
    private SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);

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
//        Search in user.volunteer for the event
//        if any event matches this one
//        then the register button text should change to "Registered" and become disable
        this.volunteerViewModel.getVolunteer(event.getName());
        this.volunteerViewModel.volunteer.observe(this, new Observer<List<Volunteer>>() {
            @Override
            public void onChanged(List<Volunteer> volunteer) {
                if (volunteer.isEmpty()){
                    Log.e(TAG, "onChanged: No volunteer");
                    registerBtn(false);
                }else{
                    registerBtn(true);
                }
            }
        });
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
                    registerBtn(true);
                }
            }
        }
    }

    public void registerBtn(boolean registered) {
        if (registered) {
            this.binding.register.setText("Registered");
            this.binding.register.setEnabled(false);
        }
        else {
            this.binding.register.setText("Register");
            this.binding.register.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:{
                this.finishAffinity();
                Intent mainIntent = new Intent(this, SignInActivity.class);
                this.prefs.edit().clear().commit();
                startActivity(mainIntent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
