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
import com.mobapp.garyjulius.mylectures.R;

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
    private final static String LECTURE_TAG = "lecture";
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    public SharedPrefManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(My_PREFS_NAME, Context.MODE_PRIVATE);
        prefEditor = pref.edit();
    }

    public boolean saveDataBase(){

        List<Event> eventList = DataBaseSingleton.getInstance().get_eventList();
        List<Docent> docentList = DataBaseSingleton.getInstance().get_docentList();
        List<Lecture> lectureList = DataBaseSingleton.getInstance().get_lectureList();

        Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeSerializer()).create();
        String eventJson = gson.toJson(eventList);
        String docentJson = gson.toJson(docentList);
        String lectureJson = gson.toJson(lectureList);

        prefEditor.putString(context.getString(R.string.prefs_events), eventJson);
        prefEditor.putString(context.getString(R.string.prefs_docents), docentJson);
        prefEditor.putString(context.getString(R.string.prefs_lectures), lectureJson);
        return prefEditor.commit();
    }
    public void loadDataBase(){
        String eventJson = pref.getString(context.getString(R.string.prefs_events), "").toString();
        String docentJson = pref.getString(context.getString(R.string.prefs_docents), "").toString();
        String lectureJson = pref.getString(context.getString(R.string.prefs_lectures), "").toString();
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
        prefEditor.clear();
        prefEditor.commit();
    }

    public void saveNote(int lectureID, String note)
    {
        prefEditor.putString(LECTURE_TAG + lectureID,note);
        prefEditor.commit();
    }

    public String getNote(int lectureID)
    {
        return pref.getString(LECTURE_TAG + lectureID, context.getString(R.string.prefs_nonote));
    }
}
