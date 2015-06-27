package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

//import com.owlike.genson.Genson;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class DeleteAsyncTask<T> extends AsyncTask<Object,Void,String> {
    final static String TAG = "DeleteAsyncTask";
    Activity context;
    public DeleteAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://staging.applab.fhws.de:8080/fhwslectures/api/");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setRequestProperty("Content-Type","application/json");
            Gson Gson = new Gson();
            String jsonOfPerson = Gson.toJson((T) params[0]);

            urlConnection.getOutputStream().write(jsonOfPerson.getBytes());
            Log.d("URL", "" + urlConnection.getHeaderField("Location"));
            Log.d("Code", "" + urlConnection.getResponseCode());
            return "Code: " + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
        } catch (Exception ex) { Log.e(TAG, "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
        }
        return "Error";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //TODO
    }


}
