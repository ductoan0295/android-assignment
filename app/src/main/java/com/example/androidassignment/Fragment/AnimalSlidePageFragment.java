package com.example.androidassignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.ViewModel.AnimalViewModel;
import com.example.androidassignment.databinding.DetailItemBinding;

public class AnimalSlidePageFragment extends Fragment {
    private static Animal animal;
    private AnimalViewModel animalViewModel;
    private DetailItemBinding binding;
    private ImageView backgroundImageView;
    private TextView animalNameTextView;
    private CheckBox likeToggleButton;
    private TextView descriptionTextView;

    public AnimalSlidePageFragment(Animal animal) {
        AnimalSlidePageFragment.animal = animal;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DetailItemBinding.inflate(inflater, container, false);
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);

        this.backgroundImageView = binding.animalImageView;
        this.animalNameTextView = binding.animalNameTextView;
        this.likeToggleButton = binding.favToggle;
        this.descriptionTextView = binding.animalDescription;

        this.likeToggleButton.setOnClickListener(view -> this.onFavouriteToggleClicked());

        this.backgroundImageView.setImageResource(animal.background_drawable);
        this.animalNameTextView.setText(animal.name);
        this.likeToggleButton.setChecked(animal.isLiked);
        this.descriptionTextView.setText(animal.description);
        return binding.getRoot();
    }

    public void onFavouriteToggleClicked() {
        animalViewModel.setViewingAnimalLikedStatus();
    }
}
