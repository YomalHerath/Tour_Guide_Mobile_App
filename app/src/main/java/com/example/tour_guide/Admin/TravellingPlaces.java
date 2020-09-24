package com.example.tour_guide.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.HelperClasses.AddPlace;
import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class TravellingPlaces extends AppCompatActivity {

    ImageView backBtn;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    FirebaseRecyclerOptions<AddPlace> options;
    FirebaseRecyclerAdapter<AddPlace, MyViewHolder> adapter;

    //.............................Firebase.............................
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_places);

        backBtn = findViewById(R.id.back_pressed);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");

        backBtn = findViewById(R.id.back_pressed);

        LoadData();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravellingPlaces.super.onBackPressed();
            }
        });
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<AddPlace>().setQuery(databaseReference, AddPlace.class).build();
        adapter = new FirebaseRecyclerAdapter<AddPlace, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull AddPlace model) {
                holder.textViewName.setText(model.getPlaceName());
                holder.textViewDesc.setText(model.getDescription());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TravellingPlaces.this, AdminViewPlaceDetails.class);
                        intent.putExtra("PlaceKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_travelling_place_item, parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


}