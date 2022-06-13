package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.OnVolunteerClickListener;
import com.example.adapters.HistoryAdapter;
import com.example.adapters.RecordAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityRecordBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements OnVolunteerClickListener {

    ActivityRecordBinding binding;

    private ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();
    private RecordAdapter recordAdapter;
    private VolunteerViewModel volunteerViewModel;

    private String TAG = this.getClass().getCanonicalName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        RecyclerView set up
        this.volunteerArrayList = new ArrayList<>();
        this.recordAdapter = new RecordAdapter(this, this.volunteerArrayList, this::onVolunteerItemClicked);

        this.binding.volunteeredListRecord.setLayoutManager(new LinearLayoutManager(this));
        this.binding.volunteeredListRecord.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
        this.binding.volunteeredListRecord.setAdapter(this.recordAdapter);

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RecordActivity","----------onResume");

        this.volunteerViewModel.getVolunteers();
        this.volunteerViewModel.allVolunteered.observe(this, new Observer<List<Volunteer>>() {
            @Override
            public void onChanged(List<Volunteer> volunteers) {
                if (volunteers.isEmpty()){
                    Log.e(TAG, "onChanged: No volunteers");
                }else{
                    for(Volunteer v : volunteers){
                        Log.e(TAG, "onChanged: v : " + v.toString() );
                    }

                    volunteerArrayList.clear();
                    volunteerArrayList.addAll(volunteers);
                    recordAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onVolunteerItemClicked(Volunteer volunteer) {
        Log.d(TAG, "onRVItemClicked: Volunteer selected " + volunteer.toString());
        Intent intent = new Intent(RecordActivity.this, RecordUpdateActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("VOLUNTEERED RECORD",(Serializable)volunteer);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
}
