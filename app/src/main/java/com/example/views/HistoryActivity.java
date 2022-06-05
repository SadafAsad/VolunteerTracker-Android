package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.adapters.HistoryAdapter;
import com.example.models.Volunteer;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ListView volunteered_list;

    ArrayList<Volunteer> volunteered;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        Set volunteered

        findViews();
        onClickListener();

        HistoryAdapter historyAdapter = new HistoryAdapter(getApplicationContext(), volunteered);
        volunteered_list.setAdapter(historyAdapter);
    }

    public void onClickListener() {
        this.volunteered_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("VOLUNTEERED",(Serializable)volunteered.get(i));
                intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });
    }

    public void findViews() {
        volunteered_list = (ListView) findViewById(R.id.volunteered_list);
    }
}
