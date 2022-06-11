package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.models.Event;
import com.example.repositories.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private final EventRepository repository = new EventRepository();
    private static EventViewModel instance;
    public MutableLiveData<List<Event>> allEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
    }

    public static EventViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new EventViewModel(application);
        }
        return instance;
    }

    public EventRepository getEventRepository() {
        return this.repository;
    }

    public void getEvents(){
        this.repository.getEvents();
        this.allEvents = this.repository.allEvents;
    }
}
