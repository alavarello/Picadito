package com.picadito.picadito.Activities.Displayers;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.picadito.picadito.Activities.Fragments.UserFragment;
import com.picadito.picadito.Activities.MainActivity;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.Model.ImageLoader;
import com.picadito.picadito.R;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */
public class UserDisplayer {

    private UserGUI userGUI;
    private ImageView profilePicture;
    private TextView profileNameText;
    private TextView statusText;
    private UserFragment userFragment;

    public UserDisplayer(UserGUI userGUI, UserFragment userFragment) {
        this.userGUI = userGUI;
        profilePicture = (ImageView) userFragment.getView().findViewById(R.id.userFragment_profilePictureImageView);
        profileNameText = (TextView) userFragment.getView().findViewById(R.id.userFragment_profileNameTextView);
        statusText = (TextView) userFragment.getView().findViewById(R.id.userFragment_stateTextView);
        this.userFragment = userFragment;

    }
    public void displayUser() {
        profileNameText.setText(userGUI.getName());
        statusText.setText(userGUI.getStatus());
         new ImageLoader(profilePicture).execute(userGUI.getUrlProfilePicture());
    }
}
