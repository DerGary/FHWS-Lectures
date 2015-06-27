package com.mobapp.garyjulius.mylectures.Model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Stefan on 11-05-15.
 */
public class Event {
    private int _id;
    private int _lecture;
    private ArrayList<Integer> _docent;
    private DateTime _beginTime;
    private DateTime _endTime;
    private LectureType _type;
    private String _room;

    public Event(int _id, int _lecture, ArrayList<Integer> _docent, DateTime _beginTime, DateTime _endTime, LectureType _type, String _room) {
        this._id = _id;
        this._lecture = _lecture;
        this._docent = _docent;
        this._beginTime = _beginTime;
        this._endTime = _endTime;
        this._type = _type;
        this._room = _room;
    }

    public int get_lecture() {
        return _lecture;
    }

    public void set_lecture(int _lecture) {
        this._lecture = _lecture;
    }

    public ArrayList<Integer> get_docent() {
        return _docent;
    }

    public void set_docent(ArrayList<Integer> _docent) {
        this._docent = _docent;
    }

    public String get_beginTime() {
        return _beginTime.toString();
    }
    public DateTime get_beginTimeDateTime() {
        return _beginTime;
    }

    public void set_beginTime(String _beginTime) {
        this._beginTime = DateTime.parse(_beginTime);
    }

    public String get_endTime() {
        return _endTime.toString();
    }
    public DateTime get_endTimeDateTime() {
        return _endTime;
    }


    public void set_endTime(String _endTime) {
        this._endTime = DateTime.parse(_endTime);
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
