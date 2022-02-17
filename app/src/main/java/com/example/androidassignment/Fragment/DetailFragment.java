package com.example.androidassignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidassignment.Adapter.AnimalSlidePagerAdapter;
import com.example.androidassignment.ViewModel.AnimalViewModel;
import com.example.androidassignment.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    private AnimalViewModel animalViewModel;
    private FragmentDetailBinding binding;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        viewPager = binding.animalPager;

        pagerAdapter = new AnimalSlidePagerAdapter(this, animalViewModel.getAnimals());
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                animalViewModel.setViewingAnimalIndexByPagerPosition(position);
            }
        });
        viewPager.setCurrentItem(animalViewModel.getViewingAnimalIndex());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}