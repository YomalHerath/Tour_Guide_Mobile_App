package Event;

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
import com.example.tour_guide.R;
import Event.addNewEvent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class admin_view_All_Events extends AppCompatActivity {
    EditText inputSearch;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    FirebaseRecyclerOptions<addNewEvent> options;
    FirebaseRecyclerAdapter<addNewEvent, EventViewHolder> adapter;

    //FireBase Reference
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view__all__events);

        floatingActionButton = findViewById(R.id.floatingBtn);
        inputSearch = findViewById(R.id.search);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addEvent.class));
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

        /*LoadData("");

        //Searching
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null) {
                    LoadData(s.toString());
                } else {
                    LoadData("");
                }
            }
        });
    }*/

    //View All the Events
    private void LoadData(String data) {

        Query query = databaseReference.orderByChild("EventName").startAt(data).endAt(data + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<addNewEvent>().setQuery(databaseReference, addNewEvent.class).build();
        adapter = new FirebaseRecyclerAdapter<addNewEvent, EventViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull final EventViewHolder holder, final int position, @NonNull final addNewEvent model) {
                /*holder.txtVEventName.setText(model.getEventName());
                holder.txtVDesc.setText(model.getDescription());*/
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);

                String EventKey =  getRef(position).getKey();

                databaseReference.child(EventKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String eventName = snapshot.child("EventName").getValue().toString();
                            String description = snapshot.child("Description").getValue().toString();

                            holder.txtVEventName.setText(eventName);
                            holder.txtVDesc.setText(description);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //Direct to the Events details screen
                holder.txtVEventName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("EventKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                //Direct to the Events details screen
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("EventKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.txtVDesc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("EventKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }
            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_items, parent, false);
                return new EventViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
