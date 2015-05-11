package com.mobapp.garyjulius.mylectures.Model;

import java.net.URL;

/**
 * Created by Stefan on 11-05-15.
 */
public class Docent {
    private String _name;
    private String _phoneNumber;
    private String _faxNumber;
    private String _email;
    private String _room;
    private String _location;
    private String _consultationHour;
    private String _function;
    private URL _picture;
    private String _linkWeLearn;

    public Docent(String _name, String _phoneNumber, String _faxNumber, String _email, String _room, String _location, String _consultationHour, String _function, URL _picture, String _linkWeLearn) {
        this._name = _name;
        this._phoneNumber = _phoneNumber;
        this._faxNumber = _faxNumber;
        this._email = _email;
        this._room = _room;
        this._location = _location;
        this._consultationHour = _consultationHour;
        this._function = _function;
        this._picture = _picture;
        this._linkWeLearn = _linkWeLearn;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String get_faxNumber() {
        return _faxNumber;
    }

    public void set_faxNumber(String _faxNumber) {
        this._faxNumber = _faxNumber;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_room() {
        return _room;
    }

    public void set_room(String _room) {
        this._room = _room;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_consultationHour() {
        return _consultationHour;
    }

    public void set_consultationHour(String _consultationHour) {
        this._consultationHour = _consultationHour;
    }

    public String get_function() {
        return _function;
    }

    public void set_function(String _function) {
        this._function = _function;
    }

    public URL get_picture() {
        return _picture;
    }

    public void set_picture(URL _picture) {
        this._picture = _picture;
    }

    public String get_linkWeLearn() {
        return _linkWeLearn;
    }

    public void set_linkWeLearn(String _linkWeLearn) {
        this._linkWeLearn = _linkWeLearn;
    }
}
