package com.picadito.picadito.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by agust on 8/23/2017.
 */

public class ImageLoader extends AsyncTask<URL, Void, Drawable> {

    ImageView bmImage;
    public ImageLoader(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Drawable doInBackground(URL... url) {
        try {
            InputStream is = (InputStream) url[0].getContent();
            Drawable d = Drawable.createFromStream(is, "drawable");
            d.setBounds(0, 0, 50, 50);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    protected void onPostExecute(Drawable result) {
        bmImage.setImageDrawable(result);
    }
}
