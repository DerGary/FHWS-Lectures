package de.fhws.applab.restdemo.models.database;

import de.fhws.applab.restdemo.models.Docent;

/**
 * Created by Stefan on 25-06-15.
 */
public class DocentDatabase {
    public Database<Docent> Database;
    private static DocentDatabase _instance;

    private DocentDatabase(){
        Database = new Database<Docent>();
    }
    public static DocentDatabase getInstance(){
        if(_instance == null){
            _instance = new DocentDatabase();
        }
        return _instance;
    }
}
