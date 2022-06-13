package com.example.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.OnVolunteerClickListener;
import com.example.R;
import com.example.adapters.HistoryAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements OnVolunteerClickListener {
    ActivityHistoryBinding binding;

    private ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private VolunteerViewModel volunteerViewModel;

    private String TAG = this.getClass().getCanonicalName();
    private SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);
        
//        RecyclerView set up
        this.volunteerArrayList = new ArrayList<>();
        this.historyAdapter = new HistoryAdapter(this, this.volunteerArrayList, this::onVolunteerItemClicked);

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
                    volunteerArrayList.clear();
                    volunteerArrayList.addAll(volunteers);
                    historyAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onVolunteerItemClicked(Volunteer volunteer) {
        Log.d(TAG, "onVolunteerItemClicked: Volunteer selected " + volunteer.toString());
        Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("VOLUNTEERED",(Serializable)volunteer);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
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
