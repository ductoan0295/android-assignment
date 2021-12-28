package com.example.androidassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidassignment.Component.AnimalFavouriteToggle;
import com.example.androidassignment.Model.Animal;

public class DetailActivity extends AppCompatActivity implements AnimalFavouriteToggle.animalFavoriteToggleListener {
    private static Animal animal;
    private boolean isLikedStatusChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView backgroundImageView = findViewById(R.id.animalImageView);
        TextView animalNameTextView = findViewById(R.id.animalNameTextView);
        CheckBox likeToggleButton = findViewById(R.id.fav_toggle);
        TextView descriptionTextView = findViewById(R.id.animalDescription);

        Bundle b = getIntent().getExtras();
        animal = b.getParcelable("animal");

        backgroundImageView.setImageResource(animal.background_drawable);
        animalNameTextView.setText(animal.name);
        likeToggleButton.setChecked(animal.isLiked);
        descriptionTextView.setText(animal.description);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            goToMainActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isLikedStatusChanged", isLikedStatusChanged);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }
}