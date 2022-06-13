package com.example.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.R;
import com.example.databinding.ActivityRecordUpdateBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

import java.util.List;
import java.util.Locale;

public class RecordUpdateActivity extends AppCompatActivity {

    ActivityRecordUpdateBinding binding;

    private GpsTracker gpsTracker;
    public Volunteer volunteeredRecord;
    private VolunteerViewModel volunteerViewModel;
    private final String TAG = this.getClass().getCanonicalName();
    String location = "";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_record_update);

        this.binding = ActivityRecordUpdateBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        gpsTracker = new GpsTracker(RecordUpdateActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            Log.e(TAG, "onCreate: Address: " + gpsTracker.getLocation().toString() + "\n" + latitude + " " + longitude );

            Geocoder geocoder = new Geocoder(RecordUpdateActivity.this, Locale.getDefault());
            try {

                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                Address obj = addresses.get(0);
                String  add = obj.getAddressLine(0);
                location = obj.getLocality();

                Log.e("Location", "Address "+ add + " \n" + location);
            }catch (Exception e) {
                Log.e(TAG, "onCreate: Location exception" + e.getLocalizedMessage() );
            }
        }else{
            gpsTracker.showSettingsAlert();
        }

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.volunteeredRecord = (Volunteer) args.getSerializable("VOLUNTEERED RECORD");

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
        loadViews();

        this.binding.btnUpdate.setOnClickListener(this::onClick);
    }

    public void loadViews() {
        this.id = volunteeredRecord.getId();
        this.binding.etEventName.setText(volunteeredRecord.getEvent());
        this.binding.etEventName.setEnabled(false);
        this.binding.etDate.setText(volunteeredRecord.getDate());
        this.binding.etDate.setEnabled(false);
        this.binding.etEventLocation.setText(location);
        this.binding.etEventLocation.setEnabled(false);
        this.binding.etEventHoursSpent.setText(volunteeredRecord.getHours() + "");
        if(volunteeredRecord.isDone()) {
            this.binding.rbAttended.setChecked(true);
        }
    }

    public void onClick(View view) {
        if(view != null){
            switch (view.getId()){
                case R.id.btnUpdate: {
                    Log.e(TAG, "onClick: Updating volunteer activity" );

                    Log.e(TAG, "onClick: Record ID: " + volunteeredRecord.getId() );
                    this.volunteeredRecord.setId(volunteeredRecord.getId());
                    this.volunteeredRecord.setHours(Double.valueOf(this.binding.etEventHoursSpent.getText().toString()));
                    this.volunteeredRecord.setLocation(this.binding.etEventLocation.getText().toString());
                    if(this.binding.rbAttended.isChecked()){
                        this.volunteeredRecord.setDone(true);
                    }
                    updateDetails(volunteeredRecord);
                    Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    private void updateDetails(Volunteer volunteer) {
        this.volunteerViewModel.updateVolunteer(volunteer);
    }
}