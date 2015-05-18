package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mobapp.garyjulius.mylectures.DemoData.DemoData;
import com.mobapp.garyjulius.mylectures.EventDetailFragment;
import com.mobapp.garyjulius.mylectures.EventRecyclerView.EventRecycleViewAdapter;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import java.util.ArrayList;


public class EventListFragment extends Fragment implements EventClickListener {

    ArrayList<Event> eventsToDisplay;

    public EventListFragment() {
        // Required empty public constructor
    }

    public void setData(DemoData data){
        eventsToDisplay = data.demoEvents;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_event_list, container, false);
        RecyclerView listView = (RecyclerView)view.findViewById(R.id.event_recycler_view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        RecyclerView.Adapter adapter = new EventRecycleViewAdapter(eventsToDisplay, this);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void OnItemClick(Event data) {
        EventDetailFragment fragment = new EventDetailFragment();
        fragment.setActualEvent(data);
        getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
