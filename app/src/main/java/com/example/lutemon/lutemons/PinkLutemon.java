package com.example.lutemon.lutemons;

import com.example.lutemon.R;

public class PinkLutemon extends Lutemon{
    public PinkLutemon(String name, int experience) {
        super(name, "Pink", experience, 18, 7 + 2*experience, 2 + experience, R.drawable.pink_lutemon);
    }
}
