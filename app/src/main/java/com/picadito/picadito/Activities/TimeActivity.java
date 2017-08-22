package com.picadito.picadito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.picadito.picadito.R;

public class TimeActivity extends AppCompatActivity {

    private TimePicker clock;
    private Button accept_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        //Seteo las dimensiones de cada componente
        //dependiendo del tamano de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width =  metrics.widthPixels;
        int height = metrics.heightPixels;

        accept_btn = (Button) findViewById(R.id.timeActivity_acceptButton);
        clock = (TimePicker) findViewById(R.id.timeActivity_timePickerTimePicker);
        clock.getLayoutParams().height = (int) (height*0.6);
        accept_btn.getLayoutParams().height = (int) (height*0.1);

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("hour",clock.getCurrentHour());
                intent.putExtra("minutes",clock.getCurrentMinute());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
