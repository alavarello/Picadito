package com.picadito.picadito.Model;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.picadito.picadito.GUI.FriendGUI;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.GUI.NotificationGUI;
import com.picadito.picadito.GUI.UserGUI;

import java.io.Serializable;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Agustin Lavarello on 6/27/2017.
 */

public class User implements Serializable {
    private String name;
    private String userName;
    private String status;
    private transient Drawable profilePicture;
    private SortedSet<Match> matcheses = new TreeSet<Match>();
    private SortedSet<FriendGUI> friends = new TreeSet<>();
    private SortedSet<Notification> notifications = new TreeSet<Notification>();
    private PriorityQueue<Chat> chats = new PriorityQueue<Chat>();
    private URL urlProfilePicture;

    public User(String name, String userName, String status, URL urlProfilePicture) {
        this.name = name;
        this.userName = userName;
        this.status = status;
        this.urlProfilePicture = urlProfilePicture;
    }

    public User(String name, String userName, String status, URL urlProfilePicture, SortedSet<Match> matcheses, TreeSet<FriendGUI> friends, SortedSet<Notification> notifications, PriorityQueue<Chat> chats) {
        this.name = name;
        this.userName = userName;
        this.status = status;
        this.urlProfilePicture = urlProfilePicture;
        this.matcheses = matcheses;
        this.friends = friends;
        this.notifications = notifications;
        this.chats = chats;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public Drawable getProfilePicture(){
        return profilePicture;
    }

    public void setProfilePicture(Drawable profilePicture){
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName(){
        return userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SortedSet<Match> getMatcheses() {
        return matcheses;
    }

    public boolean addMatch (Match match){
        if(matcheses.contains(match)){
            return false;
        }
        matcheses.add(match);
        return true;
    }

    public boolean removeMatch (Match match){
        if(matcheses.contains(match)){
            matcheses.remove(match);
            return true;
        }
        return false;
    }

    public boolean removeMatch(String name){
        for(Match m: matcheses){
            if(m.getName().equals(name)){
                matcheses.remove(m);
                return true;
            }
        }
        return false;
    }

    public SortedSet<FriendGUI> getFriends() {
        return friends;
    }

    public boolean addFriend (FriendGUI friend){
        if(friends.contains(friend)){
            return false;
        }
        friends.add(friend);
        return true;
    }

    /*
    A notification should be add always, doesn't matter if you already have it
     */
    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public void removeNotification(Notification notification){
        notifications.remove(notification);
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

    public UserGUI getGUI(){
        SortedSet<MatchGUI> matchesGUI = new TreeSet<MatchGUI>();
        for (Match m: matcheses){
            matchesGUI.add(m.getGUI());
        }
        SortedSet<NotificationGUI> notificationsGUI = new TreeSet<NotificationGUI>();
        for (Notification n: notifications){
            notificationsGUI.add(n.getGUI());
        }
        return new UserGUI(name, userName, status,urlProfilePicture,matchesGUI, friends,notificationsGUI, chats);
    }


}
