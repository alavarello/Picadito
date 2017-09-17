package com.picadito.picadito.Activities.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.picadito.picadito.Activities.Displayers.FriendsDisplayable;
import com.picadito.picadito.Activities.Displayers.FriendsDisplayer;
import com.picadito.picadito.Model.Friend;
import com.picadito.picadito.R;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class FriendsFragment extends Fragment {

    LinearLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scrolling_layout, container, false);
        FriendsDisplayable activity = (FriendsDisplayable) getActivity();
        layout = (LinearLayout) view.findViewById(R.id.scrollingFragment_mainLayoutLinearLayout);
        List<String> friends = activity.getFriends();
        SortedSet<Friend> srotedFriends = new TreeSet<>();
        for(String f: friends){
            DatabaseReference friendDataBaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(f);
          //TODO

            srotedFriends.add(new Friend());
        }
        FriendsDisplayer friendsDisplayer = new FriendsDisplayer(srotedFriends,(AppCompatActivity) this.getActivity(), layout);
        friendsDisplayer.displayFriends();
        return view;
    }
}

