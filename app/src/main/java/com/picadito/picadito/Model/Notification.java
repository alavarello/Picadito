package com.picadito.picadito.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Agustin Lavarello on 6/28/2017.
 */

public interface Notification extends Comparable<Notification> , Serializable{

    public boolean wasRead();

    public void read();

    public Date getDate();

    public String getShortMessage();

}
