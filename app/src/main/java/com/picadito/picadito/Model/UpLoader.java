package com.picadito.picadito.Model;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

/**
 * Created by agust on 8/31/2017.
 */

public class UpLoader {

    private final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final static DatabaseReference dataBaseReference = database.getReference();
    private final static FirebaseStorage storage = FirebaseStorage.getInstance();
    private final static StorageReference storageReference = storage.getReference();

    public static void loadUser(User user){

        DatabaseReference specificUserDataBase = dataBaseReference.child("user").child(user.getUserID());
        specificUserDataBase.setValue(user);
    }

    public static void loadUserName(String userID, String newName){

        DatabaseReference specificUserDataBase = dataBaseReference.child("user").child(userID).child("name");
        specificUserDataBase.setValue(newName);
    }

    public static void loadUserStatus(String userID, String newStatus){

        DatabaseReference specificUserDataBase = dataBaseReference.child("user").child(userID).child("status");
        specificUserDataBase.setValue(newStatus);
    }

    public static void loadUserProfilePicture(String userID, String progilePicture){

        DatabaseReference specificUserDataBase = dataBaseReference.child("user").child(userID).child("urlProfilePicture");
        specificUserDataBase.setValue(progilePicture);
    }


    public static void loadUserProfilePictureStorage(String userID, Uri profilePicture){

        UploadTask uploadTask = storageReference.child("profilePictures").child(userID).putFile(profilePicture);

    }



    public static void loadMatchToUser(User user, Match match){
        DatabaseReference specificUserDataBase = dataBaseReference.child("user").child(user.getUserID());
        specificUserDataBase.child("matcheses").child(Integer.toString(user.getMatcheses().size())).setValue(match.getMatchID());
    }


    public static void loadMatch(Match match){
        DatabaseReference specificMatchDataBase = dataBaseReference.child("matches");
        String key = specificMatchDataBase.push().getKey();
        match.setMatchID(key);
        specificMatchDataBase.child(match.getMatchID()).setValue(match);

    }

    public static void loadTeam(Team team){
        DatabaseReference specificTeamDataBase = dataBaseReference.child("teams");
        String key = specificTeamDataBase.push().getKey();
        team.setTeamID(key);
        specificTeamDataBase.child(team.getTeamID()).setValue(team);
    }


}
