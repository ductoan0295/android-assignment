package com.example.androidassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidassignment.Component.AnimalFavouriteToggle;
import com.example.androidassignment.Model.Animal;
import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity implements AnimalFavouriteToggle.animalFavoriteToggleListener {
    private static Animal animal;
    private boolean isLikedStatusChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setToolbarNavigationOnClickListener();
        setData();
    }

    @Override
    public void onBackPressed() {
        goToMainActivity();
    }

    @Override
    public void onFavouriteToggleClicked(View view) {
        CheckBox favToggle = (CheckBox) view;
        isLikedStatusChanged = favToggle.isChecked() != animal.isLiked;
    }

    private void setData() {
        ImageView backgroundImageView = findViewById(R.id.animalImageView);
        TextView animalNameTextView = findViewById(R.id.animalNameTextView);
        CheckBox likeToggleButton = findViewById(R.id.fav_toggle);
        TextView descriptionTextView = findViewById(R.id.animalDescription);

        Bundle bundle = getIntent().getExtras();
        animal = bundle.getParcelable("animal");

        backgroundImageView.setImageResource(animal.background_drawable);
        animalNameTextView.setText(animal.name);
        likeToggleButton.setChecked(animal.isLiked);
        descriptionTextView.setText(animal.description);

    }

    private void setToolbarNavigationOnClickListener() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view -> goToMainActivity());
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        if (isLikedStatusChanged) {
            setResult(Activity.RESULT_OK, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, intent);
        }
        finish();
    }
}