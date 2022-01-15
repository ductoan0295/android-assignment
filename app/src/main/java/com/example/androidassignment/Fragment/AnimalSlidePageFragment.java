package com.example.androidassignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.R;

public class AnimalSlidePageFragment extends Fragment {
    private static Animal animal;

    public AnimalSlidePageFragment(Animal animal) {
        AnimalSlidePageFragment.animal = animal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.detail_item, container, false);

        ImageView backgroundImageView = view.findViewById(R.id.animalImageView);
        TextView animalNameTextView = view.findViewById(R.id.animalNameTextView);
        // CheckBox likeToggleButton = view.findViewById(R.id.fav_toggle);
        TextView descriptionTextView = view.findViewById(R.id.animalDescription);

        backgroundImageView.setImageResource(animal.background_drawable);
        animalNameTextView.setText(animal.name);
        // likeToggleButton.setChecked(animal.isLiked);
        descriptionTextView.setText(animal.description);
        return view;
    }
}
