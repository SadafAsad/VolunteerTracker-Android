package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.OnRVClickListener;
import com.example.R;
import com.example.adapters.HistoryAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements OnRVClickListener {
    ActivityHistoryBinding binding;

    private ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private VolunteerViewModel volunteerViewModel;

    private String TAG = this.getClass().getCanonicalName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        RecyclerView set up
        this.volunteerArrayList = new ArrayList<>();
        this.historyAdapter = new HistoryAdapter(this, this.volunteerArrayList, this::onRVItemClicked);

        this.binding.volunteeredList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.volunteeredList.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
        this.binding.volunteeredList.setAdapter(this.historyAdapter);

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HistoryActivity","----------onResume");

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
                    historyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onRVItemClicked(Volunteer volunteer) {
        Log.d(TAG, "onRVItemClicked: Volunteer selected " + volunteer.toString());
        Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("VOLUNTEERED",(Serializable)volunteer);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
}
