package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Stefan on 18-05-15.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {
    private final TextView _lectureName, _lectureType, _docent, _time, _room;
    public Event event;
    private DataBaseSingleton _dataBase;

    public EventViewHolder(View itemView) {
        super(itemView);
        _lectureName = ((TextView) itemView.findViewById(R.id.event_lecture_name));
        _lectureType = ((TextView) itemView.findViewById(R.id.event_lecture_type));
        _docent = ((TextView) itemView.findViewById(R.id.event_docent));
        _time = ((TextView) itemView.findViewById(R.id.event_time));
        _room = ((TextView) itemView.findViewById(R.id.event_room));
    }

    public void assignData(Event data) {
        event = data;
        this._lectureName.setText(_dataBase.getLectureFromId(data.get_lecture()).get_title());
        this._lectureType.setText(data.get_type() + "");
        String docents = "";
        ArrayList<Docent> tempDocents = new ArrayList<Docent>();
        for(int i = 0; i < event.get_docent().size(); i++)
        {
            tempDocents.add(_dataBase.getDocentFromID(event.get_docent().get(i)));
        }
        for(Docent d :tempDocents){
            docents += d.get_name()+", ";
        }
        if(docents.length() > 2)
            docents = docents.substring(0,docents.length()-2);

        this._docent.setText(docents);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        this._time.setText(format.format(data.get_beginTime().getTime()) + " - " + format.format(data.get_endTime().getTime()));
        this._room.setText(data.get_room());
    }
}
