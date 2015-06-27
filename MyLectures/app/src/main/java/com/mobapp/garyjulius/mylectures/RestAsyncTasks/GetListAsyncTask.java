package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
//import com.owlike.genson.GenericType;
//import com.owlike.genson.GenericType;
//import com.owlike.genson.Genson;
//import com.owlike.genson.internal.asm.Type;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
            Gson gson = new Gson();
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String response = IOUtils.toString(urlConnection.getInputStream());
            if(tClass.getName().contains("Docent"))
            {
                Type docentType = new TypeToken<ArrayList<Docent>>() {}.getType();

                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Docent> objectList = gson.fromJson(response, docentType);

                return objectList;
            } else if(tClass.getName().contains("Event")) {
                Type docentType = new TypeToken<ArrayList<Event>>() {}.getType();

                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Event> objectList = gson.fromJson(response, docentType);

                return objectList;
            } else if(tClass.getName().contains("Lecture")) {
                Type docentType = new TypeToken<ArrayList<Lecture>>() {}.getType();

                //Log.d("URL", "" + urlConnection.getHeaderField("Location"));
                Log.d(TAG, "ResponseCode: " + urlConnection.getResponseCode());
                ArrayList<Lecture> objectList = gson.fromJson(response, docentType);

                return objectList;
            }
        } catch (Exception ex) { Log.e(TAG, "" + ex.getMessage()); }
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

