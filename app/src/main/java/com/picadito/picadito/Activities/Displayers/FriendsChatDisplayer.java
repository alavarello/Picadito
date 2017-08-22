package com.picadito.picadito.Activities.Displayers;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picadito.picadito.Activities.FriendsChatActivity;
import com.picadito.picadito.Model.Chat;
import com.picadito.picadito.R;

import java.util.PriorityQueue;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class FriendsChatDisplayer {

    private FriendsChatActivity friendsChatActivity;
    private LinearLayout friendsChatLayout;
    private PriorityQueue<Chat> chats;

    public FriendsChatDisplayer(FriendsChatActivity friendsChatActivity, PriorityQueue<Chat> chats) {
        this.friendsChatActivity = friendsChatActivity;
        this.friendsChatLayout = (LinearLayout) friendsChatActivity.findViewById(R.id.userFriendChats_LayoutLinearLayout);
        this.chats = chats;
    }

    public void displayFriendsChats() {

        friendsChatLayout.removeAllViews();
        if (chats.isEmpty()) {
            TextView text = new TextView(friendsChatActivity);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setText("No tenes niguna conversacion");
            friendsChatLayout.addView(text);
        } else {
            for (Chat chat : chats) {
                Button btnTag = new Button(friendsChatActivity);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                btnTag.setText("chat");
                btnTag.setId(chats.hashCode());
                friendsChatLayout.addView(btnTag);
            }
        }
    }
}
