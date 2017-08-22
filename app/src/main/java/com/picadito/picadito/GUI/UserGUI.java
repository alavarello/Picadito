package com.picadito.picadito.GUI;

import android.graphics.drawable.Drawable;

import com.picadito.picadito.Model.Chat;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.Model.Notification;
import com.picadito.picadito.Model.User;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Agustin Lavarello on 6/27/2017.
 */
public class UserGUI implements Serializable{

    private String Name;
    private String userName;
    private String Status;
    private transient Drawable profilePicture;
    private URL urlProfilePicture;
    private SortedSet<MatchGUI> matcheses = new TreeSet<>();
    private SortedSet<FriendGUI> friends = new TreeSet<>();
    private SortedSet<NotificationGUI> oldNotifications = new TreeSet<>();
    private SortedSet<NotificationGUI> newNotifications = new TreeSet<>();
    private PriorityQueue<Chat> chats = new PriorityQueue<>();


    public UserGUI(String name, String userName, String status, URL urlProfilePicture, SortedSet<MatchGUI> matcheses, SortedSet<FriendGUI> friends, SortedSet<NotificationGUI> notifications, PriorityQueue<Chat> chats) {
        Name = name;
        this.userName = userName;
        Status = status;
        this.urlProfilePicture = urlProfilePicture;
        this.matcheses = matcheses;
        this.friends = friends;
        this.chats = chats;
        for(NotificationGUI n: notifications){
            if(n.wasRead()){
                oldNotifications.add(n);
            }
            else{
                newNotifications.add(n);
            }
        }
    }

    public URL getUrlProfilePicture(){return urlProfilePicture;}

    public Drawable getProfilePicture(){
        return profilePicture;
    }

    public String getName() {
        return Name;
    }

    public String getUserName(){
        return userName;
    }

    public String getStatus() {
        return Status;
    }

    public SortedSet<MatchGUI> getMatcheses() {
        return matcheses;
    }

    public SortedSet<FriendGUI> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName.equals(user.getUserName());

    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public String toString() {
        return userName;

    }

    public PriorityQueue<Chat> getChats(){
        return chats;
    }

    public SortedSet<NotificationGUI> getOldNotifications() {
        return oldNotifications;
    }

    public SortedSet<NotificationGUI> getNewNotifications() {
        return newNotifications;
    }
}

