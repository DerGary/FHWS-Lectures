package com.mobapp.garyjulius.mylectures.Model;


import java.util.ArrayList;

/**
 * Created by Julius on 25.06.2015.
 */
public class DataBase {
    private ArrayList<Docent> _docentList;
    private ArrayList<Lecture> _lectureList;
    private ArrayList<Event> _eventList;

    public DataBase(ArrayList<Docent> _docentList,
                    ArrayList<Lecture> _lectureList,
                    ArrayList<Event> _eventList)
    {
        this._docentList = _docentList;
        this._lectureList = _lectureList;
        this._eventList = _eventList;
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
