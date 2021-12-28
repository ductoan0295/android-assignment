package com.example.androidassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    private static RecyclerView animalView;
    private static GridLayoutManager manager;
    private static Animal viewingAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animalView = findViewById(R.id.animalView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getBoolean("isLikedStatusChanged")) {
            int index = AnimalRecyclerViewAdapter.animals.indexOf(viewingAnimal);
            viewingAnimal.isLiked = !viewingAnimal.isLiked;
            AnimalRecyclerViewAdapter.animals.set(index, viewingAnimal);
            viewingAnimal = null;
        } else if (adapter == null) {
            AnimalResourceParser animalResourceParser = new AnimalResourceParser();
            JSONReader jsonReader = new JSONReader();
            String animalsJSON = jsonReader.getJSONStringFromInputStream(getResources().openRawResource(R.raw.animals));
            ArrayList<Animal> animals = animalResourceParser.getAnimalsFromJSONString(animalsJSON);
            adapter = new AnimalRecyclerViewAdapter(animals, this);
        }
        animalView.setAdapter(adapter);
        manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        animalView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Animal animal, View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("animal", animal);
        intent.putExtras(bundle);
        viewingAnimal = animal;
        startActivity(intent);
    }
}