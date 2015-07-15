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
public class PostAsyncTask<T> extends AsyncTask<Object, Void, String> {
    static final String TAG = "PostAsyncTask";
    private Activity _context;

    public PostAsyncTask(Activity context) {
        this._context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(_context.getString(R.string.server_basepath) + "events");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_context.getString(R.string.http_post));
            urlConnection.setRequestProperty(_context.getString(R.string.content_type_title), _context.getString(R.string.type_json));
            urlConnection.setConnectTimeout(10 * 1000);

            Gson gson = ExtendedGson.getInstance();
            String jsonOfPerson = gson.toJson((T) params[0]);

            urlConnection.getOutputStream().write(jsonOfPerson.getBytes());
            return _context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.toString());
        } finally {
            urlConnection.disconnect();
        }
        return _context.getString(R.string.asynctask_returnerror);
    }
}
