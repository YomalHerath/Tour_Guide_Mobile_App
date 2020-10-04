package UserEvents;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Event.addNewEvent;
import UserEvents.MyUserView;


public class User_View_All_Events extends AppCompatActivity {
    EditText search;
    ImageView backBtn;
    DrawerLayout drawerLayout;
    RecyclerView eventRecyclerView;
    FirebaseRecyclerOptions<addNewEvent> options;
    FirebaseRecyclerAdapter<addNewEvent, MyUserView> adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user__view__all__events);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        search = findViewById(R.id.searchBtn);
        eventRecyclerView = findViewById(R.id.UserEventRecyclerView);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventRecyclerView.setHasFixedSize(true);

        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_View_All_Events.super.onBackPressed();
            }
        });

        LoadData("");
        //User Search Events
        search.addTextChangedListener(new TextWatcher() {
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

    //View All Events
    private void LoadData(String data) {
        Query query = databaseReference.orderByChild("EventName").startAt(data).endAt(data + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<addNewEvent>().setQuery(query, addNewEvent.class).build();
        adapter = new FirebaseRecyclerAdapter<addNewEvent, MyUserView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyUserView holder, final int position, @NonNull addNewEvent model) {
                holder.viewEventName_.setText(model.getEventName());
                Picasso.get().load(model.getImageUrl()).into(holder.viewEventImage);

                String EventKey =  getRef(position).getKey();

                databaseReference.child(EventKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String eventNameView = snapshot.child("EventName").getValue().toString();
                            holder.viewEventName_.setText(eventNameView);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                holder.viewEventImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(User_View_All_Events.this, User_View_Single_Event_Details.class);
                        intent.putExtra("EventKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.viewEventName_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(User_View_All_Events.this, User_View_Single_Event_Details.class);
                        intent.putExtra("EventKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public MyUserView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view_card_event, parent, false);
                return new MyUserView(view);
            }
        };
        adapter.startListening();
        eventRecyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}
