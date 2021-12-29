package com.example.androidassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidassignment.Adapter.AnimalRecyclerViewAdapter;
import com.example.androidassignment.JSONHelper.AnimalResourceParser;
import com.example.androidassignment.JSONHelper.JSONReader;
import com.example.androidassignment.Model.Animal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnimalRecyclerViewAdapter.animalItemListener {
    private static AnimalRecyclerViewAdapter adapter;
    private static int viewingAnimalIndex;
    private static ActivityResultLauncher<Intent> activityResultLauncher;
    private static RecyclerView animalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Animal viewingAnimal = AnimalRecyclerViewAdapter.animals.get(viewingAnimalIndex);
                        viewingAnimal.isLiked = !viewingAnimal.isLiked;
                        AnimalRecyclerViewAdapter.animals.set(viewingAnimalIndex, viewingAnimal);
                        updateDataLayout();
                    }
                });

        animalView = findViewById(R.id.animalView);

        setDataAdapter();
        updateDataLayout();
    }

    @Override
    public void onItemClick(Animal animal) {
        goToDetailActivity(animal);
    }

    private void setDataAdapter() {
        if (adapter == null) {
            AnimalResourceParser animalResourceParser = new AnimalResourceParser();
            JSONReader jsonReader = new JSONReader();
            String animalsJSON = jsonReader.getJSONStringFromInputStream(getResources().openRawResource(R.raw.animals));
            ArrayList<Animal> animals = animalResourceParser.getAnimalsFromJSONString(animalsJSON);
            adapter = new AnimalRecyclerViewAdapter(animals, this);
        }
    }

    private void updateDataLayout() {
        animalView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        animalView.setLayoutManager(manager);
    }

    private void goToDetailActivity(Animal animal) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("animal", animal);
        intent.putExtras(bundle);
        viewingAnimalIndex = AnimalRecyclerViewAdapter.animals.indexOf(animal);
        activityResultLauncher.launch(intent);
    }
}