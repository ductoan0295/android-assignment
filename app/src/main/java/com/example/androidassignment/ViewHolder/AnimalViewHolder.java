package com.example.androidassignment.ViewHolder;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Adapter.AnimalRecyclerViewAdapter;
import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.R;

public class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static AnimalRecyclerViewAdapter adapter;
    public ImageView imageView;
    public CheckBox favToggle;
    private Animal animal;

    public AnimalViewHolder(View view, AnimalRecyclerViewAdapter adapter) {
        super(view);
        view.setOnClickListener(this);
        imageView = view.findViewById(R.id.animalImageView);
        favToggle = view.findViewById(R.id.fav_toggle);
        AnimalViewHolder.adapter = adapter;
    }

    public void setData(Animal animal) {
        this.animal = animal;
        imageView.setImageResource(animal.icon_drawable);
        favToggle.setChecked(animal.isLiked);
        favToggle.setOnClickListener(view -> {
            CheckBox favToggle = (CheckBox) view;
            animal.isLiked = favToggle.isChecked();
            int index = adapter.animals.indexOf(animal);
            adapter.animals.set(index, animal);
            adapter.notifyItemChanged(index, animal);
        });
    }

    @Override
    public void onClick(View view) {
        if (adapter.animalItemListener != null) {
            Animation animalAlphaAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.animal_item);
            imageView.startAnimation(animalAlphaAnimation);
            adapter.animalItemListener.onItemClick(animal);
        }
    }
}
