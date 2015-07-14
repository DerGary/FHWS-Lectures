package com.mobapp.garyjulius.mylectures;

import android.app.Fragment;
import android.app.FragmentTransaction;

import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobapp.garyjulius.mylectures.AddPage.AddPageFragment;
import com.mobapp.garyjulius.mylectures.DetailFragments.EventDetailFragment;
import com.mobapp.garyjulius.mylectures.DocentRecyclerView.DocentListFragment;
import com.mobapp.garyjulius.mylectures.Helper.DateTimeSerializer;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.Notifications.BackgroundNotificationService;
import com.mobapp.garyjulius.mylectures.RestAsyncTasks.GetListAsyncTask;
import com.mobapp.garyjulius.mylectures.ViewPager.ViewPagerFragment;

import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends ActionBarActivity {
    private ViewPagerFragment _viewPagerFragment;
    private DocentListFragment _docentListFragment;
    private AddPageFragment _addPageFragment;
    ArrayList<Docent> _docentList;
    ArrayList<Lecture> _lectureList;
    ArrayList<Event> _eventList;
    MenuItem eventItem;
    MenuItem docentItem;

    private GetListAsyncTask<Docent> _getDocents = new GetListAsyncTask<Docent>(this)
    {
        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);
            _docentList = result;
            _getLectures.execute(getString(R.string.prefs_lectures));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            createPage();
        }
    };

    private GetListAsyncTask<Lecture> _getLectures = new GetListAsyncTask<Lecture>(this)
    {
        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);
            _lectureList = result;
            if(_docentList != null)
                DataBaseSingleton.getInstance().set_docentList(_docentList);
            if(_eventList != null)
                DataBaseSingleton.getInstance().set_eventList(_eventList);
            if(_lectureList != null)
                DataBaseSingleton.getInstance().set_lectureList(_lectureList);
            DataBaseSingleton.getInstance().saveDataBase(getBaseContext());
            createPage();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            createPage();
        }
    };

    private GetListAsyncTask<Event> _getEvents = new GetListAsyncTask<Event>(this)
    {
        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);
            _eventList = result;
            _getDocents.execute(getString(R.string.prefs_docents));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            createPage();
        }
    };

    private Menu _menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String lectures = "[{ \"_id\":1, \"_title\":\"Vertiefung 1: Computergrafik\", \"_responsiblePerson\":4, \"_docents\":[4], \"_language\":\"german\", \"_swh\":4, \"_type\":[\"Seminar\"], \"_creditPoints\":5, \"_examType\":[\"OralExam\"], \"_semester\":6, \"_courses\":[\"BIN\"], \"_description\":\"Die Studierenden erweitern und vertiefen ihre Kenntnisse in Richtung �Computergrafik� und erwerben die Fertigkeit zur Analyse und Strukturierung komplexer Aufgabenstellungen. Die Studierenden lernen Aufgabenstellungen aus dem Bereich �Computergrafik� zu beschreiben und zu l�sen. Im Rahmen ihrer Aufgabenstellung erwerben die Studierenden Kenntnisse zum praktischen Einsatz von Techniken und Methoden der �Computergrafik�. Die Studierenden erwerben die F�higkeit zur Realisierung performanter Computergrafik-Applikationen.\", \"_place\":\"SHL\" }, { \"_id\":2, \"_title\":\"Tontechnik und Audioprogrammierung\", \"_responsiblePerson\":4, \"_docents\":[4], \"_language\":\"german\", \"_swh\":4, \"_type\":[\"Seminar\"], \"_creditPoints\":5, \"_examType\":[\"OralExam\"], \"_semester\":6, \"_courses\":[\"BIN\", \"BWI\", \"EC\"], \"_description\":\"Die Studierenden erweitern und vertiefen ihre Kenntnisse in Richtung \\\"Tontechnik\\\" bzw. \\\"Audioprogrammierung\\\" und erwerben die Fertigkeit zur Analyse und Strukturierung komplexer Aufgabenstellungen. Die Studierenden lernen Aufgabenstellungen aus dem Bereich �Audioprogrammierung� zu beschreiben und zu l�sen. Im Rahmen ihrer Aufgabenstellung erwerben die Studierenden Kenntnisse zum praktischen Einsatz von Audio-Techniken. Die Studierenden erwerben die F�higkeit zur Realisierung performanter Audio-Applikationen.\", \"_place\":\"SHL\" }, { \"_id\":3, \"_title\":\"Mobile Applikations\", \"_responsiblePerson\":3, \"_docents\":[3,5], \"_language\":\"english\", \"_swh\":4, \"_type\":[\"Seminar\", \"LabClass\"], \"_creditPoints\":5, \"_examType\":[\"OralExam\"], \"_semester\":6, \"_courses\":[\"BIN\", \"BWI\", \"EC\"], \"_description\":\"Grundlagen der Informatik: Die Studierenden lernen die Grundlagen der Programmierung von mobilen Applikationen am Beispiel von Android kennen. Fachspezifische Vertiefungen: Die Studierenden lernen die besonderen Herausforderungen bei der Programmierung von mobilen Endger�ten kennen. Insbesondere erlernen die Studierenden die Grundlagen der Gestaltung mobiler Nutzeroberfl�chen, Konzepte der asynchronen Programmierung und vertiefen die Kenntnisse der Thread-Programmierung in Java. Fertigkeit zur Analyse und Strukturierung technischer Problemstellungen: An Beispielen erlernen die Studenten Architekturkonzepte f�r mobile L�sungen, insbesondere die Verteilung zwischen Client und Server und spezifische Kommunikationskonzepte zwischen mobilen Endger�ten. Die Studierenden erlernen die strukturierte Programmierung von Nutzeroberfl�chen auf der Basis von wiederverwendbaren SoftwareKomponenten. Kenntnisse von praxisrelevanten Aufgabenstellungen: Der Einsatz von Methoden und Techniken wird anhand von praxisrelevanten Aufgabenstellungen dargestellt und einge�bt. Die Studierenden haben ein grundlegendes Verst�ndnis zum Aufbau und zur Architektur von mobilen Applikationen. Sie sind in der Lage, eine Aufgabenstellung mit einer mobilen Applikation f�r das Betriebssystem Android zu l�sen. Insbesondere k�nnen die Studierenden mobile Nutzeroberfl�chen gestalten, Sensordaten auswerten und ein Kommunikatonsprotokoll zu enem Server implementieren.\", \"_place\":\"SHL\" } ] ";
        //String docents = "[{ \"_id\":1, \"_name\": \"Prof. Dr. Tobias Aubele\", \"_phoneNumber\": \"0931/3511-8986\", \"_faxNumber\": \"none\", \"_email\":\"tobias.aubele@fhws.de\",  \"_room\":\"I.3.42\",  \"_location\":\"SHL\",  \"_consultationHour\":\"agreement\",  \"_function\":\"Praktikantenbeauftragter EC\",  \"_picture\":\"http://www.welearn.de/typo3temp/pics/a71b033fe9.jpg\",  \"_linkWeLearn\":\"http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-tobias-aubele.html\" }, { \"_id\":2, \"_name\": \"Prof. Dr. Arndt Balzer\", \"_phoneNumber\": \"0931/3511-8362\", \"_faxNumber\": \"0931/3511-9410\", \"_email\":\"arndt.balzer@fhws.de\",  \"_room\":\"I.3.26\",  \"_location\":\"SHL\",  \"_consultationHour\":\"agreement\",  \"_function\":\"Studienfachberater/in: Bachelor Informatik\\nVerantwortlicher f�r das Vertiefungsmodul: Smart Systems (SmS)\",  \"_picture\":\"http://www.welearn.de/typo3temp/pics/349b32b4e6.jpg\",  \"_linkWeLearn\":\"http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-arndt-balzer.html\" }, { \"_id\":3, \"_name\": \"Prof. Dr. Peter Braun\", \"_phoneNumber\": \"0931/3511-8971\", \"_faxNumber\": \"none\", \"_email\":\"peter.braun@fhws.de\",  \"_room\":\"I.3.26\",  \"_location\":\"SHL\",  \"_consultationHour\":\"thursday 12-14 o'clock and agreement\",  \"_function\":\"Prodekan\\nInternationales\\nIndia-Gateway-Program (IGP)\\nVerantwortlicher f�r das Vertiefungsmodul: Smart Systems (SmS)\",  \"_picture\":\"http://www.welearn.de/typo3temp/pics/8c69900f16.jpg\",  \"_linkWeLearn\":\"http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-peter-braun.html\" }, { \"_id\":4, \"_name\": \"Prof. Dr. Frank Deinzer\", \"_phoneNumber\": \"0931/3511-7774\", \"_faxNumber\": \"none\", \"_email\":\"frank.deinzer@fhws.de\",  \"_room\":\"I.3.26\",  \"_location\":\"SHL\",  \"_consultationHour\":\"monday 14-15 o'clock\",  \"_function\":\"Verantwortlicher f�r das Vertiefungsmodul: Medieninformatik (MI)\",  \"_picture\":\"http://www.welearn.de/typo3temp/pics/517bf0a67a.jpg\",  \"_linkWeLearn\":\"http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-frank-deinzer.html\" }, { \"_id\":5, \"_name\": \"M.Sc. Vitaliy Schreibmann\", \"_phoneNumber\": \"0931/3511-8303\", \"_faxNumber\": \"none\", \"_email\":\"vitaliy.schreibmann@fhws.de\",  \"_room\":\"I.2.37\",  \"_location\":\"SHL\",  \"_consultationHour\":\"agreement\",  \"_function\":\"\",  \"_picture\":\"http://www.welearn.de/fileadmin/bildmaterial/image/dummy_profil.jpg\",  \"_linkWeLearn\":\"http://www.welearn.de/fakultaet-iw/personen/details/person/schreibmann.html\" } ]";
        //String events = "[{ \"_id\":1, \"_lecture\":3, \"_docent\":[3], \"_beginTime\":\"2015-07-08T01:01:01.000+01:00\", \"_endTime\":\"2015-07-09T02:01:01.000+01:00\", \"_type\":\"Lecture\", \"_room\":\"room\" }]";
//        Type eventType = new TypeToken<ArrayList<Event>>() {}.getType();
//        Type docentType = new TypeToken<ArrayList<Docent>>() {}.getType();
//        Type lectureType = new TypeToken<ArrayList<Lecture>>() {}.getType();
//        Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, new DateTimeSerializer()).create();
//
//        ArrayList<Event> eventList = gson.fromJson(events, eventType);
//        ArrayList<Docent> docentList = gson.fromJson(docents, docentType);
//        ArrayList<Lecture> lectureList = gson.fromJson(lectures, lectureType);
//
//        DataBaseSingleton.getInstance().set_lectureList(lectureList);
//        DataBaseSingleton.getInstance().set_docentList(docentList);
//        DataBaseSingleton.getInstance().set_eventList(eventList);
//
//        DataBaseSingleton.getInstance().saveDataBase(getBaseContext());
//        createPage();


//        LectureDetailFragment frag = new LectureDetailFragment();
//        frag.setLecture(lectureList.get(2));
//        changeFragment(frag, false);


        //_demoData = new DemoData(getApplicationContext());
        _getEvents.execute(getString(R.string.prefs_events));
//        ArrayList<Integer> testDocents = new ArrayList<Integer>();
//        testDocents.add(4);
//        Event testEvent = new Event(0,2,testDocents,new DateTime(2015, 6,27,16,0,0),
//                new DateTime(2015, 6,27,15,0,0), LectureType.Lecture,"I.2.15");
//        PostAsyncTask<Event> testEventTask = new PostAsyncTask<Event>(this);
//        testEventTask.execute(testEvent);

    }


    public void checkIntentForEventID(){
        Intent intent = new Intent( this, BackgroundNotificationService.class );
        startService( intent );

        Intent callingIntent = getIntent();
        int eventId = 0;
        if(callingIntent != null){
            eventId = callingIntent.getIntExtra(getString(R.string.callingintent_eventID),0);
        }

        if(eventId > 0 ){

            EventDetailFragment detailFragment = new EventDetailFragment();
            detailFragment.setActualEvent(DataBaseSingleton.getInstance().getEventFromId(eventId));
            changeFragment(detailFragment, true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        _menu = menu;
        eventItem = _menu.findItem(R.id.action_events);
        docentItem = _menu.findItem(R.id.action_docents);
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
                _docentListFragment.setHasOptionsMenu(true);
                _docentListFragment.setData(DataBaseSingleton.getInstance().get_docentList());
            }
            //getSupportActionBar().setTitle("Docents");
            //item.setVisible(false);
            //eventItem.setVisible(true);
            changeFragment(_docentListFragment, false);
        }else if(id == R.id.action_events){
            if(_viewPagerFragment== null){
                _viewPagerFragment = new ViewPagerFragment();
                _viewPagerFragment.setHasOptionsMenu(true);
                _viewPagerFragment.setData(getBaseContext(),DataBaseSingleton.getInstance().get_eventList());
            }
            //getSupportActionBar().setTitle("Events");
            //item.setVisible(false);
            //docentItem.setVisible(true);
            changeFragment(_viewPagerFragment,false);
        } else if(id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else if(id == R.id.action_add){
            if(_addPageFragment == null){
                _addPageFragment = new AddPageFragment();
                _addPageFragment.setHasOptionsMenu(true);
            }
            changeFragment(_addPageFragment, true);
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

    public void parseResultList(String type,ArrayList<Map> result)
    {
        //TODO
    }


    public void createPage(){
        DataBaseSingleton.getInstance().loadDataBase(getBaseContext());
        _viewPagerFragment = new ViewPagerFragment();
        _viewPagerFragment.setData(getBaseContext(),DataBaseSingleton.getInstance().get_eventList());
        getFragmentManager().beginTransaction().replace(R.id.main_layout, _viewPagerFragment).commit();
        checkIntentForEventID();
    }

    public Menu getMenu()
    {
        return _menu;
    }
}
