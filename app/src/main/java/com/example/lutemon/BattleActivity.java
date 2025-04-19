package com.example.lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

public class BattleActivity extends AppCompatActivity {
    private Storage storage;
    private Lutemon lutemonAlly;
    private Lutemon lutemonAllyClone;
    private Lutemon lutemonEnemy;
    private TextView textViewNameAlly;
    private TextView textViewNameEnemy;
    private ProgressBar progressBarHealthAlly;
    private ProgressBar progressBarHealthEnemy;
    private TextView battleLog;
    private String battleLogText;
    private ScrollView battleLogScroll;
    private ImageView imageAlly;
    private ImageView imageEnemy;

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

        //saving
        storage.saveLutemons(this);

        //creating enemy with a random color but with the same experience
        double randomNumber = Math.random();
        if (randomNumber >= 0.8) {
            lutemonEnemy = new OrangeLutemon("Wild Lutemon", lutemonAlly.getExperience());
        } else if (randomNumber >= 0.6) {
            lutemonEnemy = new PinkLutemon("Wild Lutemon", lutemonAlly.getExperience());
        } else if (randomNumber >= 0.4) {
            lutemonEnemy = new BlackLutemon("Wild Lutemon", lutemonAlly.getExperience());
        } else if (randomNumber >= 0.2) {
            lutemonEnemy = new GreenLutemon("Wild Lutemon", lutemonAlly.getExperience());
        } else {
            lutemonEnemy = new WhiteLutemon("Wild Lutemon", lutemonAlly.getExperience());
        }


        //setting up the views
        imageAlly = findViewById(R.id.imageViewAlly);
        imageAlly.setImageResource(lutemonAlly.getImageID());

        imageEnemy = findViewById(R.id.imageViewEnemy);
        imageEnemy.setImageResource(lutemonEnemy.getImageID());

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

        battleLog = findViewById(R.id.textViewBattleLog);
        battleLogText = String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonAllyClone.getName(), lutemonAllyClone.getExperience(), lutemonAllyClone.getAttack(), lutemonAllyClone.getDefense(), lutemonAllyClone.getHealth(), lutemonAllyClone.getMaxHealth());
        battleLogText += "\n" + String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonEnemy.getName(), lutemonEnemy.getExperience(), lutemonEnemy.getAttack(), lutemonEnemy.getDefense(), lutemonEnemy.getHealth(), lutemonEnemy.getMaxHealth());
        battleLog.setText(battleLogText);

        battleLogScroll = findViewById(R.id.ScrollViewBattleLog);



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
        //dodging
        boolean enemyDodged = false;
        boolean allyDodged = false;
        if (Math.random() < 0.15) {
            allyDodged = true;
        }
        if (Math.random() > 0.74) {
            enemyDodged = true;
        }

        //attacking + handle win/loss
        battleLogText +="\n" + lutemonAllyClone.getName() + " attacked " + lutemonEnemy.getName();
        if (!enemyDodged) {
            lutemonAllyClone.attack(lutemonEnemy);
        } else{
            battleLogText +="\n" + lutemonEnemy.getName() + " dodged the attack";
        }

        progressBarHealthEnemy.setProgress(lutemonEnemy.getHealth());

        // manage death
        if (lutemonEnemy.getHealth() <= 0){
            battleLogText +="\n" + lutemonEnemy.getName() + " died";
            lutemonAlly.increaseWinCounter();
            lutemonAlly.increaseExperience();
            launchBattleOutcome(view, "won");
            return;
        }


        //enemy attacking back
        battleLogText += "\n" + lutemonEnemy.getName() + " escaped death and attacked back";
        battleLog.setText(battleLogText);

        if (!allyDodged) {
            lutemonEnemy.attack(lutemonAllyClone);
        } else {
            battleLogText +="\n" + lutemonAllyClone.getName() + " dodged the attack";
        }

        progressBarHealthAlly.setProgress(lutemonAllyClone.getHealth());

        if (lutemonAllyClone.getHealth() <= 0){
            battleLogText += "\n" + lutemonAllyClone.getName() + " died";
            launchBattleOutcome(view, "lost");
        }


        battleLogText += "\n" + lutemonAllyClone.getName() + " escaped death";
        battleLogText +="\n#####\n" + String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonAllyClone.getName(), lutemonAllyClone.getExperience(), lutemonAllyClone.getAttack(), lutemonAllyClone.getDefense(), lutemonAllyClone.getHealth(), lutemonAllyClone.getMaxHealth());
        battleLogText += "\n" + String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonEnemy.getName(), lutemonEnemy.getExperience(), lutemonEnemy.getAttack(), lutemonEnemy.getDefense(), lutemonEnemy.getHealth(), lutemonEnemy.getMaxHealth());
        battleLog.setText(battleLogText);
        battleLogScroll.fullScroll(View.FOCUS_DOWN);
    }

    public void defend(View view){
        boolean allyDodged = false;
        if (Math.random() < 0.1) {
            allyDodged = true;
        }

        lutemonAllyClone.increaseDefense(1);

        battleLogText +="\n" + lutemonAllyClone.getName() + " increased defense";
        battleLogText += "\n" + lutemonEnemy.getName() + " attacked";
        battleLog.setText(battleLogText);
        if (!allyDodged) {
            lutemonEnemy.attack(lutemonAllyClone);
        } else {
            battleLogText +="\n" + lutemonAllyClone.getName() + " dodged the attack";
        }
        progressBarHealthAlly.setProgress(lutemonAllyClone.getHealth());
        progressBarHealthEnemy.setProgress(lutemonEnemy.getHealth());

        if (lutemonAllyClone.getHealth() <= 0){
            battleLogText += "\n" + lutemonAllyClone.getName() + "died";
            launchBattleOutcome(view, "lost");
        }

        battleLogText += "\n"+ lutemonAllyClone.getName() + " escaped death";
        battleLogText +="\n#####\n" + String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonAllyClone.getName(), lutemonAllyClone.getExperience(), lutemonAllyClone.getAttack(), lutemonAllyClone.getDefense(), lutemonAllyClone.getHealth(), lutemonAllyClone.getMaxHealth());
        battleLogText += "\n" + String.format("%s(%d) att: %d; def: %d; health: %d/%d",lutemonEnemy.getName(), lutemonEnemy.getExperience(), lutemonEnemy.getAttack(), lutemonEnemy.getDefense(), lutemonEnemy.getHealth(), lutemonEnemy.getMaxHealth());
        battleLog.setText(battleLogText);
        battleLogScroll.fullScroll(View.FOCUS_DOWN);
    }



}