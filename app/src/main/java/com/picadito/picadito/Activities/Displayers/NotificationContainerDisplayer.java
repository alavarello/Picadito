package com.picadito.picadito.Activities.Displayers;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picadito.picadito.Model.Notification;
import com.picadito.picadito.R;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Agustin Lavarello on 8/6/2017.
 */

public class NotificationContainerDisplayer {

    private SortedSet<Notification> newNotifications;
    private SortedSet<Notification> oldNotification;
    private LinearLayout layout;
    private AppCompatActivity notificationsContainerActivity;

    public NotificationContainerDisplayer(SortedSet<Notification> newNotifications, SortedSet<Notification> oldNotification, LinearLayout layout, AppCompatActivity notificationsContainerActivity) {
        this.newNotifications = newNotifications;
        this.oldNotification = oldNotification;
        this.layout = layout;
        this.notificationsContainerActivity = notificationsContainerActivity;
    }

    public void displayNotifications(){
        layout.removeAllViews();
        SortedSet<Notification> notifications = new TreeSet<>();
        notifications.addAll(newNotifications);
        notifications.addAll(oldNotification);
        if (notifications.isEmpty()) {
            TextView text = new TextView(notificationsContainerActivity);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setText("No tenes Notificaciones");
            layout.addView(text);
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            notificationsContainerActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            LayoutInflater inflater = (LayoutInflater) notificationsContainerActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (Notification notification : notifications) {
                View customView = inflater.inflate(R.layout.notificationsbutton_layout, null);
                LinearLayout notificationLayout = (LinearLayout) customView.findViewById(R.id.notificationsButtonLayout_mainLinearLayout);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                notificationLayout.setLayoutParams(layoutParams);
                LinearLayout verticalLayout = (LinearLayout) customView.findViewById(R.id.notificationsButtonLayout_verticalLinearLayout);
                verticalLayout.getLayoutParams().width = (int) (width*0.7);
                ImageView picture = (ImageView) customView.findViewById(R.id.notificationsButtonLayout_profilePictureImageView);
                picture.setImageDrawable(null);
                picture.getLayoutParams().width = (int) (width*0.3);
                TextView message = (TextView) customView.findViewById(R.id.notificationsButtonLayout_messageTextView);
                message.setText(notification.getShortMessage());
                message.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextView date = (TextView) customView.findViewById(R.id.notificationsButtonLayout_dateTextView);
                date.setText(notification.getDate().toString());
                date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                if(notification.wasRead()) {
                   notificationLayout.setBackgroundColor(Color.BLUE);
                }
                layout.addView(customView);
        }
    }
    }
}
