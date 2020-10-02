package hotel;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

class MyHotelHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textVHotelName;
    public TextView textViewDesc;
    View v;

    public MyHotelHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.HotelImgView);
        textVHotelName = itemView.findViewById(R.id.Hotel_name_textView);
        textViewDesc = itemView.findViewById(R.id.Hotel_desc_textView);
        v = itemView;
    }
}
