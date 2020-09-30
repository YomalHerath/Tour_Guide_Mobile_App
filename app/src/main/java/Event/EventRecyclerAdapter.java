package Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import Event.EventRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.example.tour_guide.R;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context context;
    private List<addNewEvent> eventList;

    public EventRecyclerAdapter(Context context, List<addNewEvent> eventList) {
        this.context = context;
        this.eventList = eventList;
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
        addNewEvent addNewEvent = eventList.get(position);
        //TextView
        holder.textView.setText(eventList.get(position).getEventName());
        holder.textView1.setText(eventList.get(position).getDescription());

        //ImageView
        Glide.with(context)
                .load(eventList.get(position).getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView textView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.eventImgView);
            textView = itemView.findViewById(R.id.event_name_textView);
            textView1 = itemView.findViewById(R.id.event_desc_textView);
        }
    }
}
