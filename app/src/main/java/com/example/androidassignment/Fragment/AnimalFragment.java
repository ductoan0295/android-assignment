package com.example.androidassignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Adapter.AnimalRecyclerViewAdapter;
import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.R;
import com.example.androidassignment.ViewModel.AnimalViewModel;
import com.example.androidassignment.databinding.FragmentAnimalBinding;

public class AnimalFragment extends Fragment implements AnimalRecyclerViewAdapter.animalItemListener {
    private static AnimalRecyclerViewAdapter adapter;
    private AnimalViewModel animalViewModel;
    private FragmentAnimalBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        animalViewModel.getAnimalLiveData().observe(getViewLifecycleOwner(), animals -> adapter.updateAnimalList(animals));

        binding = FragmentAnimalBinding.inflate(inflater, container, false);
        RecyclerView animalView = binding.animalView;
        animalView.setLayoutManager(new GridLayoutManager(this.getActivity(), 3, GridLayoutManager.VERTICAL, false));
        adapter = new AnimalRecyclerViewAdapter(animalViewModel.getAnimals(), this);
        animalView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Animal animal) {
        goToDetail(animal);
    }

    private void goToDetail(Animal animal) {
        this.animalViewModel.setViewingAnimalIndex(animal);
        this.navController.navigate(R.id.action_nav_animal_to_nav_detail);
    }
}