package com.example.lutemon;

import com.example.lutemon.lutemons.Lutemon;

import java.util.List;

public class Statistics {

    public static int numberOfLutemons(){
        return Lutemon.getIdCounter();
    }
    public static int numberOfBattles(List<Lutemon> lutemons){
        int battles = 0;
        for (Lutemon lutemon : lutemons) {
            battles += lutemon.getBattleCounter();
        }
        return battles;
    }

    public static int numberOfTrainings(List<Lutemon> lutemons){
        int trainings = 0;
        for (Lutemon lutemon : lutemons) {
            trainings += lutemon.getTrainingCounter();
        }
        return trainings;
    }

    public static int numberOfWins(List<Lutemon> lutemons){
        int wins = 0;
        for (Lutemon lutemon : lutemons) {
            wins += lutemon.getWinCounter();
        }
        return wins;
    }


}
