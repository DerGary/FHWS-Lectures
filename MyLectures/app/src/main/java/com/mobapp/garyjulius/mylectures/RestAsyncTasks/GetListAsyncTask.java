package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobapp.garyjulius.mylectures.Helper.ExtendedGson;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Julius on 25.06.2015.
 */
public class GetListAsyncTask<T> extends AsyncTask<String, Void, ArrayList> {
    final String TAG = "GetListAsyncTask_ " + ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    private Activity _context;

    public GetListAsyncTask(Activity context) {
        this._context = context;
    }

    @Override
    protected ArrayList doInBackground(String... ext) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(_context.getString(R.string.server_basepath) + ext[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_context.getString(R.string.http_get));
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);

            Gson gson = ExtendedGson.getInstance();

            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String response = IOUtils.toString(urlConnection.getInputStream());
            if (tClass.getName().contains("Docent")) {
                Type docentType = new TypeToken<ArrayList<Docent>>() {
                }.getType();
                ArrayList<Docent> objectList = gson.fromJson(response, docentType);

                return objectList;
            } else if (tClass.getName().contains("Event")) {
                Type docentType = new TypeToken<ArrayList<Event>>() {
                }.getType();
                ArrayList<Event> objectList = gson.fromJson(response, docentType);

                return objectList;
            } else if (tClass.getName().contains("Lecture")) {
                Type docentType = new TypeToken<ArrayList<Lecture>>() {
                }.getType();
                ArrayList<Lecture> objectList = gson.fromJson(response, docentType);

                return objectList;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return new ArrayList();
    }
}

