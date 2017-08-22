package com.picadito.picadito.GUI;

import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class MessageNotificationGUI implements NotificationGUI {

    private String message;
    private UserGUI from;
    private UserGUI to;
    private boolean wasRead;
    private Date date;

    public MessageNotificationGUI(String message, UserGUI from, UserGUI to, boolean wasRead, Date date) {
        this.message = message;
        this.from = from;
        this.to = to;
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

    public Date getDate(){return date;}

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
