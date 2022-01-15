package com.example.androidassignment.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidassignment.Model.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalViewModel extends ViewModel {

    private MutableLiveData<List<Animal>> animalsLivedData;
    private List<Animal> animals;
    private int viewingAnimalIndex;

    public List<Animal> getAnimals() {
        if (this.animals == null) {
            this.animals = new ArrayList<>();
        }
        return this.animals;
    }

    public void setAnimals(List<Animal> animals) {
        if (this.animalsLivedData == null) {
            this.animalsLivedData = new MutableLiveData<>();
        }
        this.animals = animals;
        this.animalsLivedData.setValue(this.animals);
    }

    public LiveData<List<Animal>> getAnimalLiveData() {
        if (this.animalsLivedData == null) {
            this.animalsLivedData = new MutableLiveData<>();
        }
        return animalsLivedData;
    }

    public int getViewingAnimalIndex() {
        return this.viewingAnimalIndex;
    }

    public void setViewingAnimalIndex(Animal animal) {
        this.viewingAnimalIndex = animals.indexOf(animal);
    }
}