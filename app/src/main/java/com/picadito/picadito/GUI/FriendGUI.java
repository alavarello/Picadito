package com.picadito.picadito.GUI;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class FriendGUI implements Comparable<FriendGUI>, Serializable{

    private String Name;
    private String userName;
    private String Status;
    private int drawableTag;
    private SortedSet<MatchGUI> matcheses;
    private SortedSet<FriendGUI> friends;

    public FriendGUI(String name, String userName, String status, int drawableTag, SortedSet<MatchGUI> matcheses, SortedSet<FriendGUI> friends) {
        Name = name;
        this.userName = userName;
        Status = status;
        this.drawableTag = drawableTag;
        this.matcheses = matcheses;
        this.friends = friends;
    }

    public String getName() {
        return Name;
    }

    public String getUserName() {
        return userName;
    }

    public String getStatus() {
        return Status;
    }

    public int getProfilePicture() {
        return drawableTag;
    }

    public SortedSet<MatchGUI> getMatcheses() {
        return matcheses;
    }

    public SortedSet<FriendGUI> getFriends() {
        return friends;
    }

    @Override
    public int compareTo(FriendGUI o) {
        return getUserName().compareTo(o.getUserName());
    }
}
