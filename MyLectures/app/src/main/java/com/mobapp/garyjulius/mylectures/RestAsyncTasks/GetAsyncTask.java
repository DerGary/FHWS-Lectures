package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mobapp.garyjulius.mylectures.Helper.ExtendedGson;
import com.mobapp.garyjulius.mylectures.R;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class GetAsyncTask<T> extends AsyncTask<Integer,Void,Object> {
    final static String TAG = "GetAsyncTask";
    private Activity _context;
    public GetAsyncTask(Activity context)
    {
        this._context = context;
    }
    @Override
    protected Object doInBackground(Integer... id) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(_context.getString(R.string.server_basepath + id[0]));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_context.getString(R.string.http_get));
            urlConnection.setConnectTimeout(10*1000);
            urlConnection.setReadTimeout(10*1000);

            Gson gson = ExtendedGson.getInstance();

            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String json = IOUtils.toString(urlConnection.getInputStream());
            T object = gson.fromJson(json,tClass);

            if(object != null){
               return object;
            } else {
                return _context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getMessage());
        } finally {
            urlConnection.disconnect();
        }
        return _context.getString(R.string.asynctask_returnerror);
    }
}
