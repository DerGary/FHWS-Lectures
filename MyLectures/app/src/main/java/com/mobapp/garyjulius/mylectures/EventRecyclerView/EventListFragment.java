package com.mobapp.garyjulius.mylectures.EventRecyclerView;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.DetailFragments.EventDetailFragment;
import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class EventListFragment extends Fragment implements EventClickListener {

    private static final String TAG = "EventListFragment";
    private ArrayList<Event> _eventsToDisplay;
    private GregorianCalendar _calendar;
    private Context context;

    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, getResources().getString(R.string.log_onStart_called));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, getResources().getString(R.string.log_onDestroy_called));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.events_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(true);
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
    }

    public void setData(List<Event> events, GregorianCalendar calendar){
        _eventsToDisplay = new ArrayList<>();
        this._calendar = calendar;
        for(Event e : events){
            DateTime beginTime = e.getBeginDateTime();
            DateTime endTime = e.getEndDateTime();

            if(beginTime.get(DateTimeFieldType.dayOfYear()) <= calendar.get(Calendar.DAY_OF_YEAR)
                && beginTime.get(DateTimeFieldType.year()) <= calendar.get(Calendar.YEAR)
                && endTime.get(DateTimeFieldType.dayOfYear()) >= calendar.get(Calendar.DAY_OF_YEAR)
                && endTime.get(DateTimeFieldType.year()) >= calendar.get(Calendar.DAY_OF_YEAR))
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
        ).replace(R.id.main_layout, detailFragment).addToBackStack(null).commit();

        //((MainActivity)getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        //((MainActivity)getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
    }

    public String getTitle(){
        GregorianCalendar time = (GregorianCalendar)GregorianCalendar.getInstance();
        if(time.get(Calendar.DAY_OF_YEAR) == _calendar.get(Calendar.DAY_OF_YEAR) && time.get(Calendar.YEAR) == _calendar.get(Calendar.YEAR)){
            //today
            SimpleDateFormat format = new SimpleDateFormat("dd. MMMM");
            return context.getString(R.string.heute) + format.format(_calendar.getTime());
        }else if(time.get(Calendar.DAY_OF_YEAR)+1 == _calendar.get(Calendar.DAY_OF_YEAR) && time.get(Calendar.YEAR) == _calendar.get(Calendar.YEAR)){
            //tomorrow
            SimpleDateFormat format = new SimpleDateFormat("dd. MMMM");
            return context.getString(R.string.morgen) + format.format(_calendar.getTime());
        }
        SimpleDateFormat format = new SimpleDateFormat("EE, dd. MMMM");
        return format.format(_calendar.getTime());
    }

    public void setContext(Context context)
    {
        this.context = context;
    }
}
