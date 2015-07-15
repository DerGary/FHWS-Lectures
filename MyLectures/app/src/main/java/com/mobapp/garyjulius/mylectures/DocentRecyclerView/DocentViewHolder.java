package com.mobapp.garyjulius.mylectures.DocentRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.R;

/**
 * Created by Stefan on 18-05-15.
 */
public class DocentViewHolder extends RecyclerView.ViewHolder {
    private final TextView _name, _room;
    private Docent _data;

    public DocentViewHolder(View itemView) {
        super(itemView);
        _name = (TextView) itemView.findViewById(R.id.docent_name);
        _room = (TextView) itemView.findViewById(R.id.docent_room);
    }

    public void assignData(Docent data) {
        _data = data;
        _name.setText(data.get_name());
        _room.setText(data.get_room());
    }

    public Docent get_data() {
        return _data;
    }
}
