package com.example.tour_guide.Admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewName;
    TextView textViewDesc;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.placeImgView);
        textViewName = itemView.findViewById(R.id.place_name_textView);
        textViewDesc = itemView.findViewById(R.id.place_desc_textView);
    }
}
