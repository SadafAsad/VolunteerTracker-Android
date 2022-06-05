package com.example.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.models.Event;

import java.util.ArrayList;

public class NewsfeedActivity extends AppCompatActivity {
    ListView events_list;
    Button history;
    Button record;

    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        findViews();
        onClickListener();
    }

    public void onClickListener() {
        this.events_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NewsfeedActivity.this, EventActivity.class);
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)events_list);
//                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });

        this.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeedActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        this.record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsfeedActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void findViews() {
        events_list = (ListView) findViewById(R.id.events_list);
        history = (Button) findViewById(R.id.history);
        record = (Button) findViewById(R.id.record);
    }
}
