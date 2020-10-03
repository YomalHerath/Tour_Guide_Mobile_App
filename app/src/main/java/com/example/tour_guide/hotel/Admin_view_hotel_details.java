package com.example.tour_guide.hotel;
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

public class Admin_view_hotel_details extends AppCompatActivity {

        ImageView imageView;
        TextView textViewName, textViewProvince, textViewDesc ,textViewtype ,textViewphone ,textViewemail;
        Button delete;

        DatabaseReference ref, databaseReference;
        StorageReference storageReference;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_view_hotel_details);

            imageView = findViewById(R.id.adminhotelImg);
            textViewName = findViewById(R.id.admin_hotel_name_view);
            textViewDesc = findViewById(R.id.admin_hotel_desc_view);
            textViewProvince = findViewById(R.id.admin_province_view);
            textViewtype = findViewById(R.id.admin_hotel_type);
            textViewphone = findViewById(R.id.admin_hotel_phone);
            textViewemail =findViewById(R.id.admin_hotel_email);

            delete = findViewById(R.id.delete);

            ref = FirebaseDatabase.getInstance().getReference().child("Hotels");

            String PlaceKey = getIntent().getStringExtra("PlaceKey");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Hotels").child(PlaceKey);
            storageReference = FirebaseStorage.getInstance().getReference().child("Hotels").child(PlaceKey + ".jpg");

            ref.child(PlaceKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String description = snapshot.child("Description").getValue().toString();
                        String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                        String HotelName = snapshot.child("HotelName").getValue().toString();
                        String province = snapshot.child("Province").getValue().toString();
                        String type =snapshot.child("HotelType").getValue().toString();
                        String phone =snapshot.child("Phone").getValue().toString();
                        String email =snapshot.child("Email").getValue().toString();


                        Picasso.get().load(imageUrl).into(imageView);
                        textViewName.setText(HotelName);
                        textViewProvince.setText(province);
                        textViewDesc.setText(description);
                        textViewtype.setText(type);
                        textViewphone.setText(phone);
                        textViewemail.setText(email);
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
                                    startActivity(new Intent(getApplicationContext(), admin_hotel_list_view.class));
                                    Toast.makeText(Admin_view_hotel_details.this, "Delete Completed...", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            });

        }
    }