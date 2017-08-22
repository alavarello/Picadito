package com.picadito.picadito.GUI;

import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public class ConfirmPlayerToMAtchNotificationGUI implements NotificationGUI {

    private boolean confirmation;
    private boolean wasRead;
    private Date date;

    public ConfirmPlayerToMAtchNotificationGUI(boolean confirmation, boolean wasRead, Date date) {
        this.confirmation = confirmation;
        this.wasRead = wasRead;
        this.date = date;
    }

    @Override
    public String getShortMessage() {
        return "accepted confirmation";
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
        return date.compareTo(o.getDate());
    }
}
