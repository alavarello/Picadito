package com.picadito.picadito.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.picadito.picadito.Model.Chat;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;

import java.util.PriorityQueue;

public class FriendsChatActivity extends AppCompatActivity {

    PriorityQueue<Chat> chats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_chat);
        User user = (User)  getIntent().getSerializableExtra("user");
//        chats = user.getChats();
//        FriendsChatDisplayer friendsChatDisplayer = new FriendsChatDisplayer(this, user.getChats());
//        friendsChatDisplayer.displayFriendsChats();
        //TODO


    }
}
