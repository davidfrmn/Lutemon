package com.example.lutemon.lutemons;

public class WhiteLutemon extends Lutemon{
    public WhiteLutemon(String name, int experience) {
        super(name, "White", experience, 20, 5 + 2*experience, 4 + experience);
    }
}
