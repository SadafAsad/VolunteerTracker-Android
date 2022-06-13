package com.example.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.R;
import com.example.databinding.ActivityRecordUpdateBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

public class RecordUpdateActivity extends AppCompatActivity {

    ActivityRecordUpdateBinding binding;

    public Volunteer volunteeredRecord;
    private VolunteerViewModel volunteerViewModel;
    private final String TAG = this.getClass().getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_record_update);

        this.binding = ActivityRecordUpdateBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        this.volunteeredRecord = (Volunteer) args.getSerializable("VOLUNTEERED RECORD");

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
        loadViews();

        this.binding.btnUpdate.setOnClickListener(this::onClick);
    }

    public void loadViews() {
        this.binding.etEventName.setText(volunteeredRecord.getEvent());
        this.binding.etEventName.setEnabled(false);
        this.binding.etDate.setText(volunteeredRecord.getDate());
        this.binding.etDate.setEnabled(false);
        this.binding.etEventLocation.setText(volunteeredRecord.getLocation());
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
//                    if(this.validateHealthCardNumber()) {
//                        this.searchPatient();
//                        this.populateData();
//                    }

                    Log.e(TAG, "onClick: Record ID: " + volunteeredRecord.getId() );
                    this.volunteeredRecord.setId(volunteeredRecord.getId());
                    this.volunteeredRecord.setHours(Double.valueOf(this.binding.etEventHoursSpent.getText().toString()));
                    this.volunteeredRecord.setLocation(this.binding.etEventLocation.getText().toString());
                    if(this.binding.rbAttended.isChecked()){
                        this.volunteeredRecord.setDone(true);
                    }
                    updateDetails(volunteeredRecord);
                    break;
                }
            }
        }
    }

    private void updateDetails(Volunteer volunteer) {
        this.volunteerViewModel.updateVolunteer(volunteer);
    }
}