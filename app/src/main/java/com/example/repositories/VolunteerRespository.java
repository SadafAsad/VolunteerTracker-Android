package com.example.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.models.Volunteer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class VolunteerRespository {
    private final FirebaseFirestore DB;
    private final String COLLECTION_VOLUNTEERS = "volunteers";
    private final String COLLECTION_EVENTS = "events";
    private final String COLLECTION_USERS = "users";
    private final String FIELD_DONE = "done";
    private final String FIELD_LOCATION = "location";
    private final String FIELD_HOURS = "hours";
    public String loggedInUserEmail = "";
    private final String TAG = this.getClass().getCanonicalName();

    public VolunteerRespository() {
        DB = FirebaseFirestore.getInstance();
    }

    public void addVolunteer(Volunteer newVolunteer) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(FIELD_DONE, newVolunteer.getDone());
            data.put(FIELD_LOCATION, newVolunteer.getLocation());
            data.put(FIELD_HOURS, newVolunteer.getHours());

            DB.collection(COLLECTION_USERS).document(loggedInUserEmail).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.e(TAG, "onSuccess: Document added successfully ");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: Error while creating document" + e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "addVolunteer: " + e.getLocalizedMessage());
        }
    }

    public void getVolunteers() {}
}
