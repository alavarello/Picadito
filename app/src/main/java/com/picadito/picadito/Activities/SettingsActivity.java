package com.picadito.picadito.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.picadito.picadito.GUI.UserGUI;
import com.picadito.picadito.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by agust on 3/2/2017.
 */

public class SettingsActivity extends Activity {

    private static final int PICK_PHOTO_FOR_AVATAR = 99 ;
    EditText userName;
    EditText userStatus;
    Button saveChanges;
    Button pickPorfilePictue;
    UserGUI user;
    Bitmap newProfilePicture;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //le saco el titulo de arriba (esto tiene que estar ates del setContentView)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_configuration);
        user = (UserGUI) getIntent().getSerializableExtra("user");


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
                    if (newProfilePicture != null) {
                        createImageFromBitmap(newProfilePicture);
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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
               InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                newProfilePicture = BitmapFactory.decodeStream(bufferedInputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


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
