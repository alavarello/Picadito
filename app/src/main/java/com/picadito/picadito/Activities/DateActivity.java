package com.picadito.picadito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.picadito.picadito.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DateActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button accept_btn;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarView = (CalendarView) findViewById(R.id.dateActivity_calendarCalendarView);
        accept_btn = (Button) findViewById(R.id.dateAcitivity_acceptButton);
        //Seteo las dimensiones de cada componente
        //dependiendo del tamano de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width =  metrics.widthPixels;
        int height = metrics.heightPixels;

        calendarView.getLayoutParams().height = (int) (height*0.60);
        accept_btn.getLayoutParams().height = (int) (height*0.1);


        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = new Date(year,month,dayOfMonth);
            }
        });
        //seteo el calendario
        Calendar c = Calendar.getInstance();
        calendarView.setMinDate(c.getTimeInMillis());

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date", (Serializable) date);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
