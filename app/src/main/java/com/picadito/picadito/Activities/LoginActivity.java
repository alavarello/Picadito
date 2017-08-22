package com.picadito.picadito.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.picadito.picadito.GUI.FriendGUI;
import com.picadito.picadito.GUI.MatchGUI;
import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.Model.Match;
import com.picadito.picadito.Model.MatchNotification;
import com.picadito.picadito.Model.MessageNotification;
import com.picadito.picadito.Model.Notification;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private EditText email;
    private EditText password;
    private Button submit;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private String firstName,lastName, sEmail;
    private URL profilePicture;
    private String userId;
    private String TAG = "LoginActivity";
    private ProfileTracker mProfileTracker;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fireBaseStartAndVerification();

        //le saco el titulo de arriba (esto tiene que estar ates del setContentView)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginattempt();
            }
        });

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "user_birthday","user_posts");

        // Callback registration
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handlerFacebookAccesToken(loginResult.getAccessToken());
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        try {
//                            userId = object.getString("id");
//                            profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
//                            if(object.has("first_name"))
//                                firstName = object.getString("first_name");
//                            if(object.has("last_name"))
//                                lastName = object.getString("last_name");
//                            if (object.has("email"))
//                                sEmail = object.getString("email");
//
//                            User user = new User(firstName + " " + lastName , userId, "ocupado", profilePicture);
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            intent.putExtra("user", (Serializable) user);
//                            startActivity(intent);
//                            finish();
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                //Here we put the requested fields to be returned from the JSONObject
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id, first_name, last_name, email");
//                request.setParameters(parameters);
//                request.executeAsync();



            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "cancel login", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Error login", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void fireBaseStartAndVerification() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    user = new User("Agus", "idede", "ocupado", null);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                    finish();
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    private void loginattempt() {
        String emailSubmit = email.getText().toString();
        String passwordSubmit = password.getText().toString();

        if (validateEmail(emailSubmit) && validatePassword(passwordSubmit)){
            logingComplete();
        }
    }

    private void logingComplete() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        User user = new User("Agus", "agustinLavarello@hotmail.com", "ocupado", profilePicture);
        User user2 = new User("Agus", "agustinLavarello@hotmail.com", "ocupado", profilePicture);
        Calendar c = Calendar.getInstance();
        Match match1 = new Match(user.getGUI(), new Date(c.getTimeInMillis()), "Picadito", 6,2.2);
        TreeSet<MatchGUI> matchesLuli = new TreeSet<MatchGUI>();
        matchesLuli.add(match1.getGUI());
        TreeSet<FriendGUI> friendLuli = new TreeSet<FriendGUI>();
        FriendGUI friend1 = new FriendGUI("Marcos", "MarcosLavarello","Disponible",  R.drawable.marcos, new TreeSet<MatchGUI>(),new TreeSet<FriendGUI>());
        FriendGUI friend2 = new FriendGUI("Sofia", "SofiaLavarello","Disponible", R.drawable.player_login,new TreeSet<MatchGUI>(),new TreeSet<FriendGUI>());
        friendLuli.add(friend1);
        friendLuli.add(friend2);
        FriendGUI friend3 = new FriendGUI("Luli", "LuliStrozza","Disponible", R.drawable.luli,matchesLuli,friendLuli);
        user.addFriend(friend1);
        user.addFriend(friend2);
        user.addFriend(friend3);
        user.addMatch(match1);
        Match match2 = new Match(user.getGUI(), new Date(c.getTimeInMillis()- 100000000), "Picadito2", 6,2.2);
        user.addMatch(match2);
        Date date = new Date();
        date.setTime(c.getTimeInMillis());
        Notification notification1 = new MessageNotification("hola", user.getGUI(),user2.getGUI(),c.getTime());
        user.addNotification(notification1);
        Notification notification2 = new MatchNotification(match2.getGUI(),"hola2", c.getTime());
        Notification notification3 = new MatchNotification(match1.getGUI(),"hola3", c.getTime());
        notification3.read();
        user.addNotification(notification2);
        user.addNotification(notification3);
        intent.putExtra("user", (Serializable) user);
        startActivity(intent);
        finish();
    }

    private boolean validateEmail(String emailSubmit) {
        if(emailSubmit.equals("Agus")){
            return true;
        }
        email.setError("wrong email");
        email.requestFocus();
        return false;
    }

    private  boolean validatePassword(String passwordSubmit) {
        if(passwordSubmit.equals("itba")){
            return true;
        }
        password.setError("wrong passwor");
        password.requestFocus();
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void handlerFacebookAccesToken(AccessToken accessToken){
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser userFireBase = firebaseAuth.getCurrentUser();
                    user = new User(userFireBase.getDisplayName() , "hola", "ocupado", profilePicture);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
        checkGooglePlayServices();

    }

    private void checkGooglePlayServices() {
        GoogleApiAvailability googleApi = GoogleApiAvailability.getInstance();
        int status = googleApi.isGooglePlayServicesAvailable(getApplicationContext());
        if(status != ConnectionResult.SUCCESS){
            googleApi.getErrorDialog(this,status, 1);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}

