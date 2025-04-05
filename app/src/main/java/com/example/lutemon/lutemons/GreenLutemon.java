package com.example.lutemon.lutemons;

public class GreenLutemon extends Lutemon{
    public GreenLutemon(String name, int experience) {
        super(name, "Green", experience, 19, 6 + 2*experience, 3 + experience);
    }
}
