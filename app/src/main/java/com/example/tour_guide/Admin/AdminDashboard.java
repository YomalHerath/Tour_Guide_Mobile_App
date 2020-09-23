package com.example.tour_guide.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.R;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    //Add Place Page
    public void AddPlace (View view) {
        startActivity(new Intent(getApplicationContext(), AddTravellingPlace.class));
    }

    //View All Places
    public void ViewAddedPlaces (View view) {
        startActivity(new Intent(getApplicationContext(), TravellingPlaces.class));
    }

}