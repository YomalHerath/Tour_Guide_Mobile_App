package com.example.tour_guide.UserEvents;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class MyUserView extends RecyclerView.ViewHolder{
    ImageView viewEventImage;
    TextView viewEventName_;
    View view;

    public MyUserView(@NonNull View itemView) {
        super(itemView);
        viewEventImage = itemView.findViewById(R.id.user_view_eventImg);
        viewEventName_ = itemView.findViewById(R.id.user_view_event_name);
        view = itemView;
    }
}
