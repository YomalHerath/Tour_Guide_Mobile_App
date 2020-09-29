package com.example.tour_guide.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.HelperClasses.AddPlace;
import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class TravellingPlaces extends AppCompatActivity {

    EditText inputSearch;
    RecyclerView recyclerView;
    FloatingActionButton floatingBtn;
    FirebaseRecyclerOptions<AddPlace> options;
    FirebaseRecyclerAdapter<AddPlace, MyViewHolder> adapter;

    //.............................Firebase.............................
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_places);

        floatingBtn = findViewById(R.id.floatingBtn);
        inputSearch = findViewById(R.id.search);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),AddTravellingPlace.class));
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
                if (editable.toString() != null) {
                    LoadData(editable.toString());
                } else {
                    LoadData("");
                }
            }
        });
    }

    //View All Travelling Places
    private void LoadData(String data) {
        Query query = databaseReference.orderByChild("PlaceName").startAt(data).endAt(data + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<AddPlace>().setQuery(query, AddPlace.class).build();
        adapter = new FirebaseRecyclerAdapter<AddPlace, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull AddPlace model) {
                holder.textViewName.setText(model.getPlaceName());
                holder.textViewDesc.setText(model.getDescription());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                //View Place Details with Delete Button
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TravellingPlaces.this, AdminViewPlaceDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.textViewDesc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TravellingPlaces.this, AdminViewPlaceDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.textViewName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(TravellingPlaces.this, AdminViewPlaceDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
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
}