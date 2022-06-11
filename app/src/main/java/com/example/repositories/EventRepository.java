package com.example.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.models.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public MutableLiveData<List<Event>> allEvents = new MutableLiveData<>();

    public EventRepository() {
        DB = FirebaseFirestore.getInstance();
    }


    public void getEvents() {
        try{
            DB.collection(COLLECTION_EVENTS)
                    .orderBy(FIELD_NAME, Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Event> eventList = new ArrayList<>();

                            if (queryDocumentSnapshots.isEmpty()){
                                Log.e(TAG, "onSuccess: No events");
                            }else{
                                Log.e(TAG, "onSuccess: queryDocumentSnapshots" + queryDocumentSnapshots.getDocumentChanges() );

                                for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
                                    Event currentEvent = documentChange.getDocument().toObject(Event.class);
                                    currentEvent.setId(documentChange.getDocument().getId());
                                    eventList.add(currentEvent);
                                    Log.e(TAG, "onSuccess: currentEvent " + currentEvent.toString() );
                                }

                                allEvents.postValue(eventList);
                            }
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "getEvents: " + ex.getLocalizedMessage() );
        }
    }
}
