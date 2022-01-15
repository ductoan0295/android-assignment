package com.example.androidassignment.JSONHelper;


import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.ResourceHelper.DrawableRetriever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnimalResourceParser {
    public List<Animal> getAnimalsFromJSONString(String jsonInput) {
        List<Animal> animals = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
            JSONArray jsonArray = jsonObject.getJSONArray("animals");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject animalObject = jsonArray.getJSONObject(i);
                DrawableRetriever drawableRetriever = new DrawableRetriever();
                String name = animalObject.getString("name");
                int iconResourceID = drawableRetriever.getIDByName(animalObject.getString("icon"));
                int backgroundResourceID = drawableRetriever.getIDByName(animalObject.getString("background"));
                String description = animalObject.getString("description");
                String type = animalObject.getString("type");
                animals.add(new Animal(name, iconResourceID, backgroundResourceID, description, false, type));
            }
        } catch (JSONException e) {
            return animals;
        }
        return animals;
    }
}
