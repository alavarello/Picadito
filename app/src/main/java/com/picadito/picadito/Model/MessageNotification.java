package com.picadito.picadito.Model;

import com.picadito.picadito.GUI.MatchNotificationGUI;
import com.picadito.picadito.GUI.MessageNotificationGUI;
import com.picadito.picadito.GUI.NotificationGUI;
import com.picadito.picadito.GUI.UserGUI;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MessageNotification implements Notification {

    private String message;
    private UserGUI from;
    private UserGUI to;
    private boolean wasRead;
    private Date date;

    public MessageNotification(String message, UserGUI from, UserGUI to, Date date) {
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

    public UserGUI getFrom() {
        return from;
    }

    public UserGUI getTo() {
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

    public NotificationGUI getGUI(){
        return new MessageNotificationGUI(message,from,to,wasRead,date);
    }


}
