package de.fhws.applab.restdemo.models.database;

import de.fhws.applab.restdemo.models.Lecture;

/**
 * Created by Stefan on 25-06-15.
 */
public class LectureDatabase {
    public Database<Lecture> Database;
    private static LectureDatabase _instance;

    private LectureDatabase(){
        Database = new Database<Lecture>();
    }
    public static LectureDatabase getInstance(){
        if(_instance == null){
            _instance = new LectureDatabase();
        }
        return _instance;
    }
}
