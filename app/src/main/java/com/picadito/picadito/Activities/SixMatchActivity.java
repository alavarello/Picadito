package com.picadito.picadito.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.picadito.picadito.R;

public class SixMatchActivity extends AppCompatActivity {

    private ImageView firstTeamCapitain;
    private ImageView secondTeamCapitain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_six);

        firstTeamCapitain = (ImageView) findViewById(R.id.creatMatch_firstTeamCapitainImageVIew);


    }
}
