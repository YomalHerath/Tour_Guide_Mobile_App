package com.example.tour_guide.User;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class UserHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewName;
    View view;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.user_view_place_Img);
        textViewName = itemView.findViewById(R.id.user_view_place_name);
        view = itemView;
    }
}
