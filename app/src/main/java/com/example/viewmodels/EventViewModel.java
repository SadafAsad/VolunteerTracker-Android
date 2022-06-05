package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.models.Event;
import com.example.repositories.EventRepository;

public class EventViewModel extends AndroidViewModel {
    private final EventRepository repository = new EventRepository();
    private static EventViewModel instance;

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

    public void addEvent(Event event) {
        this.repository.addEvent(event);
    }
}
