package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemon.lutemons.BlackLutemon;
import com.example.lutemon.lutemons.GreenLutemon;
import com.example.lutemon.lutemons.Lutemon;
import com.example.lutemon.lutemons.OrangeLutemon;
import com.example.lutemon.lutemons.PinkLutemon;
import com.example.lutemon.lutemons.WhiteLutemon;

public class CreateLutemonActivity extends AppCompatActivity {

    private RadioGroup radioGroupColor;
    private EditText editTextName;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storage = Storage.getInstance();

        radioGroupColor = findViewById(R.id.radioGroupColor);
        editTextName = findViewById(R.id.editTextName);
    }

    public void createLutemon(View view) {
        int selectedColorId = radioGroupColor.getCheckedRadioButtonId();
        String name = editTextName.getText().toString();
        int experience = 0;
        if (name.isEmpty()) {
            name = "Unnamed Lutemon";
        }

        Lutemon lutemon = null;
        if (selectedColorId == R.id.radioButtonOrange) {
            lutemon = new OrangeLutemon(name, experience);
        } else if (selectedColorId == R.id.radioButtonGreen) {
            lutemon = new GreenLutemon(name, experience);
        } else if (selectedColorId == R.id.radioButtonPink) {
            lutemon = new PinkLutemon(name, experience);
        } else if (selectedColorId == R.id.radioButtonBlack) {
            lutemon = new BlackLutemon(name, experience);
        } else if (selectedColorId == R.id.radioButtonWhite) {
            lutemon = new WhiteLutemon(name, experience);
        }

        storage.addLutemon(lutemon);
        storage.saveLutemons(this);
        storage.setActiveLutemon(lutemon);
        launchMenu(view);
    }


    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}