package com.example.tour_guide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.tour_guide.HelperClasses.AddPlace;
import com.example.tour_guide.HelperClasses.reviewAdaptor;
import com.example.tour_guide.HelperClasses.viewReviews;
import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class review_list extends AppCompatActivity {

    RecyclerView CustomerRecyclerView;
    FirebaseRecyclerOptions<viewReviews> options;
    FirebaseRecyclerAdapter<viewReviews, viewReviewsholder> adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView backBtn;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_review_list);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reviews");

        CustomerRecyclerView = findViewById(R.id.CustomerRecyclerView);
        CustomerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomerRecyclerView.setHasFixedSize(true);

        viewReviewsDetails();

        backBtn = findViewById(R.id.back_pressed);
        backBtn.setOnClickListener(new make_review.OnClickListener() {
            @Override
            public void onClick(View view) {
            review_list.super.onBackPressed(); }
            }
        );
    }

    private void viewReviewsDetails() {

        options = new FirebaseRecyclerOptions.Builder<viewReviews>().setQuery(databaseReference, viewReviews.class).build();
        adapter = new FirebaseRecyclerAdapter<viewReviews, viewReviewsholder>(options){

            @Override
            protected void onBindViewHolder(@NonNull final viewReviewsholder holder, final int position, @NonNull final viewReviews model) {

                holder.textViewPlace.setText(model.getRPlace());
                holder.textViewDescription.setText(model.getRDescription());

            }

            @NonNull
            @Override
            public viewReviewsholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewreviews_details, parent,false);
                return new viewReviewsholder(v);
            }

        };
        adapter.startListening();
        CustomerRecyclerView.setAdapter(adapter);

    }


}