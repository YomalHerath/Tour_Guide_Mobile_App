package com.example.tour_guide.Event;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView txtVEventName;
    TextView txtVDesc;
    View v;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.eventImgView);
        txtVEventName = itemView.findViewById(R.id.event_name_textView);
        txtVDesc = itemView.findViewById(R.id.event_desc_textView);
        v = itemView;
    }
}