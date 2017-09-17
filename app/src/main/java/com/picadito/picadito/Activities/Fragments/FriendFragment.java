package com.picadito.picadito.Activities.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.picadito.picadito.Activities.FriendMainActivity;
import com.picadito.picadito.Model.Friend;
import com.picadito.picadito.R;

/**
 * Created by Agustin Lavarello on 8/8/2017.
 */
public class FriendFragment extends Fragment {

    private ImageView profilePicture;
    private TextView name;
    private TextView status;
    private Friend friend;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_main, container, false);

        profilePicture = (ImageView) view.findViewById(R.id.friendFragment_profilePictureImageView);
        name = (TextView) view.findViewById(R.id.friendFragment_friendNameTextView);
        status = (TextView) view.findViewById(R.id.friendFragment_friendStatus_TextView);
        friend = ((FriendMainActivity)getActivity()).getFriend();
        profilePicture.setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), friend.getProfilePicture()));
        name.setText(friend.getName());
        status.setText(friend.getStatus());

        return view;

    }
}
