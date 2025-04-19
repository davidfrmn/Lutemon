package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemon.lutemons.Lutemon;

public class TrainingActivity extends AppCompatActivity {
    private Storage storage;
    private Lutemon lutemon;
    private TextView textViewName;
    private TextView textViewTrainingStat;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storage = Storage.getInstance();
        if (storage.getActiveLutemon() != null) {
            lutemon = storage.getActiveLutemon();
        } else {
            Toast.makeText(this, "No lutemon selected", Toast.LENGTH_SHORT).show();
            launchMenu(null);
        }

        imageView = findViewById(R.id.imageView);
        textViewName = findViewById(R.id.textViewTrainingDataLine1);
        textViewTrainingStat = findViewById(R.id.textViewTrainingDataLine2);
        updateTrainingData();

    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void train(View view){
        lutemon.increaseTrainingCounter();
        lutemon.increaseExperience();
        updateTrainingData();
    }

    private void updateTrainingData() {
        textViewName.setText(lutemon.getName() + " (" + lutemon.getExperience() + ")");
        textViewTrainingStat.setText("MaxHP: " + lutemon.getMaxHealth() +
                "\nAttack: " + lutemon.getAttack() +
                "\nDefense: " + lutemon.getDefense());
    }

}