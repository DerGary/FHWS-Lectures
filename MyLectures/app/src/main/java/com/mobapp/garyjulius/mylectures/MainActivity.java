package com.mobapp.garyjulius.mylectures;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mobapp.garyjulius.mylectures.DocentRecyclerView.DocentListFragment;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.Model.DataBase;
import com.mobapp.garyjulius.mylectures.RestAsyncTasks.GetListAsyncTask;
import com.mobapp.garyjulius.mylectures.ViewPager.ViewPagerFragment;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    private ViewPagerFragment _viewPagerFragment;
    private DocentListFragment _docentListFragment;
    ArrayList<Docent> _docentList;
    ArrayList<Lecture> _lectureList;
    ArrayList<Event> _eventList;
    private DataBase _dataBase;

    private GetListAsyncTask<Docent> _getDocents = new GetListAsyncTask<Docent>(this)
    {
        @Override
        protected void onPostExecute(ArrayList<Docent> result) {
            super.onPostExecute(result);
            _docentList = result;
            _getLectures.execute("lectures");
        }
    };

    private GetListAsyncTask<Lecture> _getLectures = new GetListAsyncTask<Lecture>(this)
    {
        @Override
        protected void onPostExecute(ArrayList<Lecture> result) {
            super.onPostExecute(result);
            _lectureList = result;


            _dataBase = new DataBase(_docentList,_lectureList,_eventList);
            _viewPagerFragment.setData(_dataBase);
            getFragmentManager().beginTransaction().replace(R.id.main_layout, _viewPagerFragment).commit();
        }
    };

    private GetListAsyncTask<Event> _getEvents = new GetListAsyncTask<Event>(this)
    {
        @Override
        protected void onPostExecute(ArrayList<Event> result) {
            super.onPostExecute(result);
            _eventList = result;
            _getDocents.execute("docents");
        }
    };

    private Menu _menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //_demoData = new DemoData(getApplicationContext());
        _viewPagerFragment = new ViewPagerFragment();
        _getEvents.execute("events");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        _menu = menu;
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
        }else if(id == R.id.action_docents){
            if(_docentListFragment== null){
                _docentListFragment = new DocentListFragment();
                _docentListFragment.setData(_dataBase);
            }
            item.setVisible(false);
            MenuItem events = _menu.findItem(R.id.action_events);
            events.setVisible(true);
            changeFragment(_docentListFragment,false);
        }else if(id == R.id.action_events){
            if(_viewPagerFragment== null){
                _viewPagerFragment = new ViewPagerFragment();
                _viewPagerFragment.setData(_dataBase);
            }
            item.setVisible(false);
            MenuItem events = _menu.findItem(R.id.action_docents);
            events.setVisible(true);
            changeFragment(_viewPagerFragment,false);
        }
        return super.onOptionsItemSelected(item);
    }
    public void changeFragment(Fragment fragment, boolean addToBackStack)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
