package com.magdaproject.knightchessboardapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.magdaproject.knightchessboardapp.model.Point;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PathsAdapter extends RecyclerView.Adapter<PathsAdapter.PathViewHolder> {

    HashSet<ArrayList<Point>> pathsList;

    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PathViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PathViewHolder extends RecyclerView.ViewHolder {
        public PathViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
