package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Julius on 25.06.2015.
 */
public class GetListAsyncTask<T> extends AsyncTask<String,Void,ArrayList<T>> {
    Activity context;
    public GetListAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected ArrayList<T> doInBackground(String... ext) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://staging.applab.fhws.de:8080/fhwslectures/api/" + ext[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("Content-Type","application/json");
            Genson genson = new Genson();

            ArrayList<T> objectList = genson.deserialize(urlConnection.getInputStream(), new GenericType<ArrayList<T>>(){});

            Log.d("URL", "" + urlConnection.getHeaderField("Location"));
            Log.d("Code", "" + urlConnection.getResponseCode());
            if(objectList != null)
            {
                return objectList;
            }
            else {
                return null;
            }
        } catch (Exception ex) { Log.e("TAG", "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<T> result) {
        super.onPostExecute(result);
        //TODO
    }
}

