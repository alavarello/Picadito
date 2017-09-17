package com.picadito.picadito.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class ConfirmPlayerToMatchNotification implements Notification {

    private boolean confirmation;
    private boolean wasRead;
    private Date date;

    public ConfirmPlayerToMatchNotification(boolean confirmation, boolean wasRead, Date date) {
        this.confirmation = confirmation;
        this.wasRead = wasRead;
        Calendar c = Calendar.getInstance();
        if(c.getTime().compareTo(date)>0){
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    @Override
    public boolean wasRead() {
        return false;
    }

    @Override
    public void read() {

    }

    @Override
    public Date getDate() {
        return date;
    }


    @Override
    public String getShortMessage() {
        return "Confirmaron Partido";
    }

    @Override
    public int compareTo(Notification o) {
        return date.compareTo(o.getDate());
    }
}
