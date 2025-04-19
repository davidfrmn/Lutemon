package com.example.lutemon.lutemons;

import com.example.lutemon.R;

public class GreenLutemon extends Lutemon{
    public GreenLutemon(String name, int experience) {
        super(name, "Green", experience, 19, 6 + 2*experience, 3 + experience, R.drawable.green_lutemon);
    }
}
