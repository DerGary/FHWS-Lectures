package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;


public class LectureDetailFragment extends Fragment {


    private TextView lectureNameContent, lecturePlaceContent, lectureDescriptionContent;
    private ListView lectureDocentsContent;


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

        lectureNameContent = (TextView) rootView.findViewById(R.id.lectureNameContent);
        lecturePlaceContent = (TextView) rootView.findViewById(R.id.lecturePlaceContent);
        lectureDocentsContent = (ListView) rootView.findViewById(R.id.lectureDocentsContent);
        lectureDescriptionContent = (TextView) rootView.findViewById(R.id.lectureDescTextfield);

        setData();

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.row);

        for(int i = 0;i < actualLecture.get_docents().size(); i++)
        {
            listAdapter.add(actualLecture.get_docents().get(i).get_name());
        }
        lectureDocentsContent.setAdapter(listAdapter);
        lectureDocentsContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DocentDetailFragment docentDetailFragment = new DocentDetailFragment();
                changeFragment(docentDetailFragment, position);
            }
        });

        return rootView;
    }

    private void setData()
    {
        lectureNameContent.setText(actualLecture.get_title());
        lecturePlaceContent.setText(actualLecture.get_place().toString());
        lectureDescriptionContent.setText(actualLecture.get_description());
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setLecture(Lecture actualLecture)
    {
        this.actualLecture = actualLecture;
    }

    public void changeFragment(Fragment fragment, int docentPosition) {
        ((DocentDetailFragment) fragment).setActualDocent(actualLecture.get_docents().get(docentPosition));
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.card_flip_in, R.animator.card_flip_out, R.animator.card_flip_in, R.animator.card_flip_out
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }
}
