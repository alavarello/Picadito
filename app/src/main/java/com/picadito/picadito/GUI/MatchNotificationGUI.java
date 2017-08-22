package com.picadito.picadito.GUI;

import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MatchNotificationGUI implements NotificationGUI {

    private MatchGUI match;
    private String message;
    private boolean wasRead;
    private Date date;

    public MatchNotificationGUI(MatchGUI match, String message, boolean wasRead, Date date) {
        this.match = match;
        this.message = message;
        this.wasRead = wasRead;
        this.date = date;
    }

    @Override
    public String getShortMessage() {
        if(message.length()>10){
            return message.substring(0,10);
        }
        return message;
    }

    @Override
    public boolean wasRead() {
        return wasRead;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(NotificationGUI o) {
        if(date.equals(o.getDate()) && message.equals(o.getShortMessage())){
            return 0;
        };
        if(date.equals(o.getDate())){
            return 1;
        }
        return date.compareTo(o.getDate());
    }
}
