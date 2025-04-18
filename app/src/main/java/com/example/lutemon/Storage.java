package com.example.lutemon;

import com.example.lutemon.lutemons.Lutemon;

import java.util.ArrayList;

public class Storage {
    private static Storage instance;
    private Lutemon activeLutemon;
    private ArrayList<Lutemon> lutemons;
    private int totalTrainings;

    public void saveLutemons() {
    }

    private Storage() {
        lutemons = new ArrayList<>();
        totalTrainings = 0;
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

    public void increaseTotalTrainings() {
        this.totalTrainings++;
    }

    public int getTotalTrainings() {
        return totalTrainings;
    }

}