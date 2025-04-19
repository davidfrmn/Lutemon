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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.display.LutemonAdapter;
import com.example.lutemon.lutemons.Lutemon;

public class SelectLutemonActivity extends AppCompatActivity implements LutemonAdapter.OnItemClickListener {
    private Storage storage;
    private LutemonAdapter lutemonAdapter;
    private RecyclerView recyclerView;

    public RecyclerView setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewLutemonSelection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return recyclerView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        storage = Storage.getInstance();
        storage.loadLutemons(this);

        if (storage.listLutemons().isEmpty()) {
            Toast.makeText(this, "No lutemons created", Toast.LENGTH_SHORT).show();
            launchMenu(null);
        }

        lutemonAdapter = new LutemonAdapter(this, storage.listLutemons(), false, this);
        recyclerView = setupRecyclerView();
        recyclerView.setAdapter(lutemonAdapter);
    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //handle selection
    @Override
    public void onItemClick(int position) {
        Lutemon selectedLutemon = lutemonAdapter.getLutemonAtPosition(position);
        if (selectedLutemon != null) {
            storage.setActiveLutemon(selectedLutemon);
            launchMenu(null);
        }

    }


}