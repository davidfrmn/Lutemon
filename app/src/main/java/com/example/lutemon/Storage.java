package com.example.lutemon;

import android.content.Context;
import android.widget.Toast;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;

public class Storage {
    private static Storage instance;
    private Lutemon activeLutemon;
    private ArrayList<Lutemon> lutemons;

    private Storage() {
        lutemons = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }
    public void removeLutemon(Lutemon lutemon) {
        lutemons.remove(lutemon);
    }

    public ArrayList<Lutemon> listLutemons() {
        return lutemons;
    }

    public Lutemon getLutemon(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == id) {
                return lutemon;
            }
        }
        return null;
    }

    public Lutemon getActiveLutemon() {
        return activeLutemon;
    }

    public void setActiveLutemon(Lutemon lutemon) {
        activeLutemon = lutemon;
    }

    public void saveLutemons(Context context) {
        //saving the lutemons
        try {
            FileHandler.saveLutemons(context, this.lutemons);
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void loadLutemons(Context context){
        //loading the lutemons
        try {
            this.lutemons =  FileHandler.loadLutemons(context);
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            System.out.println("Error: " + e.getMessage());
        }

    }

}
