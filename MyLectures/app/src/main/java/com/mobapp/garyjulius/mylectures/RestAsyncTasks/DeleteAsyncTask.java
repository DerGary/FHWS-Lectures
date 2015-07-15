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
public class DeleteAsyncTask<T> extends AsyncTask<Object, Void, String> {
    final static String TAG = "DeleteAsyncTask";
    private Activity _context;

    public DeleteAsyncTask(Activity context) {
        this._context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(_context.getString(R.string.server_basepath));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_context.getString(R.string.http_delete));
            urlConnection.setRequestProperty(_context.getString(R.string.content_type_title), _context.getString(R.string.type_json));

            Gson gson = ExtendedGson.getInstance();
            String jsonOfPerson = gson.toJson((T) params[0]);

            urlConnection.getOutputStream().write(jsonOfPerson.getBytes());
            return _context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return _context.getString(R.string.asynctask_returnerror);
    }
}
