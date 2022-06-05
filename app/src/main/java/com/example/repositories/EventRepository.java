package com.example.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.models.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventRepository {
    private final FirebaseFirestore DB;
    private final String COLLECTION_EVENTS = "events";
    private final String FIELD_NAME = "name";
    private final String FIELD_DETAIL = "detail";
    private final String FIELD_ORGANIZATION = "organization";
    private final String FIELD_LOCATION = "location";
    private final String FIELD_DATE = "date";
    private final String FIELD_START_TIME = "start_time";
    private final String FIELD_FINISH_TIME = "finish_time";
    public String loggedInUserEmail = "";
    private final String TAG = this.getClass().getCanonicalName();

    public EventRepository() {
        DB = FirebaseFirestore.getInstance();
    }

    public void addEvent(Event newEvent) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(FIELD_NAME, newEvent.getName());
            data.put(FIELD_DETAIL, newEvent.getDetail());
            data.put(FIELD_ORGANIZATION, newEvent.getOrganization());
            data.put(FIELD_LOCATION, newEvent.getLocation());
            data.put(FIELD_DATE, newEvent.getDate());
            data.put(FIELD_START_TIME, newEvent.getStart_time());
            data.put(FIELD_FINISH_TIME, newEvent.getFinish_time());

            DB.collection(COLLECTION_EVENTS).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.e(TAG, "onSuccess: Document successfully created with ID " + documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: Error while creating document" + e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "addEvent: " + e.getLocalizedMessage());
        }
    }

    public void getEvents() {}
}
