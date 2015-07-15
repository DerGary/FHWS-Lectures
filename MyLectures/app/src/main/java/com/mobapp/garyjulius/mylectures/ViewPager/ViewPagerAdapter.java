package com.mobapp.garyjulius.mylectures.ViewPager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.mobapp.garyjulius.mylectures.EventRecyclerView.EventListFragment;

import java.util.List;

/**
 * Created by Stefan on 21-05-15.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<EventListFragment> _list;

    public ViewPagerAdapter(FragmentManager fm, List<EventListFragment> list) {
        super(fm);
        this._list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return _list.get(position);
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return _list.get(position).getTitle();
    }
}
