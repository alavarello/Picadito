package com.picadito.picadito.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.picadito.picadito.R;

public class MatchActivity extends AppCompatActivity {

    private ImageView firstTeamCapitain;
    private ImageView secondTeamCapitain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        firstTeamCapitain = (ImageView) findViewById(R.id.creatMatch_firstTeamCapitainImageVIew);


    }
}
