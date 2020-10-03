package com.example.tour_guide.User;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class viewReviewsholder extends RecyclerView.ViewHolder {
    TextView textViewPlace,textViewDescription;


    public viewReviewsholder(@NonNull View itemView) {
        super(itemView);
        textViewPlace = itemView.findViewById(R.id.review_Place);
        textViewDescription = itemView.findViewById(R.id. review_description);

    }
}
