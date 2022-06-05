package com.example.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final FirebaseFirestore DB;
    private final String COLLECTION_USERS = "users";
    private final String FIELD_EMAIL = "email";
    private final String FIELD_PASSWORD = "password";
    private final String FIELD_NAME = "name";
    private final String TAG = this.getClass().getCanonicalName();

    public UserRepository() {
        DB = FirebaseFirestore.getInstance();
    }

    public void addUser(User newUser) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put(FIELD_EMAIL, newUser.getEmail());
            data.put(FIELD_PASSWORD, newUser.getPassword());
            data.put(FIELD_NAME, newUser.getName());

            DB.collection(COLLECTION_USERS).document(newUser.getEmail()).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.e(TAG, "onSuccess: Document added successfully");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "onFailure: Error while creating document" + e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "addUser: " + e.getLocalizedMessage());
        }
    }
}
