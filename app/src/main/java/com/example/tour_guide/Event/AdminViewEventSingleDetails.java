package com.example.tour_guide.Event;

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

public class AdminViewEventSingleDetails extends AppCompatActivity {
    ImageView imageView;
    TextView txtVEventName, txtVLocInfo, txtVConInfo,txtVPrice, txtVDesc, txtVLocPlaceName, txtVLocCity, txtVConPersonName;
    Button delete;

    DatabaseReference databaseReference, dbRef;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_event_single_details);

        txtVEventName = findViewById(R.id.admin_event_name_view);
        imageView = findViewById(R.id.adminEventImg);
        txtVDesc = findViewById(R.id.descriptionView);
        txtVLocInfo = findViewById(R.id.admin_eventLocInfo);
        txtVLocPlaceName = findViewById(R.id.admin_event_locPlaceName);
        txtVLocCity = findViewById(R.id.admin_eventLocCityName);
        txtVConPersonName = findViewById(R.id.admin_eventConPersonName);
        txtVConInfo = findViewById(R.id.admin_eventConInfo);
        txtVPrice = findViewById(R.id.admin_eventPrice);
        delete = findViewById(R.id.delete);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Events");
        final String EventKey = getIntent().getStringExtra("EventKey");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events").child(EventKey);
        storageReference = FirebaseStorage.getInstance().getReference().child("Events").child(EventKey + ".jpg");

        dbRef.child(EventKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String eventName = snapshot.child("EventName").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                    String description = snapshot.child("Description").getValue().toString();
                    String placeName = snapshot.child("PlaceName").getValue().toString();
                    String cityName = snapshot.child("City").getValue().toString();
                    String province = snapshot.child("Province").getValue().toString();
                    String personName = snapshot.child("PersonName").getValue().toString();
                    String phone = snapshot.child("Phone").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();

                    txtVEventName.setText(eventName);
                    Picasso.get().load(imageUrl).into(imageView);
                    txtVDesc.setText(description);
                    txtVLocInfo.setText(province);
                    txtVLocPlaceName.setText(placeName);
                    txtVLocCity.setText(cityName);
                    txtVConPersonName.setText(personName);
                    txtVConInfo.setText(phone);
                    txtVPrice.setText(price);
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
                                startActivity(new Intent(getApplicationContext(), admin_view_All_Events.class));
                                Toast.makeText(AdminViewEventSingleDetails.this, "Delete Completed...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

    }
}