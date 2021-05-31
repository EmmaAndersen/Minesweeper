package com.example.minesweeper;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author Cassandra Chedjou
 * */
public class AdapterScore extends RecyclerView.Adapter<AdapterScore.ViewHolder> {

    private String[] name;
    private String[] score;
    private int bestScore;
    public AdapterScore(List<String> n, List<String> secondArray, int bestScore){

        name= new String[n.size()];
        score= new String[secondArray.size()];
        this.bestScore = bestScore;
        for(int i=0; i<n.size(); i++)
        {
            name[i] = n.get(i);
            score[i] = secondArray.get(i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowrank,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.na.setText(name[position]);
        holder.sc.setText(score[position]);
        if(position == bestScore){
            holder.sc.setBackgroundResource(R.drawable.button_design);
        }
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView na, sc;

        public ViewHolder(final View itemView){
            super(itemView);
            na = itemView.findViewById(R.id.textnam);
            sc = itemView.findViewById(R.id.textsco);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}