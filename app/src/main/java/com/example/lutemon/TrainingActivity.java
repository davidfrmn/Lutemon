package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemon.lutemons.Lutemon;

public class TrainingActivity extends AppCompatActivity {
    private Storage storage;
    Lutemon lutemon;

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
            // Train button functionality
            findViewById(R.id.btnTrain).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lutemon.increaseExperience(); // Increases experience
                    Toast.makeText(TrainingActivity.this,
                            lutemon.getName() + " trained! ATK: " +
                                    lutemon.getAttack() + " DEF: " + lutemon.getDefense(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No lutemon selected", Toast.LENGTH_SHORT).show();
            launchMenu(null);
        }

    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}