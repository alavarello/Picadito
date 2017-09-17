package com.picadito.picadito.Model;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by Agustin Lavarello on 8/5/2017.
 */

public class Friend implements Comparable<Friend>, Serializable{

    private String Name;
    private String userName;
    private String Status;
    private int drawableTag;
    private SortedSet<Match> matcheses;
    private List<String> friends;

    public Friend(String name, String userName, String status, int drawableTag, SortedSet<Match> matcheses, List<String> friends) {
        Name = name;
        this.userName = userName;
        Status = status;
        this.drawableTag = drawableTag;
        this.matcheses = matcheses;
        this.friends = friends;
    }

    public Friend(){}

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

    public SortedSet<Match> getMatcheses() {
        return matcheses;
    }

    public List<String> getFriends() {
        return friends;
    }

    @Override
    public int compareTo(Friend o) {
        return getUserName().compareTo(o.getUserName());
    }
}
