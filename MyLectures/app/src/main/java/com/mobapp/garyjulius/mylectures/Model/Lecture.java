package com.mobapp.garyjulius.mylectures.Model;

import java.util.ArrayList;

/**
 * Created by Stefan on 11-05-15.
 */

public class Lecture {
    private Docent _responsiblePerson;
    private ArrayList<Docent> _docents;
    private String _language;
    private int _swh;
    private ArrayList<LectureType> Type;
    private int _creditPoints;
    private ArrayList<ExamType> _examType;
    private int _semester;
    private Course _course;

    public Docent get_responsiblePerson() {
        return _responsiblePerson;
    }

    public void set_responsiblePerson(Docent _responsiblePerson) {
        this._responsiblePerson = _responsiblePerson;
    }

    public ArrayList<Docent> get_docents() {
        return _docents;
    }

    public void set_docents(ArrayList<Docent> _docents) {
        this._docents = _docents;
    }

    public String get_language() {
        return _language;
    }

    public void set_language(String _language) {
        this._language = _language;
    }

    public int get_swh() {
        return _swh;
    }

    public void set_swh(int _swh) {
        this._swh = _swh;
    }

    public ArrayList<LectureType> getType() {
        return Type;
    }

    public void setType(ArrayList<LectureType> type) {
        Type = type;
    }

    public int get_creditPoints() {
        return _creditPoints;
    }

    public void set_creditPoints(int _creditPoints) {
        this._creditPoints = _creditPoints;
    }

    public ArrayList<ExamType> get_examType() {
        return _examType;
    }

    public void set_examType(ArrayList<ExamType> _examType) {
        this._examType = _examType;
    }

    public int get_semester() {
        return _semester;
    }

    public void set_semester(int _semester) {
        this._semester = _semester;
    }

    public Course get_course() {
        return _course;
    }

    public void set_course(Course _course) {
        this._course = _course;
    }
}
