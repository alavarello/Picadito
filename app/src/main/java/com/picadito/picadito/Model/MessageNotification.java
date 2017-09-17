package com.picadito.picadito.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MessageNotification implements Notification {

    private String message;
    private User from;
    private User to;
    private boolean wasRead;
    private Date date;

    public MessageNotification(String message, User from, User to, Date date) {
        this.message = message;
        this.from = from;
        this.to = to;
        this.wasRead = false;
        Calendar c = Calendar.getInstance();
//        if(c.getTime().compareTo(date)>0){
//            throw new IllegalArgumentException();
//        }
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    @Override
    public boolean wasRead() {
        return wasRead;
    }

    public void read(){
        wasRead = true;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public String getShortMessage(){
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
