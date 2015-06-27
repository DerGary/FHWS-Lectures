package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

//import com.owlike.genson.Genson;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class GetAsyncTask<T> extends AsyncTask<Integer,Void,Object> {
    Activity context;
    public GetAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected Object doInBackground(Integer... id) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://staging.applab.fhws.de:8080/fhwslectures/api/" + id[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("Content-Type","application/json");
            Gson gson = new Gson();
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String json = IOUtils.toString(urlConnection.getInputStream());
            T object = gson.fromJson(json,tClass);

            Log.d("URL", "" + urlConnection.getHeaderField("Location"));
            Log.d("Code", "" + urlConnection.getResponseCode());
            if(object != null)
            {
               return object;
            }
            else {
                return "Code: " + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
            }
        } catch (Exception ex) { Log.e("TAG", "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
        }
        return "Error";
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        //TODO
    }
}
