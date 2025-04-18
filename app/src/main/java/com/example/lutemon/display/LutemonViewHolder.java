package com.example.lutemon.display;

import android.graphics.Color; // Import Color
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.R;
import com.example.lutemon.lutemons.Lutemon;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewDataLine1;
    private TextView textViewDataLine2;
    private ImageView imageViewLutemon;

    public LutemonViewHolder(View itemView) {
        super(itemView);
        textViewDataLine1 = itemView.findViewById(R.id.textViewDataLine1);
        textViewDataLine2 = itemView.findViewById(R.id.textViewDataLine2);
        imageViewLutemon = itemView.findViewById(R.id.imageViewAvatar);
    }

    public void bind(Lutemon lutemon, boolean modeBattleStatistics) {
        //TODO set avatar
        imageViewLutemon.setImageResource(itemView.getContext().getResources().getIdentifier(
                lutemon.getName().toLowerCase(), "drawable", itemView.getContext().getPackageName()));

        String dataLine1 = lutemon.getName() + " (" + lutemon.getExperience() + ")";
        String dataLine2;
        if (modeBattleStatistics) {
            dataLine2 = "Battles: " + lutemon.getBattleCounter() + "| Wins: " + lutemon.getWinCounter() + "| Trainings: " + lutemon.getTrainingCounter();
        } else {
            dataLine2 = "HP: " + lutemon.getMaxHealth() + " | Attack: " + lutemon.getAttack() + " | Defense: " + lutemon.getDefense();
        }

        textViewDataLine1.setText(dataLine1);
        textViewDataLine2.setText(dataLine2);
    }

    public void setActiveState(boolean isActive) {
        if (isActive) {
            itemView.setBackgroundColor(Color.LTGRAY); // Or your preferred active color
        } else {
            itemView.setBackgroundColor(Color.TRANSPARENT); // Default color
        }
    }
}
