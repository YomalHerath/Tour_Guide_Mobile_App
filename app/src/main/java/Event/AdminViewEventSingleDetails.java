package Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView txtVEventName, txtVLocInfo, txtVConInfo,txtVPrice, txtVDesc;
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
        txtVConInfo = findViewById(R.id.admin_eventConInfo);
        txtVPrice = findViewById(R.id.admin_eventPrice);
        delete = findViewById(R.id.deleteBtn);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Events");
        final String EventKey = getIntent().getStringExtra("EventKey");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");
        storageReference = FirebaseStorage.getInstance().getReference().child("Events");

        dbRef.child(EventKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String eventName = snapshot.child("EventName").getValue().toString();
                    String imageUrl = snapshot.child("ImageUrl").getValue().toString();
                    String description = snapshot.child("Description").getValue().toString();
                    String province = snapshot.child("Province").getValue().toString();
                    String phone = snapshot.child("Phone").getValue().toString();
                   // String price = snapshot.child("Price").getValue().toString();

                    txtVEventName.setText(eventName);
                    Picasso.get().load(imageUrl).into(imageView);
                    txtVDesc.setText(description);
                    txtVLocInfo.setText(province);
                    txtVConInfo.setText(phone);
                   // txtVPrice.setText(price);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(),admin_view_All_Events.class));
                                Toast.makeText(AdminViewEventSingleDetails.this,"Deleted successfully !",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                });
            }
        });

    }
}