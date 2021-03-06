package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Created by Stefan on 18-05-15.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {
    private final TextView _lectureName, _lectureType, _docent, _time, _room;
    private Event _data;

    public EventViewHolder(View itemView) {
        super(itemView);
        _lectureName = ((TextView) itemView.findViewById(R.id.event_lecture_name));
        _lectureType = ((TextView) itemView.findViewById(R.id.event_lecture_type));
        _docent = ((TextView) itemView.findViewById(R.id.event_docent));
        _time = ((TextView) itemView.findViewById(R.id.event_time));
        _room = ((TextView) itemView.findViewById(R.id.event_room));
    }

    public void assignData(Event data) {
        _data = data;
        this._lectureName.setText(DataBaseSingleton.getInstance().getLectureFromId(data.get_lecture()).get_title());
        this._lectureType.setText(data.get_type().toString());

        String docents = "";
        ArrayList<Docent> tempDocents = new ArrayList<Docent>();

        for(int i = 0; i < data.get_docent().size(); i++)
        {
            tempDocents.add(DataBaseSingleton.getInstance().getDocentFromID(data.get_docent().get(i)));
        }

        for(Docent d :tempDocents){
            docents += d.get_name()+", ";
        }

        if(docents.length() > 2)
            docents = docents.substring(0,docents.length()-2);

        this._docent.setText(docents);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
        DateTime beginTime = data.getBeginDateTime();
        DateTime endTime = data.getEndDateTime();

        this._time.setText(fmt.print(beginTime) + " - " + fmt.print(endTime));
        this._room.setText(data.get_room());
    }

    public Event get_data() {
        return _data;
    }

}
