package com.mobapp.garyjulius.mylectures.Model;

import java.util.ArrayList;

/**
 * Created by Stefan on 11-05-15.
 */

public class Lecture {
    private String _title;
    private Docent _responsiblePerson;
    private ArrayList<Docent> _docents;
    private Language _language;
    private int _swh;
    private ArrayList<LectureType> _type;
    private int _creditPoints;
    private ArrayList<ExamType> _examType;
    private int _semester;
    private ArrayList<Course> _courses;

    public Lecture(String _title,Docent _responsiblePerson, ArrayList<Docent> _docents, Language _language, int _swh, ArrayList<LectureType> _type, int _creditPoints, ArrayList<ExamType> _examType, int _semester, ArrayList<Course> _courses) {
        this._title = _title;
        this._responsiblePerson = _responsiblePerson;
        this._docents = _docents;
        this._language = _language;
        this._swh = _swh;
        this._type = _type;
        this._creditPoints = _creditPoints;
        this._examType = _examType;
        this._semester = _semester;
        this._courses = _courses;
    }

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

    public Language get_language() {
        return _language;
    }

    public void set_language(Language _language) {
        this._language = _language;
    }

    public int get_swh() {
        return _swh;
    }

    public void set_swh(int _swh) {
        this._swh = _swh;
    }

    public ArrayList<LectureType> get_type() {
        return _type;
    }

    public void set_type(ArrayList<LectureType> type) {
        _type = type;
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

    public ArrayList<Course> get_courses() {
        return _courses;
    }

    public void set_courses(ArrayList<Course> _courses) {
        this._courses = _courses;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }
}
