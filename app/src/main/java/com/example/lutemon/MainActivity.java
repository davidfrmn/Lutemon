package com.example.lutemon;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelectLutemon;
    private Button buttonTrainLutemon;
    private Button buttonBattle;
    private Button buttonStatisics;
    private Button buttonCreateLutemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonSelectLutemon = findViewById(R.id.buttonSelectLutemon);
        buttonTrainLutemon = findViewById(R.id.buttonTrainLutemon);
        buttonBattle = findViewById(R.id.buttonBattle);
        buttonStatisics = findViewById(R.id.buttonStatisics);
        buttonCreateLutemon = findViewById(R.id.buttonCreateLutemon);

    }




}