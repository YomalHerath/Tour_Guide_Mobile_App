package com.example.tour_guide.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public  class reviewAdaptor extends RecyclerView.Adapter<reviewAdaptor.ViewReviewsholder>{

    private static final String Tag = "RecyclerView";
    private Context context;
    private List<viewReviews> addReviewsList;

    public reviewAdaptor(Context context, List<viewReviews> addReviewsList) {
        this.context = context;
        this.addReviewsList = addReviewsList;
    }


    @Override
    public ViewReviewsholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewreviews_details, parent, false);
        ViewReviewsholder viewHolder = new ViewReviewsholder(view);

        return new ViewReviewsholder(view);
    }

    @Override
    public void onBindViewHolder(ViewReviewsholder holder, int position) {
        viewReviews reviews = addReviewsList.get(position);

        holder.textViewPlace.setText(addReviewsList.get(position).getRPlace());
        holder.textViewDescription.setText(addReviewsList.get(position).getRDescription());
    }

    @Override
    public int getItemCount() {
            return addReviewsList.size();
    }

    public static class ViewReviewsholder extends RecyclerView.ViewHolder {

        TextView textViewPlace;
        TextView textViewDescription;

        public ViewReviewsholder(@NonNull View itemView) {
            super(itemView);
            textViewPlace = itemView.findViewById(R.id.review_Place);
            textViewDescription = itemView.findViewById(R.id.review_description);
        }
    }
}
//