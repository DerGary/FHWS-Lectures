package com.mobapp.garyjulius.mylectures;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.Lecture;


public class LectureDetailFragment extends Fragment {


    TextView lectureNameContent;
    TextView lecturePlaceContent;
    TextView lectureDocentsContent;
    TextView lectureDescriptionContent;

    Lecture actualLecture;

    public void LectureDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_lecture_detail,container,false);

        this.lectureNameContent = (TextView) rootView.findViewById(R.id.lectureNameContent);
        this.lecturePlaceContent = (TextView) rootView.findViewById(R.id.lecturePlaceContent);
        this.lectureDocentsContent = (TextView) rootView.findViewById(R.id.lectureDocentContent);
        this.lectureDescriptionContent = (TextView) rootView.findViewById(R.id.lectureDescTextfield);

        lectureNameContent.setText(actualLecture.get_title());
        lecturePlaceContent.setText(actualLecture.get_place().toString());
        lectureDocentsContent.setText(actualLecture.get_docents().get(0).get_name());
        lectureDescriptionContent.setText(actualLecture.get_description());

        lectureDocentsContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to docent page
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setLecture(Lecture actualLecture)
    {
        this.actualLecture = actualLecture;
    }
}
