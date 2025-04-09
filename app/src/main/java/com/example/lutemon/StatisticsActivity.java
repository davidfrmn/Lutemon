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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.display.LutemonAdapter;

public class StatisticsActivity extends AppCompatActivity {
    private Storage storage;
    private LutemonAdapter lutemonAdapter;
    private RecyclerView recyclerView;
    private TextView textViewNumberOfLutemons;
    private TextView textViewNumberOfBattles;
    private TextView textViewNumberOfTrainings;
    private TextView textViewnumberOfWins;

    public RecyclerView setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewLutemonStats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return recyclerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewNumberOfLutemons = findViewById(R.id.textViewNumberOfLutemons);
        textViewNumberOfBattles = findViewById(R.id.textViewNumberOfBattles);
        textViewNumberOfTrainings = findViewById(R.id.textViewNumberOfTrainings);
        textViewnumberOfWins = findViewById(R.id.textViewnumberOfWins);

        storage = Storage.getInstance();
        lutemonAdapter = new LutemonAdapter(this, storage.listLutemons(), true);
        recyclerView = setupRecyclerView();
        recyclerView.setAdapter(lutemonAdapter);

        String textNoLutemons = "Number of Lutemons: " + String.valueOf(Statistics.numberOfLutemons(storage.listLutemons()));
        textViewNumberOfLutemons.setText(textNoLutemons);

        String textNoBattles = "Number of Battles: " + String.valueOf(Statistics.numberOfBattles(storage.listLutemons()));
        textViewNumberOfBattles.setText(textNoBattles);

        String textNoTrainings = "Number of Trainings: " + String.valueOf(Statistics.numberOfTrainings(storage.listLutemons()));
        textViewNumberOfTrainings.setText(textNoTrainings);

        String textNoWins = "Number of Wins: " + String.valueOf(Statistics.numberOfWins(storage.listLutemons()));
        textViewnumberOfWins.setText(textNoWins);


    }
    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}