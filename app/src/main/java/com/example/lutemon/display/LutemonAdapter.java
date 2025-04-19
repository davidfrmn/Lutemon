package com.example.lutemon.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemon.R;
import com.example.lutemon.lutemons.Lutemon;

import java.util.List;

public class LutemonAdapter  extends RecyclerView.Adapter<LutemonViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final Context context;
    private List<Lutemon> lutemons;
    boolean modeBattleStatistics;
    private OnItemClickListener listener;


    public LutemonAdapter(Context context, List<Lutemon> lutemons, boolean modeBattleStatistics, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.lutemons = lutemons;
        this.modeBattleStatistics = modeBattleStatistics;
    }

    @Override
    public LutemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.lutemon_card, parent, false);
        return new LutemonViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.bind(lutemon, modeBattleStatistics);
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void updateData(List<Lutemon> newLutemons) {
        lutemons = newLutemons;
        notifyDataSetChanged();
    }

    public Lutemon getLutemonAtPosition(int position) {
        if (position < 0 || position >= lutemons.size()) {
            return null;
        }
        return lutemons.get(position);
    }

}
