package com.picadito.picadito.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.R;

import java.io.Serializable;
import java.util.Date;

public class CreatMatchActivity extends AppCompatActivity {

    private EditText nameOfMatch_et;
    private EditText priceOfTheMatch_et;
    private LinearLayout buttonsLinearLayout;
    private Button date_btn;
    private Button time_btn;
    private Button place_btn;
    private Button creatMatch_btn;
    private Button inviteFriends_btn;
    private Date date;
    private UserGUI mainUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_match);
        mainUser = (UserGUI)getIntent().getSerializableExtra("user");
        //Seteo los resources
        nameOfMatch_et = (EditText) findViewById(R.id.creatActivity_nameOfMatchEditText);
        priceOfTheMatch_et = (EditText) findViewById(R.id.creatMAtchActivity_priceOfTheMatchEditText);
        buttonsLinearLayout = (LinearLayout) findViewById(R.id.creatMatchActivity_buttonContainerLinearLayout);
        creatMatch_btn = (Button) findViewById(R.id.creatMatchActivity_creatMatchButton);
        inviteFriends_btn = (Button) findViewById(R.id.creatMatchActivity_inviteFriendsButton);
        date_btn = (Button) findViewById(R.id.creatMatchActivity_pickDateButton);
        time_btn = (Button) findViewById(R.id.creatMatchActivity_pickTimeButton);
        place_btn = (Button) findViewById(R.id.creatMatchActivity_pickPlaceButton);


        //Seteo las dimensiones de cada componente
        //dependiendo del tamano de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width =  metrics.widthPixels;
        int height = metrics.heightPixels;

        nameOfMatch_et.getLayoutParams().height = (int)(height*0.10);
        priceOfTheMatch_et.getLayoutParams().height = (int)(height*0.10);
        date_btn.getLayoutParams().height = (int)(height*0.1);
        time_btn.getLayoutParams().height = (int)(height*0.1);
        place_btn.getLayoutParams().height = (int)(height*0.15);
        buttonsLinearLayout.getLayoutParams().height = (int)(height*0.10);
        inviteFriends_btn.getLayoutParams().width = (int) (width*0.5);
        creatMatch_btn.getLayoutParams().width = (int) (width*0.5);


        //seteo los botones
        date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DateActivity.class);
                startActivityForResult(intent,1);
            }
        });
       time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
                startActivityForResult(intent,2);
            }
        });

        place_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        creatMatch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean errorOccurs = false;
                if(nameOfMatch_et.getText().toString().equals("")){
                    nameOfMatch_et.setError("Falta el nombre del partido");
                    nameOfMatch_et.requestFocus();
                    errorOccurs = true;
                }
                if(priceOfTheMatch_et.getText().toString().equals("")){
                    priceOfTheMatch_et.setError("Falta el precio");
                    priceOfTheMatch_et.requestFocus();
                    errorOccurs = true;
                }
                if(date == null){
                    Toast.makeText(getApplicationContext(), "Elegi un dia", Toast.LENGTH_LONG).show();
                    errorOccurs = true;
                }
                if(!errorOccurs){
                    Match match = new Match(mainUser, date,nameOfMatch_et.getText().toString(),6,Double.parseDouble(priceOfTheMatch_et.getText().toString()));
                    Intent intent = new Intent();
                    intent.putExtra("match", (Serializable) match);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            this.date = (Date)data.getSerializableExtra("date");
        }
        if(requestCode == 2){
            date.setHours(data.getIntExtra("hour",0));
            date.setMinutes(data.getIntExtra("minutes",0));
        }




    }
}
