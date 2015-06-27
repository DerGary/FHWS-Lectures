package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.internal.asm.Type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Julius on 25.06.2015.
 */
public class GetListAsyncTask<T> extends AsyncTask<String,Void,ArrayList> {
    final String TAG = "GetListAsyncTask_ " + ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    Activity context;
    public GetListAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected ArrayList doInBackground(String... ext) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://staging.applab.fhws.de:8080/fhwslectures/api/" + ext[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            Log.d(TAG,"URL: " + urlConnection.getURL().toString());
            //urlConnection.setRequestProperty("Content-Type","application/json");
            Genson genson = new Genson();
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if(tClass.getName().contains("Docent"))
            {
                GenericType<ArrayList<Docent>> genericType = new GenericType<ArrayList<Docent>>() {
                };

                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Docent> objectList = genson.deserialize(urlConnection.getInputStream(),
                        genericType);

                return objectList;
            } else if(tClass.getName().contains("Event")) {

                GenericType<ArrayList<Event>> genericType = new GenericType<ArrayList<Event>>() {
                };
                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Event> objectList = genson.deserialize(urlConnection.getInputStream(),
                        genericType);

                return objectList;
            } else if(tClass.getName().contains("Lecture")) {

                GenericType<ArrayList<Lecture>> genericType = new GenericType<ArrayList<Lecture>>() {
                };
                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Lecture> objectList = genson.deserialize(urlConnection.getInputStream(),
                        genericType);

                return objectList;
            }
        } catch (Exception ex) { Log.e("TAG", "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
        }
        return new ArrayList();
    }

    @Override
    protected void onPostExecute(ArrayList result) {
        super.onPostExecute(result);
        //Gets overridden!
    }
}

