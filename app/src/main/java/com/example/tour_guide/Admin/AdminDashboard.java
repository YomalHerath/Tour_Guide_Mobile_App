package com.example.tour_guide.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.Event.addEvent;
import com.example.tour_guide.Event.admin_view_All_Events;
import com.example.tour_guide.R;
import com.example.tour_guide.hotel.add_hotel;
import com.example.tour_guide.hotel.admin_all_hotels;

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

    //Add Event
    public void AddEvent (View view) {
        startActivity(new Intent(getApplicationContext(), addEvent.class));
    }

    //view Event
    public void ViewEvents (View view) {
        startActivity(new Intent(getApplicationContext(), admin_view_All_Events.class));
    }

    //Add hotel Page
    public void AddHospitality(View view) {
        startActivity(new Intent(getApplicationContext(), add_hotel.class));
    }
    //View All Hotel Places
    public void ViewHospitality (View view) {
        startActivity(new Intent(getApplicationContext(), admin_all_hotels.class));
    }

}