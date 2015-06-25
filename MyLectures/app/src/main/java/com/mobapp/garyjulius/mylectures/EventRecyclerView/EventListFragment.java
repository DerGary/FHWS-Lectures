package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.DetailFragments.EventDetailFragment;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.DataBase;
import com.mobapp.garyjulius.mylectures.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class EventListFragment extends Fragment implements EventClickListener {
    private ArrayList<Event> _eventsToDisplay;
    private GregorianCalendar _calendar;

    public EventListFragment() {
        // Required empty public constructor
    }

    public void setData(DataBase data, GregorianCalendar calendar){
        _eventsToDisplay = new ArrayList<>();
        this._calendar = calendar;
        for(Event e : data.get_eventList()){
            GregorianCalendar begintime = e.get_beginTime();
            GregorianCalendar endtime = e.get_endTime();
            if(begintime.get(Calendar.DAY_OF_YEAR) <= calendar.get(Calendar.DAY_OF_YEAR)
                && begintime.get(Calendar.YEAR) <= calendar.get(Calendar.YEAR)
                && endtime.get(Calendar.DAY_OF_YEAR) >= calendar.get(Calendar.DAY_OF_YEAR)
                && endtime.get(Calendar.YEAR) >= calendar.get(Calendar.DAY_OF_YEAR))
            {
                _eventsToDisplay.add(e);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView listView = (RecyclerView)view.findViewById(R.id.recycler_view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerView.Adapter adapter = new EventRecycleViewAdapter(_eventsToDisplay, this);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void OnEventItemClick(Event data) {
        EventDetailFragment detailFragment = new EventDetailFragment();
        detailFragment.setActualEvent(data);
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout,detailFragment).addToBackStack(null).commit();
    }

    public String getTitle(){
        GregorianCalendar time = (GregorianCalendar)GregorianCalendar.getInstance();
        if(time.get(Calendar.DAY_OF_YEAR) == _calendar.get(Calendar.DAY_OF_YEAR) && time.get(Calendar.YEAR) == _calendar.get(Calendar.YEAR)){
            //today
            SimpleDateFormat format = new SimpleDateFormat("dd. MMMM");
            return "Heute, "+ format.format(_calendar.getTime());
        }else if(time.get(Calendar.DAY_OF_YEAR)+1 == _calendar.get(Calendar.DAY_OF_YEAR) && time.get(Calendar.YEAR) == _calendar.get(Calendar.YEAR)){
            //tomorrow
            SimpleDateFormat format = new SimpleDateFormat("dd. MMMM");
            return "Morgen, "+ format.format(_calendar.getTime());
        }
        SimpleDateFormat format = new SimpleDateFormat("EE, dd. MMMM");
        return format.format(_calendar.getTime());
    }
}
