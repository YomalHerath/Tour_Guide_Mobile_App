package com.example.tour_guide.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AdminViewPlaceDetails extends AppCompatActivity {

    ImageView imageView;
    TextView textViewName, textViewProvince, textViewDesc;
    Button delete;

    DatabaseReference ref, databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_place_details);

        imageView = findViewById(R.id.adminPlaceImg);
        textViewName = findViewById(R.id.admin_place_name_view);
        textViewDesc = findViewById(R.id.admin_place_desc_view);
        textViewProvince = findViewById(R.id.admin_province_view);
        delete = findViewById(R.id.delete);

        ref = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");

        final String PlaceKey = getIntent().getStringExtra("PlaceKey");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelPlaces").child(PlaceKey);
        storageReference = FirebaseStorage.getInstance().getReference().child("TravelPlaces").child(PlaceKey + ".jpg");

        ref.child(PlaceKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String description = snapshot.child("Description").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                    String placeName = snapshot.child("PlaceName").getValue().toString();
                    String province = snapshot.child("Province").getValue().toString();

                    Picasso.get().load(imageUrl).into(imageView);
                    textViewName.setText(placeName);
                    textViewProvince.setText(province);
                    textViewDesc.setText(description);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(), TravellingPlaces.class));
                                Toast.makeText(AdminViewPlaceDetails.this, "Delete Completed...", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                });
            }
        });
    }
}