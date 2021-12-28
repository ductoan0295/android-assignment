package com.example.androidassignment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.R;
import com.example.androidassignment.ViewHolder.AnimalViewHolder;

import java.util.ArrayList;

public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalViewHolder> {
    public static ArrayList<Animal> animals;
    public static animalItemListener animalItemListener;

    public AnimalRecyclerViewAdapter(ArrayList<Animal> animals, animalItemListener animalItemListener) {
        AnimalRecyclerViewAdapter.animals = animals;
        this.animalItemListener = animalItemListener;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list animal
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.animal_item, viewGroup, false);

        return new AnimalViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder animalViewHolder, int position) {
        animalViewHolder.setData(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public interface animalItemListener {
        void onItemClick(Animal animal, View view);
    }
}
