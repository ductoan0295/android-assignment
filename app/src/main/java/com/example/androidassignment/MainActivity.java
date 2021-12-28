package com.example.androidassignment;

import android.content.Intent;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView animalView = findViewById(R.id.animalView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getBoolean("isLikedStatusChanged")) {
            Animal viewingAnimal = AnimalRecyclerViewAdapter.animals.get(viewingAnimalIndex);
            viewingAnimal.isLiked = !viewingAnimal.isLiked;
            AnimalRecyclerViewAdapter.animals.set(viewingAnimalIndex, viewingAnimal);
        } else if (adapter == null) {
            AnimalResourceParser animalResourceParser = new AnimalResourceParser();
            JSONReader jsonReader = new JSONReader();
            String animalsJSON = jsonReader.getJSONStringFromInputStream(getResources().openRawResource(R.raw.animals));
            ArrayList<Animal> animals = animalResourceParser.getAnimalsFromJSONString(animalsJSON);
            adapter = new AnimalRecyclerViewAdapter(animals, this);
        }
        animalView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        animalView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Animal animal) {
        goToDetailActivity(animal);
    }

    @Override
    public void onBackPressed() {
        Animal viewingAnimal = AnimalRecyclerViewAdapter.animals.get(viewingAnimalIndex);
        goToDetailActivity(viewingAnimal);
    }

    private void goToDetailActivity(Animal animal) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("animal", animal);
        intent.putExtras(bundle);
        viewingAnimalIndex = AnimalRecyclerViewAdapter.animals.indexOf(animal);
        startActivity(intent);
    }
}