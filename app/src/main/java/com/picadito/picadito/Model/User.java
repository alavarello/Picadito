package com.picadito.picadito.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Agustin Lavarello on 6/27/2017.
 */

public class User implements Serializable {
    private String userName;
    private String userID;
    private String status;
    private String urlProfilePicture;

    private List<String> matcheses = new ArrayList<>();
    private List<String> friends = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private List<String> chats = new ArrayList<>();


    public User(){}

    public User(String name, String userName, String status, String urlProfilePicture) {
        this.userName = name;
        this.userID = userName;
        this.status = status;
        this.urlProfilePicture = urlProfilePicture;
    }

    public void setMatcheses(List<String> matcheses) {
        this.matcheses = matcheses;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<String> getChats() {
        return chats;
    }

    public void setChats(List<String> chats) {
        this.chats = chats;
    }

    public String getUrlProfilePicture() {
        return urlProfilePicture;
    }

    public void setUrlProfilePicture(String urlProfilePicture) {
        this.urlProfilePicture = urlProfilePicture;
    }

    public User(String name, String userName, String status, String urlProfilePicture, List<String> matcheses, List<String> friends, List<Notification> notifications, List<String> chats) {
        this.userName = name;
        this.userID = userName;
        this.status = status;
        this.urlProfilePicture = urlProfilePicture;
        this.matcheses = matcheses;
        this.friends = friends;
        this.notifications = notifications;
        this.chats = chats;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }


    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getUserID(){
        return userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMatcheses() {
        return matcheses;
    }

    public boolean addMatch (String match){
        if(matcheses.contains(match)){
            return false;
        }
        matcheses.add(match);
        return true;
    }


    public boolean removeMatch(String match){
        for(String m: matcheses){
            if(m.equals(match)){
                matcheses.remove(m);
                return true;
            }
        }
        return false;
    }

    public List<String> getFriends() {
        return friends;
    }

    public boolean addFriend (String friend){
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

        return userID.equals(user.getUserID());

    }

    @Override
    public int hashCode() {
        return userID.hashCode();
    }

    @Override
    public String toString() {
        return userName;

    }

}
