package com.example.androidassignment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.R;

import java.util.ArrayList;

public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>{
    private final ArrayList<Animal> animals;
    protected animalListener animalListener;

    public AnimalRecyclerViewAdapter(ArrayList<Animal> animals, animalListener animalListener) {
        this.animals = animals;
        this.animalListener=animalListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public CheckBox checkBox;
        Animal animal;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            imageView = view.findViewById(R.id.animalImageView);
            checkBox = view.findViewById(R.id.fav_toggle);
        }

        public void setData(Animal animal) {
            this.animal = animal;
            imageView.setImageResource(animal.drawable);
            checkBox.setChecked(animal.isLiked);
        }

        @Override
        public void onClick(View view) {
            if (animalListener != null) {
                animalListener.onItemClick(animal);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list animal
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.animal_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData(this.animals.get(position));
    }

    @Override
    public int getItemCount() {
        return this.animals.size();
    }

    public interface animalListener {
        void onItemClick(Animal animal);
    }
}
