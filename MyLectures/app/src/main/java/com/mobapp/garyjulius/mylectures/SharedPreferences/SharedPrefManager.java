package com.mobapp.garyjulius.mylectures.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobapp.garyjulius.mylectures.Helper.DateTimeSerializer;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;

import org.joda.time.DateTime;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 09-04-15.
 */
public class SharedPrefManager {
    private final Context context;
    private final static String My_PREFS_NAME = "SHARED_USER_PREFERENCES";

    public SharedPrefManager(Context context){
        this.context = context;
    }
    public boolean saveDataBase(){
        SharedPreferences.Editor pref = context.getSharedPreferences(My_PREFS_NAME, Context.MODE_PRIVATE).edit();
        List<Event> eventList = DataBaseSingleton.getInstance().get_eventList();
        List<Docent> docentList = DataBaseSingleton.getInstance().get_docentList();
        List<Lecture> lectureList = DataBaseSingleton.getInstance().get_lectureList();

        Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeSerializer()).create();
        String eventJson = gson.toJson(eventList);
        String docentJson = gson.toJson(docentList);
        String lectureJson = gson.toJson(lectureList);

        pref.putString("events", eventJson);
        pref.putString("docents", docentJson);
        pref.putString("lectures", lectureJson);
        return pref.commit();
    }
    public void loadDataBase(){
        SharedPreferences pref = context.getSharedPreferences(My_PREFS_NAME, Context.MODE_PRIVATE);
        String eventJson = pref.getString("events","").toString();
        String docentJson = pref.getString("docents","").toString();
        String lectureJson = pref.getString("lectures","").toString();
        Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class,new DateTimeSerializer()).create();
        if(!TextUtils.isEmpty(eventJson)){
            Type listType = new TypeToken<ArrayList<Event>>() {}.getType();
            ArrayList<Event> eventList = gson.fromJson(eventJson, listType);
            if(eventList != null){
                DataBaseSingleton.getInstance().set_eventList(eventList);
            }
        }
        if(!TextUtils.isEmpty(docentJson)){
            Type listType = new TypeToken<ArrayList<Docent>>() {}.getType();

            ArrayList<Docent> docentList = gson.fromJson(docentJson, listType);
            if(docentList != null){
                DataBaseSingleton.getInstance().set_docentList(docentList);
            }
        }
        if(!TextUtils.isEmpty(lectureJson)){
            Type listType = new TypeToken<ArrayList<Lecture>>() {}.getType();

            ArrayList<Lecture> lectureList = gson.fromJson(lectureJson, listType);
            if(lectureList != null){
                DataBaseSingleton.getInstance().set_lectureList(lectureList);
            }
        }
    }
    public void delete(){
        SharedPreferences.Editor pref = context.getSharedPreferences(My_PREFS_NAME, Context.MODE_PRIVATE).edit();
        pref.clear();
        pref.commit();
    }
}
