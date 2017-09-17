package com.picadito.picadito.Activities.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.picadito.picadito.Activities.Displayers.MatchesDisplayer;
import com.picadito.picadito.Activities.FriendMainActivity;
import com.picadito.picadito.Model.Friend;
import com.picadito.picadito.R;

/**
 * Created by Agustin Lavarello on 8/8/2017.
 */
public class FriendMatchesFragment extends Fragment {

   LinearLayout layout;
    private Friend friend;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scrolling_layout, container, false);

        layout = (LinearLayout) view.findViewById(R.id.scrollingFragment_mainLayoutLinearLayout);
        friend = ((FriendMainActivity)getActivity()).getFriend();
        MatchesDisplayer matchesDisplayer = new MatchesDisplayer(getActivity(), layout);
        matchesDisplayer.displayMatches();

        return view;

    }
}
