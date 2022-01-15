package com.example.androidassignment.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidassignment.Fragment.AnimalSlidePageFragment;
import com.example.androidassignment.Model.Animal;

import java.util.List;

public class AnimalSlidePagerAdapter extends FragmentStateAdapter {
    private static List<Animal> animals;

    public AnimalSlidePagerAdapter(Fragment fa, List<Animal> animals) {
        super(fa);
        AnimalSlidePagerAdapter.animals = animals;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new AnimalSlidePageFragment(animals.get(position));
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
