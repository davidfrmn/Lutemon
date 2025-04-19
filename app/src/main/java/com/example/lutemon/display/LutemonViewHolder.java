package com.example.lutemon.display;

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

    public LutemonViewHolder(View itemView, final LutemonAdapter.OnItemClickListener listener) {
        super(itemView);
        textViewDataLine1 = itemView.findViewById(R.id.textViewDataLine1);
        textViewDataLine2 = itemView.findViewById(R.id.textViewDataLine2);
        imageViewLutemon = itemView.findViewById(R.id.imageViewAvatar);

        //set click listener for the entire item
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }
        });

    }

    public void bind(Lutemon lutemon, boolean modeBattleStatistics) {
        //TODO set avatar

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


}
