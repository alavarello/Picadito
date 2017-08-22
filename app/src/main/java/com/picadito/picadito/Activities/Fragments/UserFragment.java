package com.picadito.picadito.Activities.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.picadito.picadito.Activities.CreatMatchActivity;
import com.picadito.picadito.Activities.Displayers.UserDisplayer;
import com.picadito.picadito.Activities.FriendsChatActivity;
import com.picadito.picadito.Activities.LoginActivity;
import com.picadito.picadito.Activities.MainActivity;
import com.picadito.picadito.Activities.NotificationsContainerActivity;
import com.picadito.picadito.Activities.SettingsActivity;
import com.picadito.picadito.Activities.UserMatchesActivity;
import com.picadito.picadito.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;


public class UserFragment extends Fragment {

    private ImageView profilePicture;
    private ImageView profilePictureDrawer;
    private ImageButton settingsButton;
    private ImageButton chatButton;
    private TextView profileNameText;
    private TextView profileNameTextDrawer;
    private TextView userNameDrawer;
    private TextView statusText;
    private LinearLayout creatMatchLayout;
    private LinearLayout viewMatchesLayout;
    private RelativeLayout firstRelativeLayout;
    private RelativeLayout secondRelativeLayout;
    private Button creatMatchButton;
    private Button viewMatchButton;
    private MainActivity mainActivity;
    private View view;
    private DrawerLayout drawerLayout;
    private FloatingActionButton floatingActionButton;
    private Button configurationDrawer;
    private Button acoountManagerDrawer;
    private Button logoutDrawerButton;
    public View getView(){
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        mainActivity = (MainActivity) getActivity();


        //Seteo los resources
        profilePicture = (ImageView) view.findViewById(R.id.userFragment_profilePictureImageView);
        settingsButton = (ImageButton) view.findViewById(R.id.userFragment_settingButton);
        chatButton = (ImageButton) view.findViewById(R.id.userFragment_chatButton);
        profileNameText = (TextView) view.findViewById(R.id.userFragment_profileNameTextView);
        statusText = (TextView) view.findViewById(R.id.userFragment_stateTextView);
        firstRelativeLayout = (RelativeLayout) view.findViewById(R.id.userFragment_UpperRelativeLayout);
        viewMatchButton = (Button) view.findViewById(R.id.userFragment_viewMatchesButton);
        configurationDrawer = (Button) view.findViewById(R.id.userFragmentDrawer_configurationButton);
        creatMatchButton = (Button) view.findViewById(R.id.userFragment_creatMatchButton);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.userFragment_drawerDrawer);
        secondRelativeLayout = (RelativeLayout) view.findViewById(R.id.userFragment_pictureRelativeLayout);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.userFragment_notificationFloatingActionButton);
        profilePictureDrawer = (ImageView) view.findViewById(R.id.userFragmentDrawer_profilePictureImageView);
        profileNameTextDrawer = (TextView) view.findViewById(R.id.userFragmentDrawer_nameTextView);
        userNameDrawer = (TextView) view.findViewById(R.id.userFragmentDrawer_userNameTextView);
        acoountManagerDrawer = (Button) view.findViewById(R.id.userFragment_accountManagerButton);
        logoutDrawerButton = (Button) view.findViewById(R.id.userFragment_logoutButton);


        //seteo las funciones
        viewMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getApplicationContext(), UserMatchesActivity.class);
                intent.putExtra("user", (Serializable) mainActivity.getUserGUI());
                startActivity(intent);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getApplicationContext(), FriendsChatActivity.class);
                intent.putExtra("user", (Serializable) mainActivity.getUserGUI());
                startActivity(intent);
            }
        });
        creatMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getApplicationContext(), CreatMatchActivity.class);
                intent.putExtra("user", (Serializable) mainActivity.getUserGUI());
                getActivity().startActivityForResult(intent,2);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getApplicationContext(), NotificationsContainerActivity.class);
                intent.putExtra("user", (Serializable) mainActivity.getUserGUI());
                startActivity(intent);
            }
        });
        configurationDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.getApplicationContext(), SettingsActivity.class);
                intent.putExtra("user", (Serializable) mainActivity.getUserGUI());
                getActivity().startActivityForResult(intent, 1);

            }
        });
        logoutDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(mainActivity.getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                mainActivity.finish();

            }
        });

        //Seteo las dimensiones de cada componente
        //dependiendo del tamano de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        mainActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        ViewGroup.LayoutParams firstRelativeLayoutParams = firstRelativeLayout.getLayoutParams();
        firstRelativeLayoutParams.height = (int) (height * 0.15);
        firstRelativeLayout.setLayoutParams(firstRelativeLayoutParams);
        settingsButton.getLayoutParams().width = firstRelativeLayoutParams.height;//para que los botones sean cuadrados
        chatButton.getLayoutParams().width = firstRelativeLayoutParams.height;//para que los botones sean cuadrados

       secondRelativeLayout.getLayoutParams().height = (int) (height * 0.25);

        profilePicture.getLayoutParams().width = (int) (width * 0.33);

        profileNameText.getLayoutParams().height = (int) (height * 0.10);

        statusText.getLayoutParams().height = (int) (height * 0.20);

        viewMatchButton.getLayoutParams().height = (int) (height * 0.15);

        creatMatchButton.getLayoutParams().height = (int) (height * 0.15);

        profilePictureDrawer.getLayoutParams().height = (int) (height*0.15);

        configurationDrawer.getLayoutParams().height = (int) (height*0.1);

        acoountManagerDrawer.getLayoutParams().height = (int) (height*0.1);

        //seteo lo que dicen las cosas

        displayContent();


        return view;
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);


        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    public void displayContent()  {
        UserDisplayer dislplayUserContent = new UserDisplayer(mainActivity.getUserGUI(), this);
        dislplayUserContent.displayUser();
        if(mainActivity.getUserGUI().getNewNotifications().size() == 0){
           //TODO
        }else {
            //TODO
        }
        userNameDrawer.setText(((MainActivity) getActivity()).getUserGUI().getUserName());
        profileNameTextDrawer.setText(((MainActivity) getActivity()).getUserGUI().getName());
       //TODO //profile picture


    }

}



