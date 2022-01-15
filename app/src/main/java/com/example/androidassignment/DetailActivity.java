//package com.example.androidassignment;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.CheckBox;
//
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.androidassignment.Adapter.AnimalSlidePagerAdapter;
//import com.example.androidassignment.Component.AnimalFavouriteToggle;
//import com.example.androidassignment.Model.Animal;
//import com.google.android.material.appbar.MaterialToolbar;
//
//import java.util.List;
//
//public class DetailActivity extends FragmentActivity implements AnimalFavouriteToggle.animalFavoriteToggleListener {
//    private static int viewingAnimalIndex;
//    private ViewPager2 viewPager;
//    private FragmentStateAdapter pagerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        setToolbarNavigationOnClickListener();
//
//        Bundle bundle = getIntent().getExtras();
//        List<Animal> animals = bundle.getParcelableArrayList("animals");
//        viewingAnimalIndex = bundle.getInt("viewingAnimalIndex");
//
//        viewPager = findViewById(R.id.animalPager);
//        pagerAdapter = new AnimalSlidePagerAdapter(this, animals);
//        viewPager.setAdapter(pagerAdapter);
//        viewPager.setCurrentItem(viewingAnimalIndex);
//    }
//
//    @Override
//    public void onBackPressed() {
//        goToMainActivity();
//    }
//
//    @Override
//    public void onFavouriteToggleClicked(View view) {
//        CheckBox favToggle = (CheckBox) view;
//        // isLikedStatusChanged = favToggle.isChecked() != animal.isLiked;
//    }
//
//    private void setToolbarNavigationOnClickListener() {
//        MaterialToolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(view -> goToMainActivity());
//    }
//
//    private void goToMainActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
////        if (isLikedStatusChanged) {
////            setResult(Activity.RESULT_OK, intent);
////        } else {
////            setResult(Activity.RESULT_CANCELED, intent);
////        }
//        setResult(Activity.RESULT_CANCELED, intent);
//        finish();
//    }
//}
//
//
