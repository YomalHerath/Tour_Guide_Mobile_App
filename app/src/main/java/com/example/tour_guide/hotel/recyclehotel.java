package com.example.tour_guide.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tour_guide.R;

import java.util.List;

public class recyclehotel extends RecyclerView.Adapter<recyclehotel.ViewHolder> {

        private static final String Tag = "RecyclerView";
        private Context context;
        private List<hotel> addPlaceList;

        public recyclehotel(Context context, List<hotel> addPlaceList) {
            this.context = context;
            this.addPlaceList = addPlaceList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_travelling_place_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            hotel addPlace = addPlaceList.get(position);
            //TextView
            holder.textView.setText(addPlaceList.get(position).getHotelName());
            holder.textView1.setText(addPlaceList.get(position).getDescription());

            //ImageView
            Glide.with(context)
                    .load(addPlaceList.get(position).getImageUrl())
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return addPlaceList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            TextView textView1;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.placeImgView);
                textView = itemView.findViewById(R.id.place_name_textView);
                textView1 = itemView.findViewById(R.id.place_desc_textView);
            }
        }
    }


