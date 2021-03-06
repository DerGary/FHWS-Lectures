package com.mobapp.garyjulius.mylectures.DocentRecyclerView;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.DetailFragments.DocentDetailFragment;
import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.R;

import java.util.ArrayList;


public class DocentListFragment extends Fragment implements DocentClickListener {
    private ArrayList<Docent> _docentsToDisplay;

    public DocentListFragment() {
        // Required empty public constructor
    }

    public void setData(ArrayList<Docent> data) {
        _docentsToDisplay = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.docents_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView listView = (RecyclerView) view.findViewById(R.id.recycler_view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecyclerView.Adapter adapter = new DocentRecycleViewAdapter(_docentsToDisplay, this);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void OnDocentItemClick(Docent data) {
        DocentDetailFragment detailFragment = new DocentDetailFragment();
        detailFragment.setHasOptionsMenu(true);
        detailFragment.set_actualDocent(data);
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, detailFragment).addToBackStack(null).commit();
    }
}
