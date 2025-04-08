package com.example.lutemon.lutemons;

public class Lutemon {

    private final int id;
    private static int idCounter = 0;
    private final String name;
    private final String color;
    private int experience;
    private final int maxHealth;
    private int attack;
    private int defense;
    private int health;
    private int battleCounter;
    private int winCounter;
    private int trainingCounter;

    public Lutemon(String name, String color, int experience, int maxHealth, int attack, int defense){
        this.id = idCounter++;
        this.name = name;
        this.color = color;
        this.experience = experience;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.health = maxHealth;
        this.battleCounter = 0;
        this.winCounter = 0;
        this.trainingCounter = 0;
    }

    public int getId() {return id;}
    public String getName() {return this.name;}
    public String getColor() {return this.color;}
    public int getExperience() {return this.experience;}
    public void increaseExperience() {
        this.experience++;
        this.attack+=2;
        this.defense+=1;
    }

    public int getAttack() {return this.attack;}
    public int getDefense() {return this.defense;}
    public int getMaxHealth() {return this.maxHealth;}
    public int getHealth() {return this.health;}
    public int getBattleCounter() {return this.battleCounter;}
    public void increaseBattleCounter() {this.battleCounter++;}
    public int getWinCounter() {return this.winCounter;}
    public void increaseWinCounter() {this.winCounter++;}
    public int getTrainingCounter() {return this.trainingCounter;}
    public void increaseTrainingCounter() {this.trainingCounter++;}
    public static int getIdCounter() {return idCounter;}

    //for temporary battle modifications
    //it increases the id but it doesn't matter since the purpose of the id is to identify the lutemons and it will serve its purpose
    public Lutemon getClone(){
        idCounter--; // it will decrease the id number so we will get continuous id numbers and since we don't save the close it doesn't matter if it has the same id as another lutemon
        return new Lutemon(this.name, this.color, this.experience, this.maxHealth, this.attack, this.defense);
    }
    public void increaseDefense(){ this.defense++;}

    //for fighting
    public void decreaseHealth(int damage){
        health -= (damage - this.defense);
        if(health <= 0){
            health = 0;
        }
    }

    public void attack(Lutemon lutemon){
        lutemon.decreaseHealth(this.attack);
    }

}
