package com.mobapp.garyjulius.mylectures.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Stefan on 11-05-15.
 */
public class Event {
    private Lecture _lecture;
    private ArrayList<Docent> _docent;
    private GregorianCalendar _beginTime;
    private GregorianCalendar _endTime;
    private LectureType _type;
    private String _room;

    public Event(Lecture _lecture, ArrayList<Docent> _docent, GregorianCalendar _beginTime, GregorianCalendar _endTime, LectureType _type, String _room) {
        this._lecture = _lecture;
        this._docent = _docent;
        this._beginTime = _beginTime;
        this._endTime = _endTime;
        this._type = _type;
        this._room = _room;
    }

    public Lecture get_lecture() {
        return _lecture;
    }

    public void set_lecture(Lecture _lecture) {
        this._lecture = _lecture;
    }

    public ArrayList<Docent> get_docent() {
        return _docent;
    }

    public void set_docent(ArrayList<Docent> _docent) {
        this._docent = _docent;
    }

    public GregorianCalendar get_beginTime() {
        return _beginTime;
    }

    public void set_beginTime(GregorianCalendar _beginTime) {
        this._beginTime = _beginTime;
    }

    public GregorianCalendar get_endTime() {
        return _endTime;
    }

    public void set_endTime(GregorianCalendar _endTime) {
        this._endTime = _endTime;
    }

    public LectureType get_type() {
        return _type;
    }

    public void set_type(LectureType _type) {
        this._type = _type;
    }

    public String get_room() {
        return _room;
    }

    public void set_room(String _room) {
        this._room = _room;
    }
}
