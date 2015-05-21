package com.mobapp.garyjulius.mylectures.ViewPager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobapp.garyjulius.mylectures.DemoData.DemoData;
import com.mobapp.garyjulius.mylectures.EventRecyclerView.EventListFragment;
import com.mobapp.garyjulius.mylectures.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Stefan on 21-05-15.
 */
public class ViewPagerFragment extends Fragment {
    private ViewPager _pager;
    private ViewPagerAdapter _pagerAdapter;
    private View _view;
    private List<EventListFragment> _list;
    private DemoData _data;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public void setData(DemoData data){
        this._data = data;
        _list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            EventListFragment eventListFragment = new EventListFragment();
            GregorianCalendar cal = (GregorianCalendar)GregorianCalendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR,i);
            eventListFragment.setData(_data, cal);
            _list.add(eventListFragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(_view != null){
            return _view;
        }
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        _pager = (ViewPager) view.findViewById(R.id.pager);
        _pagerAdapter = new ViewPagerAdapter(getFragmentManager(), _list);
        _pager.setAdapter(_pagerAdapter);
        _view = view;
        return view;
    }
}
