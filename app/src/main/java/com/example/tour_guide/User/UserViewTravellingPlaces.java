package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class UserViewTravellingPlaces extends AppCompatActivity {

    EditText inputSearch;
    ImageView backBtn;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    FirebaseRecyclerOptions<AddPlace> options;
    FirebaseRecyclerAdapter<AddPlace, UserHolder>adapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_travelling_places);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");
        inputSearch = findViewById(R.id.search);
        recyclerView = findViewById(R.id.UserRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserViewTravellingPlaces.super.onBackPressed();
            }
        });

        LoadData("");

        //Searching Part
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString()!=null){
                    LoadData(editable.toString());
                } else {
                    LoadData("");
                }
            }
        });
    }

    private void LoadData(String data) {
        Query query = databaseReference.orderByChild("PlaceName").startAt(data).endAt(data + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<AddPlace>().setQuery(query, AddPlace.class).build();
        adapter = new FirebaseRecyclerAdapter<AddPlace, UserHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserHolder holder, final int position, @NonNull AddPlace model) {
                holder.textViewName.setText(model.getPlaceName());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserViewTravellingPlaces.this, UserViewTravellingPlaceDetails.class);
                        intent.putExtra("PlaceKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
                holder.textViewName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserViewTravellingPlaces.this, UserViewTravellingPlaceDetails.class);
                        intent.putExtra("PlaceKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_view_place_item,parent,false);
                return new UserHolder(v);
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
