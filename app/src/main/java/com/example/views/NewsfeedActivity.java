package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.adapters.EventAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.databinding.ActivityNewsfeedBinding;
import com.example.models.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsfeedActivity extends AppCompatActivity {
    ActivityNewsfeedBinding binding;

    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewsfeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Retrieve events

        onClickListener();

        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), events);
        this.binding.eventsList.setAdapter(eventAdapter);
    }

    public void onClickListener() {
        this.binding.eventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewsfeedActivity.this, RegisterActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("EVENT",(Serializable)events.get(i));
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });

        this.binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeedActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        this.binding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeedActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
