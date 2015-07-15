package com.mobapp.garyjulius.mylectures.Helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPictureTask extends AsyncTask<ImageView, Void, Bitmap> {
    private static final String TAG = "DownloadPictureTask";
    private ImageView _imageView = null;

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        this._imageView = imageViews[0];
        return download_Image((URL) _imageView.getTag());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            _imageView.setImageBitmap(result);
            _imageView.setAdjustViewBounds(true);
            _imageView.setMinimumHeight(150);
            _imageView.setMinimumWidth(result.getWidth() / 150);
        }
    }

    private Bitmap download_Image(URL url) {
        Bitmap bm = null;
        try {
            URL aURL = url;
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting the image from server : " + e.getMessage().toString());
        }
        return bm;
    }
}