package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.adapters.HistoryAdapter;
import com.example.databinding.ActivityHistoryBinding;
import com.example.models.Volunteer;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ActivityHistoryBinding binding;

    ArrayList<Volunteer> volunteered;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Retrieve volunteered for the logged in user

        onClickListener();

        HistoryAdapter historyAdapter = new HistoryAdapter(getApplicationContext(), volunteered);
        this.binding.volunteeredList.setAdapter(historyAdapter);
    }

    public void onClickListener() {
        this.binding.volunteeredList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
}
