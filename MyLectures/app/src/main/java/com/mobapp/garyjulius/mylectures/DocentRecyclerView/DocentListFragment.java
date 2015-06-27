package com.mobapp.garyjulius.mylectures.DocentRecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.DetailFragments.DocentDetailFragment;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.R;

import java.util.ArrayList;
import java.util.List;


public class DocentListFragment extends Fragment implements DocentClickListener {
    ArrayList<Docent> _docentsToDisplay;
    public DocentListFragment() {
        // Required empty public constructor
    }

    public void setData(DataBaseSingleton data){
        _docentsToDisplay = data.get_docentList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView listView = (RecyclerView)view.findViewById(R.id.recycler_view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerView.Adapter adapter = new DocentRecycleViewAdapter(_docentsToDisplay, this);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void OnDocentItemClick(Docent data) {
        DocentDetailFragment detailFragment = new DocentDetailFragment();
        detailFragment.setActualDocent(data);
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout,detailFragment).addToBackStack(null).commit();
    }
}
