package com.picadito.picadito.GUI;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public interface NotificationGUI extends Comparable<NotificationGUI>, Serializable{
    /*
    String that display a short message of the notification
    This is to show it in the list of notificatios
    */
    public String getShortMessage();

    public boolean wasRead();

    public Date getDate();


    //public void display();

}
