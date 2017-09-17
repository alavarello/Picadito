package com.picadito.picadito.Activities.Displayers;

import android.widget.ImageView;
import android.widget.TextView;

import com.picadito.picadito.Activities.Fragments.UserFragment;
import com.picadito.picadito.Activities.MainActivity;
import com.picadito.picadito.Model.DownLoader;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */
public class UserDisplayer {

    private User user;
    private ImageView profilePicture;
    private ImageView profilePictureDrawer;
    private TextView statusDrawer;
    private TextView profileNameTextDrawer;
    private TextView profileNameText;
    private TextView statusText;
    private UserFragment userFragment;

    public UserDisplayer(User user, UserFragment userFragment) {
        this.user = user;
        profilePicture = (ImageView) userFragment.getView().findViewById(R.id.userFragment_profilePictureImageView);
        profilePictureDrawer = (ImageView) userFragment.getView().findViewById(R.id.userFragmentDrawer_profilePictureImageView);
        profileNameText = (TextView) userFragment.getView().findViewById(R.id.userFragment_profileNameTextView);
        profileNameTextDrawer = (TextView) userFragment.getView().findViewById(R.id.userFragmentDrawer_nameTextView);
        statusText = (TextView) userFragment.getView().findViewById(R.id.userFragment_stateTextView);
        statusDrawer = (TextView) userFragment.getView().findViewById(R.id.userFragmentDrawer_userNameTextView);
        this.userFragment = userFragment;

    }
    public void displayUser() {
        profileNameText.setText(user.getName());
        statusText.setText(user.getStatus());
        statusDrawer.setText(user.getStatus());
        profileNameTextDrawer.setText(user.getName());
        showProfilePicture();
    }

    private void showProfilePicture() {
        DownLoader.showProfilePicture(user, profilePicture, userFragment.getContext());
        DownLoader.showProfilePicture(user, profilePictureDrawer, userFragment.getContext());
    }
}
