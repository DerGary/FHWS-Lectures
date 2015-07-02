package de.fhws.applab.restdemo.models.database;

import java.util.*;

/**
 * Created by Stefan on 25-06-15.
 */
public class Database<T> {
    public int id = 1;
    private HashMap<Integer,T> dictionary;

    public Database(){
        dictionary = new HashMap<>();
    }
    public T getValue(int key){
        return dictionary.get(key);
    }

    public boolean setValue(int key, T obj){
        if(dictionary.containsKey(key)){
            return false;
        }
        dictionary.put(key,obj);
        return true;
    }
    public boolean replaceValue(int key, T obj){
        if(dictionary.put(key,obj) == null){
            return false;
        }
        return true;
    }
    public Collection<T> getAll(){
        return dictionary.values();
    }
    public int getNextKey(){
        return id++;
    }
    public boolean removeValue(int key){
        if (dictionary.remove(key) == null)
            return false;
        return true;
    }
}
