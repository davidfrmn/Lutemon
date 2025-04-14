package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemon.lutemons.BlackLutemon;
import com.example.lutemon.lutemons.Lutemon;

public class BattleActivity extends AppCompatActivity {
    private Storage storage;
    private Lutemon lutemonAlly;
    private Lutemon lutemonAllyClone;
    private Lutemon lutemonEnemy;
    TextView textViewNameAlly;
    TextView textViewNameEnemy;
    ProgressBar progressBarHealthAlly;
    ProgressBar progressBarHealthEnemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        storage = Storage.getInstance();

        // if there is no selected lutemon, launch menu
        // if there is a selected lutemon, get it and clone it (allows temporary buffs)
        if (storage.getActiveLutemon() != null) {
            lutemonAlly = storage.getActiveLutemon();
            lutemonAllyClone = lutemonAlly.getClone();
        } else {
            Toast.makeText(this, "No lutemon selected", Toast.LENGTH_SHORT).show();
            launchMenu(null);
        }

        lutemonAlly.increaseBattleCounter();
        lutemonEnemy = new BlackLutemon("Wild Lutemon", lutemonAlly.getExperience());

        //setting up the views
        textViewNameAlly = findViewById(R.id.textViewNameAlly);
        textViewNameAlly.setText(lutemonAlly.getName());

        textViewNameEnemy = findViewById(R.id.textViewEnemyName);
        textViewNameEnemy.setText(lutemonEnemy.getName());

        progressBarHealthAlly = findViewById(R.id.progressBarHealthAlly);
        progressBarHealthAlly.setMax(lutemonAlly.getMaxHealth());
        progressBarHealthAlly.setProgress(lutemonAllyClone.getHealth());

        progressBarHealthEnemy = findViewById(R.id.progressBarHealthEnemy);
        progressBarHealthEnemy.setMax(lutemonEnemy.getMaxHealth());
        progressBarHealthEnemy.setProgress(lutemonEnemy.getHealth());


    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void launchBattleOutcome(View view, String outcome) {
        Intent intent = new Intent(this, BattleOutcomeActivity.class);
        intent.putExtra("outcome", outcome);
        startActivity(intent);
    }

    public void attack(View view){
        //attacking + handle win/loss
        lutemonAllyClone.attack(lutemonEnemy);
        if (lutemonEnemy.getHealth() <= 0){
            lutemonAlly.increaseWinCounter();
            lutemonAlly.increaseExperience();
            launchBattleOutcome(view, "won");
        }
        lutemonEnemy.attack(lutemonAllyClone);
        if (lutemonAllyClone.getHealth() <= 0){
            launchBattleOutcome(view, "lost");
        }
        //updating healthbar
        progressBarHealthAlly.setProgress(lutemonAllyClone.getHealth());
        progressBarHealthEnemy.setProgress(lutemonEnemy.getHealth());
    }

    public void defend(View view){
        lutemonAllyClone.increaseDefense(1);
        lutemonEnemy.attack(lutemonAllyClone);
    }



}