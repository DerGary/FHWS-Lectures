package com.mobapp.garyjulius.mylectures;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.Event;


public class EventDetailFragment extends Fragment {

    TextView eventStartContent;
    TextView eventEndContent;
    TextView eventPlaceContent;
    TextView eventDocentContent;
    TextView eventLectureContent;

    private Event actualEvent;

    public void EventDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_event_detail,container,false);
        this.eventStartContent = (TextView)rootView.findViewById(R.id.eventStartContent);
        this.eventEndContent = (TextView) rootView.findViewById(R.id.eventEndContent);
        this.eventPlaceContent = (TextView)rootView.findViewById(R.id.eventPlaceContent);
        this.eventDocentContent = (TextView)rootView.findViewById(R.id.eventDocentContent);
        this.eventLectureContent = (TextView)rootView.findViewById(R.id.eventLectureContent);

        setData();

        eventDocentContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to docent detail view
            }
        });
        eventLectureContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LectureDetailFragment lectureDetailFragment = new LectureDetailFragment();
                changeFragment(lectureDetailFragment);
            }
        });




        return rootView;
    }

    private void setData()
    {
        eventStartContent.setText(actualEvent.get_beginTime().getTime().toString());
        eventEndContent.setText(actualEvent.get_endTime().getTime().toString());
        eventPlaceContent.setText(actualEvent.get_room());
        eventDocentContent.setText(actualEvent.get_docent().get(0).get_name()); //Change to listView!!
        eventLectureContent.setText(actualEvent.get_lecture().get_title());
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    public Event getActualEvent() {
        return actualEvent;
    }

    public void setActualEvent(Event actualEvent) {
        this.actualEvent = actualEvent;
    }

    public void changeFragment(Fragment fragment) {
        //Testing event details
        ((LectureDetailFragment) fragment).setLecture(actualEvent.get_lecture());
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.card_flip_in, R.animator.card_flip_out, R.animator.card_flip_in, R.animator.card_flip_out
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
