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

import com.example.OnRecordClickListener;
import com.example.R;
import com.example.adapters.RecordAdapter;
import com.example.databinding.ActivityRecordBinding;
import com.example.models.Volunteer;
import com.example.viewmodels.VolunteerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity implements OnRecordClickListener {

    ActivityRecordBinding binding;

    private ArrayList<Volunteer> volunteerArrayList = new ArrayList<>();
    private RecordAdapter recordAdapter;
    private VolunteerViewModel volunteerViewModel;

    private String TAG = this.getClass().getCanonicalName();
    private SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);

//        RecyclerView set up
        this.volunteerArrayList = new ArrayList<>();
        this.recordAdapter = new RecordAdapter(this, this.volunteerArrayList, this::onRecordItemClicked);

        this.binding.volunteeredListRecord.setLayoutManager(new LinearLayoutManager(this));
        this.binding.volunteeredListRecord.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
        this.binding.volunteeredListRecord.setAdapter(this.recordAdapter);

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RecordActivity","----------onResume");

        this.volunteerViewModel.getVolunteersRecord();
        this.volunteerViewModel.volunteersRecord.observe(this, new Observer<List<Volunteer>>() {
            @Override
            public void onChanged(List<Volunteer> volunteers) {
                if (volunteers.isEmpty()){
                    Log.e(TAG, "onChanged: No volunteers");
                }else{
                    volunteerArrayList.clear();
                    volunteerArrayList.addAll(volunteers);
                    recordAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onRecordItemClicked(Volunteer volunteer) {
        Log.d(TAG, "onRecordItemClicked: event selected " + volunteer.toString());
        Intent intent = new Intent(RecordActivity.this, RecordUpdateActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("VOLUNTEERED RECORD",(Serializable)volunteer);
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
                Intent mainIntent = new Intent(this, SignInActivity.class);
                this.prefs.edit().clear().commit();
                startActivity(mainIntent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
