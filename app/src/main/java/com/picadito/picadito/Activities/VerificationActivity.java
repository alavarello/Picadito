package com.picadito.picadito.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.picadito.picadito.Model.DownLoader;
import com.picadito.picadito.Model.Notification;
import com.picadito.picadito.Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by agust on 8/28/2017.
 */

public class VerificationActivity extends AppCompatActivity {


    public User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    final DatabaseReference userDataBaseReference = database.getReference();
                    DatabaseReference specificUserDataBase = userDataBaseReference.child("user").child(firebaseUser.getUid());
                    specificUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            user = DownLoader.downLoadUser(dataSnapshot);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user", (Serializable) user);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // ...
                        }
                    });
                }else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
        //checkGooglePlayServices();

    }

    private void checkGooglePlayServices() {
        GoogleApiAvailability googleApi = GoogleApiAvailability.getInstance();
        int status = googleApi.isGooglePlayServicesAvailable(getApplicationContext());
        if(status != ConnectionResult.SUCCESS){
            googleApi.getErrorDialog(this,status, 1);
        }
    }
}
