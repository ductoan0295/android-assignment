package com.example.androidassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidassignment.JSONHelper.AnimalResourceParser;
import com.example.androidassignment.JSONHelper.JSONReader;
import com.example.androidassignment.Model.Animal;
import com.example.androidassignment.ViewModel.AnimalViewModel;
import com.example.androidassignment.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private AnimalViewModel animalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;

        NavigationView navigationView = binding.navView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph())
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        animalViewModel = new ViewModelProvider(this).get(AnimalViewModel.class);

        binding.navView.setNavigationItemSelectedListener(item -> {
            List<Animal> animals;
            switch (item.getItemId()) {
                case (R.id.nav_mammal):
                    animals = getAnimals("Mammal");
                    animalViewModel.setAnimals(animals);
                    break;
                case (R.id.nav_ocean):
                    animals = getAnimals("Ocean");
                    animalViewModel.setAnimals(animals);
                    break;
                case (R.id.nav_bird):
                    animals = getAnimals("Bird");
                    animalViewModel.setAnimals(animals);
                    break;
            }
            navController.navigate(R.id.action_nav_home_to_nav_animal);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private List<Animal> getAnimals(String animalType) {
        AnimalResourceParser animalResourceParser = new AnimalResourceParser();
        JSONReader jsonReader = new JSONReader();
        String animalsJSON = jsonReader.getJSONStringFromInputStream(getResources().openRawResource(R.raw.animals));
        List<Animal> animals = animalResourceParser.getAnimalsFromJSONString(animalsJSON);
        return animals.stream().filter(animal -> animal.type.equals(animalType)).collect(Collectors.toList());
    }
}
