package com.example.androidassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidassignment.Adapter.AnimalRecyclerViewAdapter;
import com.example.androidassignment.JSONHelper.AnimalResourceParser;
import com.example.androidassignment.JSONHelper.JSONReader;
import com.example.androidassignment.Model.Animal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AnimalRecyclerViewAdapter.animalListener {
    RecyclerView animalView;
    ArrayList<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.animalView = findViewById(R.id.animalView);

        AnimalResourceParser animalResourceParser = new AnimalResourceParser();
        JSONReader jsonReader = new JSONReader();
        String animalsJSON = jsonReader.getJSONStringFromInputStream(getResources().openRawResource(R.raw.animals));
        this.animals = animalResourceParser.getAnimalsFromJSONString(animalsJSON);
        AnimalRecyclerViewAdapter adapter = new AnimalRecyclerViewAdapter(animals, this);
        animalView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        animalView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Animal animal) {
        Toast.makeText(getApplicationContext(), animal.name + " is clicked", Toast.LENGTH_SHORT).show();
    }
}