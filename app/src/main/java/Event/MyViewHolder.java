package Event;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView txtVEventName;
    TextView txtVDesc;
    TextView txtVPrice;
    View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.eventImgView);
        txtVEventName = itemView.findViewById(R.id.event_name_textView);
        txtVDesc = itemView.findViewById(R.id.event_desc_textView);
       // txtVPrice = itemView.findViewById(R.id.event_desc_textView);
        v = itemView;
    }
}