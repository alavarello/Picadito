package com.picadito.picadito.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.picadito.picadito.Model.DownLoader;
import com.picadito.picadito.Model.UpLoader;
import com.picadito.picadito.Model.User;
import com.picadito.picadito.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by agust on 3/2/2017.
 */

public class SettingsActivity extends Activity {

    private static final int PICK_PHOTO_FOR_AVATAR = 99 ;
    EditText userName;
    EditText userStatus;
    Button saveChanges;
    Button pickPorfilePictue;
    User user;
    Uri newProfilePicture;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //le saco el titulo de arriba (esto tiene que estar ates del setContentView)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_configuration);
        user = (User) getIntent().getSerializableExtra("user");


        userName = (EditText) findViewById(R.id.configurationActivity_nameEditText);
        userStatus= (EditText) findViewById(R.id.configurationActivity_statusEditText);
        userName.setText(user.getName());
        userStatus.setText(user.getStatus());


        saveChanges = (Button) findViewById(R.id.configurationActivity_saveDataButton);
        pickPorfilePictue = (Button) findViewById(R.id.configurationActivity_pickProfilePictureButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName.getText().toString().equals("")){
                    Toast toast = Toast.makeText(SettingsActivity.this.getApplicationContext(), "El nombre de usuario no puede estar vacio",
                            Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("newUserName", userName.getText().toString());
                    intent.putExtra("newUserStatus", userStatus.getText().toString());
                    intent.putExtra("newProfilePicture", true);
                    if (newProfilePicture != null) {
                        UpLoader.loadUserProfilePictureStorage(user.getUserID(), newProfilePicture);
                        UpLoader.loadUserProfilePicture(user.getUserID(), newProfilePicture.toString());
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

        pickPorfilePictue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });


    }

    public void pickImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            newProfilePicture = data.getData();

        }
    }

    public String createImageFromBitmap(Bitmap bitmap) {
        String fileName = "myImage";//no .png or .jpg needed
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
        return fileName;
   }

}
