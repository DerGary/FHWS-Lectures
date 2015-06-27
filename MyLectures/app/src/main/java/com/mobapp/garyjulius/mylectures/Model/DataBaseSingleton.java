package com.mobapp.garyjulius.mylectures.Model;

import java.util.ArrayList;

/**
 * Created by Julius on 25.06.2015.
 */
public class DataBaseSingleton {
    private static DataBaseSingleton ourInstance = new DataBaseSingleton();

    public static DataBaseSingleton getInstance() {
        return ourInstance;
    }

    private DataBaseSingleton() {
        this._docentList = new ArrayList<>();
        this._lectureList = new ArrayList<>();
        this._eventList = new ArrayList<>();
    }

    private ArrayList<Docent> _docentList;
    private ArrayList<Lecture> _lectureList;
    private ArrayList<Event> _eventList;

    public Docent getDocentFromID(int docentId)
    {
        for(Docent d:_docentList)
        {
            if(d.get_id() == docentId)
            {
                return d;
            }
        }
        return null;
    }

    public Event getEventFromId(int eventID)
    {
        for(Event e:_eventList)
        {
            if(e.get_id() == eventID)
            {
                return e;
            }
        }
        return null;
    }

    public Lecture getLectureFromId(int lectureID)
    {
        for(Lecture l:_lectureList)
        {
            if(l.get_id() == lectureID)
            {
                return l;
            }
        }
        return null;
    }
    public ArrayList<Docent> get_docentList() {
        return _docentList;
    }

    public void set_docentList(ArrayList<Docent> _docentList) {
        this._docentList = _docentList;
    }

    public ArrayList<Lecture> get_lectureList() {
        return _lectureList;
    }

    public void set_lectureList(ArrayList<Lecture> _lectureList) {
        this._lectureList = _lectureList;
    }

    public ArrayList<Event> get_eventList() {
        return _eventList;
    }

    public void set_eventList(ArrayList<Event> _eventList) {
        this._eventList = _eventList;
    }
}
