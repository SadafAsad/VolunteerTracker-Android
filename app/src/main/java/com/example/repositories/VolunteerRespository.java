package com.example.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.models.Event;
import com.example.models.Volunteer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolunteerRespository {
    private final FirebaseFirestore DB;
    private final String COLLECTION_VOLUNTEERS = "volunteers";
    private final String COLLECTION_USERS = "users";
    private final String FIELD_EVENT = "event";
    private final String FIELD_DONE = "done";
    private final String FIELD_LOCATION = "location";
    private final String FIELD_HOURS = "hours";
    public String loggedInUserEmail = "";
    private final String TAG = this.getClass().getCanonicalName();
    public MutableLiveData<List<Volunteer>> allVolunteered = new MutableLiveData<>();

    public VolunteerRespository() {
        DB = FirebaseFirestore.getInstance();
    }

    public void addVolunteer(Volunteer newVolunteer) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(FIELD_EVENT, newVolunteer.getEvent());
            data.put(FIELD_DONE, newVolunteer.getDone());
            data.put(FIELD_LOCATION, newVolunteer.getLocation());
            data.put(FIELD_HOURS, newVolunteer.getHours());

            DB.collection(COLLECTION_USERS).document(loggedInUserEmail).collection(COLLECTION_VOLUNTEERS).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
            Log.e(TAG, "addVolunteer: " + e.getLocalizedMessage());
        }
    }

    public void getVolunteers() {
        try{
            DB.collection(COLLECTION_USERS)
                    .document(loggedInUserEmail)
                    .collection(COLLECTION_VOLUNTEERS)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Volunteer> volunteeredList = new ArrayList<>();

                            if (queryDocumentSnapshots.isEmpty()){
                                Log.e(TAG, "onSuccess: No volunteered");
                            }else{
                                Log.e(TAG, "onSuccess: queryDocumentSnapshots" + queryDocumentSnapshots.getDocumentChanges() );

                                for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
                                    Volunteer currentVolunteer = documentChange.getDocument().toObject(Volunteer.class);
                                    currentVolunteer.setId(documentChange.getDocument().getId());
                                    volunteeredList.add(currentVolunteer);
                                    Log.e(TAG, "onSuccess: currentVolunteered " + currentVolunteer.toString() );
                                }

                                allVolunteered.postValue(volunteeredList);
                            }
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "getVolunteers: " + ex.getLocalizedMessage() );
        }
    }

    public void updateVolunteer(Volunteer v) {
        Map<String, Object> data = new HashMap<>();
        data.put(FIELD_EVENT, v.getEvent());
        data.put(FIELD_DONE, v.getDone());
        data.put(FIELD_LOCATION, v.getLocation());
        data.put(FIELD_HOURS, v.getHours());

        try{
            DB.collection(COLLECTION_USERS)
                    .document(loggedInUserEmail)
                    .collection(COLLECTION_VOLUNTEERS)
                    .document(v.getId())
                    .update(data)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e(TAG, "onSuccess: document successfully updated");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "onFailure: Unable to update document" + e.getLocalizedMessage());
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "updateVolunteer: " + ex.getLocalizedMessage() );
        }
    }
}
