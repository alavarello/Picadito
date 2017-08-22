package com.picadito.picadito.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.picadito.picadito.Activities.Displayers.NotificationContainerDisplayer;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.R;

public class NotificationsContainerActivity extends AppCompatActivity {

    private LinearLayout layout;
    private UserGUI userGUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_layout);
        layout =(LinearLayout) findViewById(R.id.scrollingFragment_mainLayoutLinearLayout);
        userGUI = (UserGUI) getIntent().getSerializableExtra("user");
        FrameLayout fragmentLayout = (FrameLayout) findViewById(R.id.scrollingFragment_frameLayout);
        fragmentLayout.removeView(findViewById(R.id.scrollingFragment_searchViewSearchView));
        NotificationContainerDisplayer notificationContainerDisplayer = new NotificationContainerDisplayer(userGUI.getNewNotifications(), userGUI.getOldNotifications(), layout, this);
        notificationContainerDisplayer.displayNotifications();

    }
}
