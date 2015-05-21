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
    private List<EventListFragment> list;

    public ViewPagerAdapter(FragmentManager fm, List<EventListFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTitle();
    }
}
