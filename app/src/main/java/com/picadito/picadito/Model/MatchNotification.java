package com.picadito.picadito.Model;

import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.GUI.MatchNotificationGUI;
import com.picadito.picadito.GUI.NotificationGUI;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MatchNotification implements Notification {

    private MatchGUI match;
    private String message;
    private boolean wasRead;
    private Date date;

    public MatchNotification(MatchGUI match, String message, Date date) {
        this.match = match;
        this.message = message;
        this.wasRead = false;
        Calendar c = Calendar.getInstance();
//        if(c.getTime().compareTo(date)>0){
//            throw new IllegalArgumentException();
//        }
        this.date = date;
    }

    public MatchGUI getMatch() {
        return match;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean wasRead() {
        return wasRead;
    }

    @Override
    public void read() {
        wasRead = true;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public NotificationGUI getGUI() {
        return new MatchNotificationGUI(match,message,wasRead,date);
    }

    @Override
    public String getShortMessage() {
        if(message.length()>10){
            return message.substring(0,10);
        }
        return message;
    }

    @Override
    public int compareTo(Notification o) {
        if(date.equals(o.getDate()) && message.equals(o.getShortMessage())){
            return 0;
        };
        if(date.equals(o.getDate())){
            return 1;
        }
        return date.compareTo(o.getDate());
    }
}
