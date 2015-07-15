package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import java.util.List;

/**
 * Created by Stefan on 18-05-15.
 */
public class EventRecycleViewAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> _list;
    private EventClickListener _clickListener;

    public EventRecycleViewAdapter(List<Event> eventList, EventClickListener clickListener) {
        _list = eventList;
        this._clickListener = clickListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        holder.assignData(_list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _clickListener.OnEventItemClick(holder.get_data());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.cardview_event_item;
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
