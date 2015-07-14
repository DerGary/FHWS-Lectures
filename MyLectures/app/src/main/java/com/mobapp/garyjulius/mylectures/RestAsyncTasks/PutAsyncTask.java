package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mobapp.garyjulius.mylectures.Helper.ExtendedGson;
import com.mobapp.garyjulius.mylectures.R;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class PutAsyncTask<T> extends AsyncTask<Object,Void,String> {
    final static String TAG = "PutAsyncTask";
    private Activity _context;

    public PutAsyncTask(Activity context)
    {
        this._context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(_context.getString(R.string.server_basepath));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_context.getString(R.string.http_put));
            urlConnection.setRequestProperty(_context.getString(R.string.content_type_title), _context.getString(R.string.type_json));
            urlConnection.setConnectTimeout(10*1000);

            Gson gson = ExtendedGson.getInstance();
            String jsonOfPerson = gson.toJson((T) params[0]);

            urlConnection.getOutputStream().write(jsonOfPerson.getBytes());
            Log.d("URL", "" + urlConnection.getHeaderField("Location"));
            Log.d("Code", "" + urlConnection.getResponseCode());
            return _context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return _context.getString(R.string.asynctask_returnerror);
    }

}
