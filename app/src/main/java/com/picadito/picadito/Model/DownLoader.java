package com.picadito.picadito.Model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by agust on 9/4/2017.
 */

public class DownLoader {

    public final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final static DatabaseReference dataBaseReference = database.getReference();
    private final static FirebaseStorage storage = FirebaseStorage.getInstance();
    private final static StorageReference storageReference = storage.getReference();

    public static User downLoadUser(DataSnapshot dataSnapshot){
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
        return new User(dataSnapshot.child("name").getValue(String.class), dataSnapshot.child("userID").getValue(String.class), dataSnapshot.child("status").getValue(String.class), dataSnapshot.child("urlProfilePicture").getValue(String.class),
                dataSnapshot.child("matcheses").getValue(t),dataSnapshot.child("friends").getValue(t),new LinkedList<Notification>(),new LinkedList<String>() );
    }

    public static void showProfilePicture(final User user, final ImageView view, final Context context){
        storageReference.child("profilePictures").child(user.getUserID()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri).resize(300,300).into(view);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Picasso.with(context).load(user.getUrlProfilePicture()).resize(300,300).into(view);
            }
        });

    }

}
