package hotel;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class admin_hotel_list_view extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerOptions<hotel> options;
    FirebaseRecyclerAdapter<hotel, MyViewHolder> adapter;

    //.............................Firebase.............................
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hotel_list_view);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Hotels");

        LoadData();
    }

    //View All Hotels
    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<hotel>().setQuery(databaseReference, hotel.class).build();
        adapter = new FirebaseRecyclerAdapter<hotel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull hotel model) {
                holder.textVHotelName.setText(model.getHotelName());
                holder.textViewDesc.setText(model.getDescription());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                //View  Hotels Details with Delete Button
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(admin_hotel_list_view.this, Admin_view_hotel_details.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.textViewDesc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(admin_hotel_list_view.this,Admin_view_hotel_details.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.textVHotelName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(admin_hotel_list_view.this, Admin_view_hotel_details.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hotel_view_item, parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}