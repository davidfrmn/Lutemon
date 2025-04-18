package com.example.lutemon.display;

import android.content.Context;
import android.graphics.Color; // Import Color
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.R;
import com.example.lutemon.Storage; // Import Storage
import com.example.lutemon.lutemons.Lutemon;

import java.util.List;

public class LutemonAdapter  extends RecyclerView.Adapter<LutemonViewHolder>{
    private final Context context;
    private List<Lutemon> lutemons;
    boolean modeBattleStatistics;
    // ToDo make them clickable and add as active
    private int activeLutemonPosition = RecyclerView.NO_POSITION; // To track the active Lutemon


    public LutemonAdapter(Context context, List<Lutemon> lutemons, boolean modeBattleStatistics) {
        this.context = context;
        this.lutemons = lutemons;
        this.modeBattleStatistics = modeBattleStatistics;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.lutemon_card, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.bind(lutemon, modeBattleStatistics);

        // Set background color based on active state
        if (position == activeLutemonPosition) {
            holder.itemView.setBackgroundColor(Color.LTGRAY); // Or any color you prefer
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT); // Default color
        }

        // Add OnClickListener to mark as active
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousActive = activeLutemonPosition;
                activeLutemonPosition = holder.getAdapterPosition();
                Lutemon selectedLutemon = lutemons.get(activeLutemonPosition);
                Storage.getInstance().setActiveLutemon(selectedLutemon);

                // Update the visual state of the items that changed
                notifyItemChanged(previousActive);
                notifyItemChanged(activeLutemonPosition);

                // Optional: Provide some feedback
                // Toast.makeText(context, selectedLutemon.getName() + " selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void updateData(List<Lutemon> newLutemons) {
        lutemons = newLutemons;
        notifyDataSetChanged();
    }

}
