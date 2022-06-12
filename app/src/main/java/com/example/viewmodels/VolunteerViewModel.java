package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.models.Volunteer;
import com.example.repositories.VolunteerRespository;

import java.util.List;

public class VolunteerViewModel extends AndroidViewModel {
    private final VolunteerRespository repository = new VolunteerRespository();
    private static VolunteerViewModel instance;
    public MutableLiveData<List<Volunteer>> allVolunteered;
    public MutableLiveData<List<Volunteer>> volunteer;

    public VolunteerViewModel(@NonNull Application application) {
        super(application);
    }

    public static VolunteerViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new VolunteerViewModel(application);
        }
        return instance;
    }

    public VolunteerRespository getVolunteerRepository() {
        return this.repository;
    }

    public void addVolunteer(Volunteer volunteer) {
        this.repository.addVolunteer(volunteer);
    }

    public void getVolunteers(){
        this.repository.getVolunteers();
        this.allVolunteered = this.repository.allVolunteered;
    }

    public void updateVolunteer(Volunteer v){ this.repository.updateVolunteer(v);}

    public void getVolunteer(String name){
        this.repository.getVolunteer(name);
        this.volunteer = this.repository.volunteer;
    }
}
