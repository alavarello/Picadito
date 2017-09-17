package com.picadito.picadito.Activities.Displayers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.picadito.picadito.Activities.EightMatchActivity;
import com.picadito.picadito.Activities.SixMatchActivity;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.R;

import java.io.Serializable;
import java.util.SortedSet;

/**
 * Created by Agustin Lavarello on 7/25/2017.
 */

public class MatchesDisplayer {
    Activity matchesActivity;
    LinearLayout matchesLayout;
    SortedSet<Match> matches;

    public MatchesDisplayer(Activity matchesActivity, LinearLayout layout) {
        this.matchesActivity =  matchesActivity;
        this.matchesLayout = layout;
        this.matches = ((MatchesDisplayable) matchesActivity).getMatches();
    }

    public void displayMatches() {

        matchesLayout.removeAllViews();
        if (matches.isEmpty()) {
            TextView text = new TextView(matchesActivity);
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setText("No hay partidos para mostrar");
            matchesLayout.addView(text);
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            matchesActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            LayoutInflater inflater = (LayoutInflater) matchesActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (final Match match : matches) {
                View customView = inflater.inflate(R.layout.matchesbutton_layout, null);
                LinearLayout matchLayout = (LinearLayout) customView.findViewById(R.id.matchesButtonLayout_mainLinearLayout);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                matchLayout.setLayoutParams(layoutParams);
                TextView name = (TextView) customView.findViewById(R.id.matchesButtonLayout_nameTextView);
                name.setText(match.getName());
                name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                name.getLayoutParams().height = (int) (height*0.1);
                TextView date = (TextView) customView.findViewById(R.id.matchesButtonLayout_dateTextView);
                date.setText(match.getDate().toString());
                date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                matchesLayout.addView(matchLayout);
                matchLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(match.getNumberOfPlayers() == 6){
                            Intent intent = new Intent(matchesActivity.getApplicationContext(), SixMatchActivity.class);
                            intent.putExtra("match", (Serializable) ((MatchesDisplayable) matchesActivity).getMatches());
                            matchesActivity.startActivity(intent);
                        }
                        if(match.getNumberOfPlayers() == 9){
                            Intent intent = new Intent(matchesActivity.getApplicationContext(), EightMatchActivity.class);
                            intent.putExtra("match", (Serializable) ((MatchesDisplayable) matchesActivity).getMatches());
                            matchesActivity.startActivity(intent);
                        }

                    }
                });
            }
        }
    }
}


