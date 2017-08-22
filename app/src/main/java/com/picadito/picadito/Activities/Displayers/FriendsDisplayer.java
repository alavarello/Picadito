package com.picadito.picadito.Activities.Displayers;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picadito.picadito.Activities.FriendMainActivity;
import com.picadito.picadito.GUI.FriendGUI;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.R;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.jar.Attributes;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class FriendsDisplayer {

    private SortedSet<FriendGUI> friends;
    private AppCompatActivity activity;
    private LinearLayout layout;

    public FriendsDisplayer(SortedSet<FriendGUI> friends, AppCompatActivity activity, LinearLayout layout) {
        this.friends = friends;
        this.activity = activity;
        this.layout = layout;

    }

    public void displayFriends() {

        layout.removeAllViews();
        if (friends.isEmpty()) {
            TextView text = new TextView(activity);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setText("No tenes nigun Amigo");
            layout.addView(text);
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (final FriendGUI friend : friends) {
                View customView = inflater.inflate(R.layout.friendsbutton_layout, null);
                LinearLayout friendLayout = (LinearLayout) customView.findViewById(R.id.friendsButtonLayout_mainLinearLayout);
                friendLayout.setClickable(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                friendLayout.setLayoutParams(layoutParams);
                LinearLayout verticalLayout = (LinearLayout) customView.findViewById(R.id.friendsButtonLayout_verticalLinearLayout);
                verticalLayout.getLayoutParams().width = (int) (width*0.7);
                ImageView picture = (ImageView) customView.findViewById(R.id.friendsButtonLayout_profilePictureImageView);
                picture.setImageDrawable(ContextCompat.getDrawable(activity.getApplicationContext(),friend.getProfilePicture()));
                picture.getLayoutParams().width = (int) (width*0.3);
                TextView name = (TextView) customView.findViewById(R.id.friendsButtonLayout_nameTextView);
                name.setText(friend.getName());
                name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextView status = (TextView) customView.findViewById(R.id.friendsButtonLayout_statusTextView);
                status.setText(friend.getStatus());
                status.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                friendLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity.getApplicationContext(), FriendMainActivity.class);
                        intent.putExtra("friend", (Serializable) friend);
                        activity.startActivity(intent);
                    }
                });
               layout.addView(customView);
            }
        }
    }
}
