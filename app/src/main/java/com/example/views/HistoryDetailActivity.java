package com.example.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.databinding.ActivityVolunteeredBinding;
import com.example.models.Volunteer;

public class HistoryDetailActivity extends AppCompatActivity {
    ActivityVolunteeredBinding binding;

    private String TAG = this.getClass().getCanonicalName();
    Volunteer volunteered;
    private SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVolunteeredBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);
        
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
