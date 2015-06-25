package com.mobapp.garyjulius.mylectures.DemoData;

import android.content.Context;

import com.mobapp.garyjulius.mylectures.Model.Course;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.ExamType;
import com.mobapp.garyjulius.mylectures.Model.Language;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.Model.LectureType;
import com.mobapp.garyjulius.mylectures.Model.Place;
import com.mobapp.garyjulius.mylectures.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Julius on 11.05.2015.
 */
public class DemoData {
    /*public ArrayList<Docent> demoDocents;
    public ArrayList<Lecture> demoLectures;
    public ArrayList<Event> demoEvents;
    Context context;
    int docentId = 0;
    public DemoData(Context context){
        this.context = context;
        demoDocents = new ArrayList<>();
        demoLectures = new ArrayList<>();
        demoEvents = new ArrayList<Event>();
        addDemoDocents();
        addDemoLectures();
        addDemoEvents();
    }

    private void addDemoDocents() {
        try {
            demoDocents.add(new Docent("Prof. Dr. Tobias Aubele",
                    "0931/3511-8986", "none", "tobias.aubele@fhws.de",
                    "I.3.42", "SHL", "agreement", "Praktikantenbeauftragter EC"
                    , new URL("http://www.welearn.de/typo3temp/pics/a71b033fe9.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-tobias-aubele.html"));

            demoDocents.add(new Docent("Prof. Dr. Arndt Balzer", "0931/3511-8362",
                    "0931/3511-9410", "arndt.balzer@fhws.de", "I.3.26", "SHL",
                    "agreement", "Studienfachberater/in: Bachelor Informatik\n" +
                    "Verantwortlicher für das Vertiefungsmodul: Smart Systems (SmS)",
                    new URL("http://www.welearn.de/typo3temp/pics/349b32b4e6.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-arndt-balzer.html"));

            demoDocents.add(new Docent("Prof. Dr. Peter Braun", "0931/3511-8971",
                    "none", "peter.braun@fhws.de", "I.3.26", "SHL", "thursday 12-14 o'clock and agreement",
                    "Prodekan\n" + "Internationales\n" + "India-Gateway-Program (IGP)\n" +
                            "Verantwortlicher für das Vertiefungsmodul: Smart Systems (SmS)",
                    new URL("http://www.welearn.de/typo3temp/pics/8c69900f16.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-peter-braun.html"));

            demoDocents.add(new Docent("Prof. Dr. Frank Deinzer", "0931/3511-7774",
                    "none", "frank.deinzer@fhws.de", "I.2.37", "SHL", "monday 14-15 o'clock",
                    "Verantwortlicher für das Vertiefungsmodul: Medieninformatik (MI)",
                    new URL("http://www.welearn.de/typo3temp/pics/517bf0a67a.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-frank-deinzer.html"));

            demoDocents.add(new Docent("M.Sc. Vitaliy Schreibmann", "0931/3511-8303",
                    "none", "vitaliy.schreibmann@fhws.de", "I.3.26", "SHL", "agreement",
                    "",
                    new URL("http://www.welearn.de/fileadmin/bildmaterial/image/dummy_profil.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/details/person/schreibmann.html"));
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    private void addDemoLectures()
    {
        ArrayList<Docent> _docents = new ArrayList<>();
        ArrayList<ExamType> _examTypes = new ArrayList<>();
        ArrayList<LectureType> _lectureTypes = new ArrayList<>();
        ArrayList<Course> _courses = new ArrayList<>();

        _docents.add(demoDocents.get(3));
        _lectureTypes.add(LectureType.Seminar);
        _examTypes.add(ExamType.OralExam);
        _courses.add(Course.BIN);
        _courses.add(Course.BWI);

        demoLectures.add(new Lecture("Vertiefung 1: Computergrafik",demoDocents.get(3),
               _docents, Language.german,4,_lectureTypes,5,_examTypes,6,
                _courses,context.getResources().getString(R.string.CG_Description), Place.SHL));

        _courses = new ArrayList<>();
        _courses.add(Course.BIN);
        _courses.add(Course.BWI);
        _courses.add(Course.EC);

        demoLectures.add(new Lecture("Tontechnik und Audioprogrammierung",
                demoDocents.get(3), _docents,Language.german, 4, _lectureTypes, 5, _examTypes, 6,
                _courses,context.getResources().getString(R.string.TTAP_Description),Place.SHL));

        _lectureTypes = new ArrayList<>();
        _lectureTypes.add(LectureType.Seminar);
        _lectureTypes.add(LectureType.LabClass);
        _docents = new ArrayList<>();
        _docents.add(demoDocents.get(1));
        _docents.add(demoDocents.get(4));

        demoLectures.add(new Lecture("Mobile Applikations",
                demoDocents.get(1), _docents, Language.english, 4, _lectureTypes, 5, _examTypes, 6,
                _courses,context.getResources().getString(R.string.MobApp_Description),Place.SHL));
    }
    private void addDemoEvents(){
        ///Computergrafik
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015, Calendar.MAY,18,8,15,0),new GregorianCalendar(2015, Calendar.MAY,18,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,1,8,15,0), new GregorianCalendar(2015,Calendar.JUNE,1,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,8,8,15,0), new GregorianCalendar(2015,Calendar.JUNE,8,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,15,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,15,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,22,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,22,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,29,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,29,11,30,0),LectureType.Lecture,"I 2.14"));
        demoEvents.add(new Event(demoLectures.get(0),demoLectures.get(0).get_docents(),new GregorianCalendar(2015,Calendar.JULY,6,8,15,0), new GregorianCalendar(2015,Calendar.JULY,6,11,30,0),LectureType.Lecture,"I 2.14"));

        //TestEvents
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.MAY ,22,8,15,0),new GregorianCalendar(2015,Calendar.MAY ,24,11,30,0),LectureType.Lecture,"H 1.3"));



        //TTAP
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.MAY ,21,8,15,0),new GregorianCalendar(2015,Calendar.MAY ,21,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.MAY ,28,8,15,0),new GregorianCalendar(2015,Calendar.MAY ,28,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,11,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,11,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,18,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,18,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.JUNE,25,8,15,0),new GregorianCalendar(2015,Calendar.JUNE,25,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.JULY,2,8,15,0), new GregorianCalendar(2015,Calendar.JULY,2,11,30,0),LectureType.Lecture,"H 1.3"));
        demoEvents.add(new Event(demoLectures.get(1),demoLectures.get(1).get_docents(),new GregorianCalendar(2015,Calendar.JULY,9,8,15,0), new GregorianCalendar(2015,Calendar.JULY,9,11,30,0),LectureType.Lecture,"H 1.3"));

        //Mobile Applikations
        Docent braun = demoDocents.get(2);
        Docent schreibmann = demoDocents.get(4);
        ArrayList<Docent> braunList = new ArrayList<Docent>();
        ArrayList<Docent> schreibmannList = new ArrayList<Docent>();
        braunList.add(braun);
        schreibmannList.add(schreibmann);
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.MAY ,21,11,45,0),new GregorianCalendar(2015,Calendar.MAY ,21,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.MAY ,21,13,30,0),new GregorianCalendar(2015,Calendar.MAY ,21,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.MAY ,28,11,45,0),new GregorianCalendar(2015,Calendar.MAY ,28,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.MAY ,28,13,30,0),new GregorianCalendar(2015,Calendar.MAY ,28,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.JUNE,11,11,45,0),new GregorianCalendar(2015,Calendar.JUNE,11,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.JUNE,11,13,30,0),new GregorianCalendar(2015,Calendar.JUNE,11,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.JUNE,18,11,45,0),new GregorianCalendar(2015,Calendar.JUNE,18,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.JUNE,18,13,30,0),new GregorianCalendar(2015,Calendar.JUNE,18,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.JUNE,25,11,45,0),new GregorianCalendar(2015,Calendar.JUNE,25,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.JUNE,25,13,30,0),new GregorianCalendar(2015,Calendar.JUNE,25,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.JULY,2,11,45,0), new GregorianCalendar(2015,Calendar.JULY,2,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.JULY,2,13,30,0), new GregorianCalendar(2015,Calendar.JULY,2,15,0,0),LectureType.LabClass,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),braunList,         new GregorianCalendar(2015,Calendar.JULY,9,11,45,0), new GregorianCalendar(2015,Calendar.JULY,9,13,15,0),LectureType.Lecture,"I 2.15"));
        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.JULY,9,13,30,0), new GregorianCalendar(2015,Calendar.JULY,9,15,0,0),LectureType.LabClass,"I 2.15"));


        demoEvents.add(new Event(demoLectures.get(2),schreibmannList,   new GregorianCalendar(2015,Calendar.MAY ,27,13,30,0),new GregorianCalendar(2015,Calendar.MAY ,27,15,0,0),LectureType.LabClass,"I 2.15"));

    }*/


}
