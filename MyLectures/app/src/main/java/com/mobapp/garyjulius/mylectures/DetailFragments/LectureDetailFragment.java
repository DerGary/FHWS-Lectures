package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;
import com.mobapp.garyjulius.mylectures.SharedPreferences.SharedPrefManager;


public class LectureDetailFragment extends Fragment {
    private TextView lectureNameContent, lecturePlaceContent, lectureDescriptionContent,lectureNoteContent;
    private DataBaseSingleton dataBase;
    private Lecture actualLecture;
    private SharedPrefManager prefs;

    public void LectureDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBase = DataBaseSingleton.getInstance();
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_lecture_detail,container,false);

        prefs = new SharedPrefManager(getActivity());

        lectureNameContent = (TextView) rootView.findViewById(R.id.lectureNameContent);
        lecturePlaceContent = (TextView) rootView.findViewById(R.id.lecturePlaceContent);
        lectureDescriptionContent = (TextView) rootView.findViewById(R.id.lectureDescTextfield);
        lectureNoteContent = (TextView) rootView.findViewById(R.id.lectureNotesTextfield);

        setData();

        LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.lectureDocentLayout);

        for(int i : actualLecture.get_docents())
        {
            TextView text = (TextView) getActivity().getLayoutInflater().inflate(R.layout.text_view, null);
            final Docent docent = DataBaseSingleton.getInstance().getDocentFromID(i);
            text.setText(docent.get_name());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocentDetailFragment docentDetailFragment = new DocentDetailFragment();
                    docentDetailFragment.setActualDocent(docent);
                    changeFragment(docentDetailFragment);
                }
            });

            layout.addView(text);
        }
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.lecture_detail_title));
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.saveNote(actualLecture.get_id(),lectureNoteContent.getText().toString());
    }

    private void setData()
    {
        lectureNameContent.setText(actualLecture.get_title());
        lecturePlaceContent.setText(actualLecture.get_place().toString());
        lectureDescriptionContent.setText(actualLecture.get_description());
        lectureNoteContent.setText(prefs.getNote(actualLecture.get_id()));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setLecture(Lecture actualLecture)
    {
        this.actualLecture = actualLecture;
    }

    public void changeFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
