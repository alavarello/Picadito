package com.picadito.picadito.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.picadito.picadito.Model.DownLoader;
import com.picadito.picadito.Model.Notification;
import com.picadito.picadito.Model.UpLoader;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;

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
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private User user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //le saco el titulo de arriba (esto tiene que estar ates del setContentView)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logingComplete();
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


    private void logingComplete() {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        User user = new User("Agus", "agustinLavarello@hotmail.com", "ocupado", profilePicture.toString());
//        User user2 = new User("Agus", "agustinLavarello@hotmail.com", "ocupado", profilePicture.toString());
//        Calendar c = Calendar.getInstance();
//        Match match1 = new Match(user.getGUI(), new Date(c.getTimeInMillis()), "Picadito", 6,2.2);
//        TreeSet<MatchGUI> matchesLuli = new TreeSet<MatchGUI>();
//        matchesLuli.add(match1.getGUI());
//        TreeSet<Friend> friendLuli = new TreeSet<Friend>();
//        Friend friend1 = new Friend("Marcos", "MarcosLavarello","Disponible",  R.drawable.marcos, new TreeSet<MatchGUI>(),new TreeSet<Friend>());
//        Friend friend2 = new Friend("Sofia", "SofiaLavarello","Disponible", R.drawable.player_login,new TreeSet<MatchGUI>(),new TreeSet<Friend>());
//        friendLuli.add(friend1);
//        friendLuli.add(friend2);
//        Friend friend3 = new Friend("Luli", "LuliStrozza","Disponible", R.drawable.luli,matchesLuli,friendLuli);
//        user.addFriend(friend1);
//        user.addFriend(friend2);
//        user.addFriend(friend3);
//        user.addMatch(match1);
//        Match match2 = new Match(user.getGUI(), new Date(c.getTimeInMillis()- 100000000), "Picadito2", 6,2.2);
//        user.addMatch(match2);
//        Date date = new Date();
//        date.setTime(c.getTimeInMillis());
//        Notification notification1 = new MessageNotification("hola", user.getGUI(),user2.getGUI(),c.getTime());
//        user.addNotification(notification1);
//        Notification notification2 = new MatchNotification(match2.getGUI(),"hola2", c.getTime());
//        Notification notification3 = new MatchNotification(match1.getGUI(),"hola3", c.getTime());
//        notification3.read();
//        user.addNotification(notification2);
//        user.addNotification(notification3);
//        intent.putExtra("user", (Serializable) user);
//        startActivity(intent);
//        finish();
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
                    final FirebaseUser userFireBase = firebaseAuth.getCurrentUser();
                    final DatabaseReference userDataBaseReference = database.getReference();
                    DatabaseReference specificUserDataBase = userDataBaseReference.child("user").child(userFireBase.getUid());
                    specificUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null) {

                                user = DownLoader.downLoadUser(dataSnapshot);
                            } else {
                                user = new User(userFireBase.getDisplayName(), userFireBase.getUid(), "disponible", userFireBase.getPhotoUrl().toString());
                                user.addFriend("hola");
                                UpLoader.loadUser(user);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("user", (Serializable) user);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // ...
                        }
                    });
                    }
                }
        });
    }
}

