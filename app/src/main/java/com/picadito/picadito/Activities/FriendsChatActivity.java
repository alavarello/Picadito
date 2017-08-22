package com.picadito.picadito.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.picadito.picadito.Activities.Displayers.FriendsChatDisplayer;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.Model.Chat;
import com.picadito.picadito.R;

import java.util.PriorityQueue;

public class FriendsChatActivity extends AppCompatActivity {

    PriorityQueue<Chat> chats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_chat);
        UserGUI user = (UserGUI)  getIntent().getSerializableExtra("user");
        chats = user.getChats();
        FriendsChatDisplayer friendsChatDisplayer = new FriendsChatDisplayer(this, user.getChats());
        friendsChatDisplayer.displayFriendsChats();


    }
}
