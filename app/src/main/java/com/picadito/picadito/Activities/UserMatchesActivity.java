package com.picadito.picadito.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.picadito.picadito.Activities.Displayers.MatchesDisplayable;
import com.picadito.picadito.Activities.Displayers.MatchesDisplayer;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.Model.UpLoader;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Agustin Lavarello on 7/18/2017.
 */

public class UserMatchesActivity extends AppCompatActivity implements MatchesDisplayable {

    private SortedSet<Match> matches = new TreeSet<>();
    private List<String> matchesList;
    private LinearLayout layout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = (User)  getIntent().getSerializableExtra("user");
        DatabaseReference matchDatabaseReference = database.getReference().child("matches");
        matchesList = user.getMatcheses();
            matchDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() != null) {
                        for(String m: matchesList){
                            Match match = dataSnapshot.child(m).getValue(Match.class);
                            if(match != null){
                                matches.add(match);
                            }
                        }
                        setContentView(R.layout.scrolling_layout);
                        layout = (LinearLayout) findViewById(R.id.scrollingFragment_mainLayoutLinearLayout);
                        FrameLayout fragmentLayout = (FrameLayout) findViewById(R.id.scrollingFragment_frameLayout);
                        fragmentLayout.removeView(findViewById(R.id.scrollingFragment_searchViewSearchView));
                        MatchesDisplayer matchesDisplayer = new MatchesDisplayer(UserMatchesActivity.this, layout);
                        matchesDisplayer.displayMatches();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });

    }

    public SortedSet<Match> getMatches(){return matches;}





}
