package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BattleOutcomeActivity extends AppCompatActivity {
    private TextView textViewOutcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle_outcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //getting the outcome from the previous activity
        Intent intent = getIntent();
        String outcome = intent.getStringExtra("outcome");

        //saving
        Storage storage = Storage.getInstance();
        storage.saveLutemons(this);

        //setting the outcome text
        textViewOutcome = findViewById(R.id.textViewOutcome);
        String textOutcome = "You have " + outcome + " the match!";
        textViewOutcome.setText(textOutcome);

    }


    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}