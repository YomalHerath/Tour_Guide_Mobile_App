package Userhotels;

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

public class user_view_all_hotel_details extends AppCompatActivity {

    TextView textViewName, textViewProvince, textViewDesc, textViewtype, textViewphone, textViewemail;
    ImageView imageViewPlace;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_view_all_hotel_details);

        textViewName = findViewById(R.id.user_Hotel_name_view);
        textViewProvince = findViewById(R.id.user_Hotel_province_view);
        textViewDesc = findViewById(R.id.user_Hotel_desc_view);
        textViewtype = findViewById(R.id.user_hotel_type);
        textViewphone = findViewById(R.id.user_hotel_phone);
        textViewemail = findViewById(R.id.user_hotel_email);
        imageViewPlace = findViewById(R.id.userHotelImg);
        ref = FirebaseDatabase.getInstance().getReference().child("Hotels");

        String PlaceKey = getIntent().getStringExtra("PlaceKey");

        ref.child(PlaceKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String description = snapshot.child("Description").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                    String HotelName = snapshot.child("HotelName").getValue().toString();
                    String province = snapshot.child("Province").getValue().toString();
                    String type = snapshot.child("HotelType").getValue().toString();
                    String phone = snapshot.child("Phone").getValue().toString();
                    String email = snapshot.child("Email").getValue().toString();

                    Picasso.get().load(imageUrl).into(imageViewPlace);


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
    }
}