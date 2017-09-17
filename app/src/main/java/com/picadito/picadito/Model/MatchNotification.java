package com.picadito.picadito.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MatchNotification implements Notification {

    private Match match;
    private String message;
    private boolean wasRead;
    private Date date;

    public MatchNotification(Match match, String message, Date date) {
        this.match = match;
        this.message = message;
        this.wasRead = false;
        Calendar c = Calendar.getInstance();
//        if(c.getTime().compareTo(date)>0){
//            throw new IllegalArgumentException();
//        }
        this.date = date;
    }

    public Match getMatch() {
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
