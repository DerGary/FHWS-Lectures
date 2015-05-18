package com.mobapp.garyjulius.mylectures;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mobapp.garyjulius.mylectures.DemoData.DemoData;
import com.mobapp.garyjulius.mylectures.EventRecyclerView.EventClickListener;
import com.mobapp.garyjulius.mylectures.EventRecyclerView.EventListFragment;
import com.mobapp.garyjulius.mylectures.Model.Event;

public class MainActivity extends ActionBarActivity {
    private EventListFragment _eventListFragment;

    DemoData demoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoData = new DemoData(getApplicationContext());
        _eventListFragment = new EventListFragment();
        _eventListFragment.setData(demoData);
        getFragmentManager().beginTransaction().replace(R.id.main_layout, _eventListFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void changeFragment(Fragment fragment)
    {
        //Testing event details
        ((EventDetailFragment)fragment).setActualEvent(demoData.demoEvents.get(0));
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.card_flip_in, R.animator.card_flip_out, R.animator.card_flip_in, R.animator.card_flip_out
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
