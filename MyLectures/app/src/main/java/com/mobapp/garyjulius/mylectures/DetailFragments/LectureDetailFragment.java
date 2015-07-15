package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;
import com.mobapp.garyjulius.mylectures.SharedPreferences.SharedPrefManager;


public class LectureDetailFragment extends Fragment {
    private TextView _lectureNameContent, _lecturePlaceContent, _lectureDescriptionContent, _lectureNoteContent;
    private Lecture _actualLecture;
    private SharedPrefManager _prefs;

    public void LectureDetailFragment() {
        //Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_lecture_detail, container, false);

        _prefs = new SharedPrefManager(getActivity());

        _lectureNameContent = (TextView) rootView.findViewById(R.id.lectureNameContent);
        _lecturePlaceContent = (TextView) rootView.findViewById(R.id.lecturePlaceContent);
        _lectureDescriptionContent = (TextView) rootView.findViewById(R.id.lectureDescTextfield);
        _lectureNoteContent = (TextView) rootView.findViewById(R.id.lectureNotesTextfield);

        setData();

        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.lectureDocentLayout);

        for (int i : _actualLecture.get_docents()) {
            TextView text = (TextView) getActivity().getLayoutInflater().inflate(R.layout.text_view, null);
            final Docent docent = DataBaseSingleton.getInstance().getDocentFromID(i);
            text.setText(docent.get_name());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocentDetailFragment docentDetailFragment = new DocentDetailFragment();
                    docentDetailFragment.set_actualDocent(docent);
                    changeFragment(docentDetailFragment);
                }
            });

            layout.addView(text);
        }
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.lecture_detail_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        _prefs.saveNote(_actualLecture.get_id(), _lectureNoteContent.getText().toString());
    }

    private void setData() {
        _lectureNameContent.setText(_actualLecture.get_title());
        _lecturePlaceContent.setText(_actualLecture.get_place().toString());
        _lectureDescriptionContent.setText(_actualLecture.get_description());
        _lectureNoteContent.setText(_prefs.getNote(_actualLecture.get_id()));
    }

    public void setLecture(Lecture actualLecture) {
        this._actualLecture = actualLecture;
    }

    public void changeFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
