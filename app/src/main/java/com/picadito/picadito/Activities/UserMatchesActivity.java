package com.picadito.picadito.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.picadito.picadito.Activities.Displayers.MatchesDisplayable;
import com.picadito.picadito.Activities.Displayers.MatchesDisplayer;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.R;

import java.util.SortedSet;

/**
 * Created by Agustin Lavarello on 7/18/2017.
 */

public class UserMatchesActivity extends AppCompatActivity implements MatchesDisplayable {

    private SortedSet<MatchGUI> matches;
    private LinearLayout layout;
    private Context context;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserGUI user = (UserGUI)  getIntent().getSerializableExtra("user");
        matches =  user.getMatcheses();
        setContentView(R.layout.scrolling_layout);
        layout = (LinearLayout) findViewById(R.id.scrollingFragment_mainLayoutLinearLayout);
        FrameLayout fragmentLayout = (FrameLayout) findViewById(R.id.scrollingFragment_frameLayout);
        fragmentLayout.removeView(findViewById(R.id.scrollingFragment_searchViewSearchView));
        MatchesDisplayer matchesDisplayer = new MatchesDisplayer(this, layout);
        matchesDisplayer.displayMatches();

    }

    public SortedSet<MatchGUI> getMatches(){return matches;}





}
