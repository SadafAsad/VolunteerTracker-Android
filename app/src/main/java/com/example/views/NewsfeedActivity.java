package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.OnEventClickListener;
import com.example.R;
import com.example.adapters.EventAdapter;
import com.example.adapters.HistoryAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityNewsfeedBinding;
import com.example.models.Event;
import com.example.models.Volunteer;
import com.example.viewmodels.EventViewModel;
import com.example.viewmodels.VolunteerViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsfeedActivity extends AppCompatActivity implements OnEventClickListener, View.OnClickListener {
    ActivityNewsfeedBinding binding;

    private ArrayList<Event> eventArrayList = new ArrayList<>();
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;

    private String TAG = this.getClass().getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewsfeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.binding.history.setOnClickListener(this::onClick);
        this.binding.record.setOnClickListener(this::onClick);

//        RecyclerView set up
        this.eventArrayList = new ArrayList<>();
        this.eventAdapter = new EventAdapter(this, this.eventArrayList, this::onEventItemClicked);

        this.binding.eventsList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.eventsList.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
        this.binding.eventsList.setAdapter(this.eventAdapter);

        this.eventViewModel = EventViewModel.getInstance(this.getApplication());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("NewsfeedActivity","----------onResume");

        this.eventViewModel.getEvents();
        this.eventViewModel.allEvents.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                if (events.isEmpty()){
                    Log.e(TAG, "onChanged: No events");
                }else{
                    eventArrayList.clear();
                    eventArrayList.addAll(events);
                    eventAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onEventItemClicked(Event event) {
        Intent intent = new Intent(NewsfeedActivity.this, RegisterActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("EVENT",(Serializable)event);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.history:{
                    Intent intent = new Intent(NewsfeedActivity.this, HistoryActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.record:{
                    Intent intent = new Intent(NewsfeedActivity.this, RecordActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            this.finishAffinity();
            Intent signIn = new Intent(this, SignInActivity.class);
            startActivity(signIn);
        }
        return super.onOptionsItemSelected(item);
    }
}
