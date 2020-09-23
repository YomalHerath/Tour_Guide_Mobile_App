package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tour_guide.Admin.TravellingPlaces;
import com.example.tour_guide.R;

public class TravellingPlacesCategories extends AppCompatActivity {

    private ImageView backBtn;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_travelling_places_categories);

        //Hooks
        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravellingPlacesCategories.super.onBackPressed();
            }
        });

    }

    //View All Historical Places
    public void expandHistoricalPlaces(View view) {
        startActivity(new Intent(getApplicationContext(), TravellingPlaces.class));
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}