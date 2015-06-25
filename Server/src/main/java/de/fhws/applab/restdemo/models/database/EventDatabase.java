package de.fhws.applab.restdemo.models.database;

import de.fhws.applab.restdemo.models.Event;

/**
 * Created by Stefan on 25-06-15.
 */
public class EventDatabase {
    public Database<Event> Database;
    private static EventDatabase _instance;

    private EventDatabase(){
        Database = new Database<Event>();
    }
    public static EventDatabase getInstance(){
        if(_instance == null){
            _instance = new EventDatabase();
        }
        return _instance;
    }
}
