package com.mobapp.garyjulius.mylectures.RestAsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

//import com.owlike.genson.Genson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobapp.garyjulius.mylectures.Helper.DateTimeSerializer;
import com.mobapp.garyjulius.mylectures.R;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;

import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julius on 30.05.2015.
 */
public class GetAsyncTask<T> extends AsyncTask<Integer,Void,Object> {
    final static String TAG = "GetAsyncTask";
    Activity context;
    public GetAsyncTask(Activity context)
    {
        this.context = context;
    }
    @Override
    protected Object doInBackground(Integer... id) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(context.getString(R.string.server_basepath + id[0]));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(context.getString(R.string.http_get));
            urlConnection.setConnectTimeout(10*1000);
            urlConnection.setReadTimeout(10*1000);
            //urlConnection.setRequestProperty("Content-Type","application/json");
            Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeSerializer()).create();
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            String json = IOUtils.toString(urlConnection.getInputStream());
            T object = gson.fromJson(json,tClass);

            Log.d("URL", "" + urlConnection.getHeaderField(context.getString(R.string.header_location)));
            Log.d("Code", "" + urlConnection.getResponseCode());
            if(object != null)
            {
               return object;
            }
            else {
                return context.getString(R.string.http_errorcode_title) + urlConnection.getResponseCode() + " " + urlConnection.getResponseMessage();
            }
        } catch (Exception ex) { Log.e(TAG, "" + ex.getMessage()); }
        finally {
            urlConnection.disconnect();
        }
        return context.getString(R.string.asynctask_returnerror);
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        //TODO
    }
}
