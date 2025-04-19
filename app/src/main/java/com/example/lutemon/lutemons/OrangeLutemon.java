package com.example.lutemon.lutemons;

import com.example.lutemon.R;

public class OrangeLutemon extends Lutemon{
    public OrangeLutemon(String name, int experience) {
        super(name, "Orange", experience, 17, 8 + 2*experience, 1 + experience, R.drawable.orange_lutemon);
    }
}
