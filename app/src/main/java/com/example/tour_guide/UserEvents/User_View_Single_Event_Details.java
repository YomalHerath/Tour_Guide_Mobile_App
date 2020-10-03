package com.example.tour_guide.UserEvents;

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

public class User_View_Single_Event_Details extends AppCompatActivity {
    TextView txtViewEventName, txtViewAboutEvent, txtViewLocEvent, txtViewConInfoEvent,txtViewPriceEvent,txtViewLocPlace, txtViewLocCity, txtViewConPersonName;
    ImageView imageViewEvent;
    DatabaseReference dBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user__view__single__event__details);

        txtViewEventName = findViewById(R.id.user_event_name_view);
        txtViewAboutEvent = findViewById(R.id.user_Event_description_view);
        txtViewLocEvent = findViewById(R.id.user_Event_locInfo_view);
        txtViewLocPlace = findViewById(R.id.user_Event_locPlaceName);
        txtViewLocCity = findViewById(R.id.user_Event_locCityName);
        txtViewConPersonName = findViewById(R.id.user_Event_PersonName);
        txtViewConInfoEvent = findViewById(R.id.user_Event_conInfo_view);
        txtViewPriceEvent = findViewById(R.id.user_Event_price_view);
        imageViewEvent = findViewById(R.id.userEventImage);

        dBRef = FirebaseDatabase.getInstance().getReference().child("Events");

        String EventKey = getIntent().getStringExtra("EventKey");

        dBRef.child(EventKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String eventNameView = snapshot.child("EventName").getValue().toString();
                    String eventImgView = snapshot.child("ImageUrl").getValue().toString();
                    String eventDescView = snapshot.child("Description").getValue().toString();
                    String eventlocView = snapshot.child("Province").getValue().toString();
                    String eventLocPlace = snapshot.child("PlaceName").getValue().toString();
                    String eventLocCity = snapshot.child("City").getValue().toString();
                    String eventConPerson = snapshot.child("PersonName").getValue().toString();
                    String eventConView = snapshot.child("Phone").getValue().toString();
                    String eventPriceView = snapshot.child("price").getValue().toString();

                    txtViewEventName.setText(eventNameView);
                    Picasso.get().load(eventImgView).into(imageViewEvent);
                    txtViewAboutEvent.setText(eventDescView);
                    txtViewLocEvent.setText(eventlocView);
                    txtViewLocPlace.setText(eventLocPlace);
                    txtViewLocCity.setText(eventLocCity);
                    txtViewConPersonName.setText(eventConPerson);
                    txtViewConInfoEvent.setText(eventConView);
                    txtViewPriceEvent.setText(eventPriceView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}