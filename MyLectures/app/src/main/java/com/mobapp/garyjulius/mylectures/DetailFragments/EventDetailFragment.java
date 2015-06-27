package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.R;

import java.text.SimpleDateFormat;


public class EventDetailFragment extends Fragment {

    TextView eventStartContent;
    TextView eventEndContent;
    TextView eventPlaceContent;
    ListView eventDocentsContent;
    TextView eventLectureContent;
    TextView eventTypeContent;

    private DataBaseSingleton dataBase;

    private Event actualEvent;

    public void EventDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBase = DataBaseSingleton.getInstance();
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_event_detail,container,false);
        eventStartContent = (TextView)rootView.findViewById(R.id.eventStartContent);
        eventEndContent = (TextView) rootView.findViewById(R.id.eventEndContent);
        eventPlaceContent = (TextView)rootView.findViewById(R.id.eventPlaceContent);
        eventDocentsContent = (ListView)rootView.findViewById(R.id.eventDocentsContent);
        eventLectureContent = (TextView)rootView.findViewById(R.id.eventLectureContent);
        eventTypeContent = (TextView)rootView.findViewById(R.id.eventTypeContent);

        setData();

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.row);

        for(int i = 0;i < actualEvent.get_docent().size(); i++)
        {
            listAdapter.add(dataBase.getDocentFromID(actualEvent.get_docent().get(i)).get_name());
        }
        eventDocentsContent.setAdapter(listAdapter);
        eventDocentsContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DocentDetailFragment docentDetailFragment = new DocentDetailFragment();
                changeToDocentFragment(docentDetailFragment, position);
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
        SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd.MM.yyyy");
        eventStartContent.setText(format.format(actualEvent.get_beginTime().getTime()));
        eventEndContent.setText(format.format(actualEvent.get_endTime().getTime()));
        eventPlaceContent.setText(actualEvent.get_room());
        eventTypeContent.setText(actualEvent.get_type().toString());
        eventLectureContent.setText(dataBase.getLectureFromId(actualEvent.get_lecture()).get_title());
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
        ((LectureDetailFragment) fragment).setLecture(dataBase.getLectureFromId(actualEvent.get_lecture()));
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }

    public void changeToDocentFragment(Fragment fragment,int position) {
        ((DocentDetailFragment) fragment).setActualDocent(dataBase.getDocentFromID(actualEvent.get_docent().get(position)));
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
