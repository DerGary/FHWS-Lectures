package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

//import com.owlike.genson.Genson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobapp.garyjulius.mylectures.Helper.DateTimeSerializer;
import com.mobapp.garyjulius.mylectures.R;

import org.joda.time.DateTime;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class PutAsyncTask<T> extends AsyncTask<Object,Void,String> {
    final static String TAG = "PutAsyncTask";
    Activity context;
    public PutAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(context.getString(R.string.server_basepath));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(context.getString(R.string.http_put));
            urlConnection.setRequestProperty(context.getString(R.string.content_type_title),context.getString(R.string.type_json));
            urlConnection.setConnectTimeout(10*1000);

            Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeSerializer()).create();
            String jsonOfPerson = gson.toJson((T) params[0]);

            urlConnection.getOutputStream().write(jsonOfPerson.getBytes());
            Log.d("URL", "" + urlConnection.getHeaderField("Location"));
            Log.d("Code", "" + urlConnection.getResponseCode());
            return context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
            } catch (Exception ex) { Log.e(TAG, "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
            }
        return context.getString(R.string.asynctask_returnerror);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //TODO
    }
}
