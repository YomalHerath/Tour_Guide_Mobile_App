package com.example.tour_guide.User;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserViewTravellingPlaceDetails extends AppCompatActivity {

    TextView textViewName, textViewProvince, textViewDesc;
    ImageView imageViewPlace;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_view_travelling_place_details);

        textViewName = findViewById(R.id.user_place_name_view);
        textViewProvince = findViewById(R.id.user_province_view);
        textViewDesc = findViewById(R.id.user_place_desc_view);
        imageViewPlace = findViewById(R.id.userPlaceImg);
        ref = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");

        String PlaceKey = getIntent().getStringExtra("PlaceKey");

        ref.child(PlaceKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String description = snapshot.child("Description").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                    String placeName = snapshot.child("PlaceName").getValue().toString();
                    String province = snapshot.child("Province").getValue().toString();

                    Picasso.get().load(imageUrl).into(imageViewPlace);
                    textViewName.setText(placeName);
                    textViewProvince.setText(province);
                    textViewDesc.setText(description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}