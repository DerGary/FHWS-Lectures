package com.mobapp.garyjulius.mylectures.DemoData;

import com.mobapp.garyjulius.mylectures.Model.Course;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.ExamType;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.Model.LectureType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Julius on 11.05.2015.
 */
public class DemoData {
    ArrayList<Docent> demoDocents;
    ArrayList<Lecture> demoLectures;
    public DemoData(){
        demoDocents = new ArrayList<>();
        demoLectures = new ArrayList<>();
        addDemoDocents();
        addDemoLectures();
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

            demoDocents.add(new Docent("Prof. Dr. Frank Deinzer", "0931/3511-8303",
                    "none", "frank.deinzer@fhws.de", "I.3.26", "SHL", "monday 14-15 o'clock",
                    "Verantwortlicher für das Vertiefungsmodul: Medieninformatik (MI)",
                    new URL("http://www.welearn.de/typo3temp/pics/517bf0a67a.jpg"),
                    "http://www.welearn.de/fakultaet-iw/personen/professoren-dozenten/details/person/prof-dr-frank-deinzer.html"));
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

        _docents.add(demoDocents.get(3));
        _lectureTypes.add(LectureType.Seminar);
        _examTypes.add(ExamType.WrittenExam);
        _examTypes.add( ExamType.OralExam);

        demoLectures.add(new Lecture("Vertiefung 1: Computergrafik",demoDocents.get(3),
               _docents,"german",4,_lectureTypes,5,_examTypes,6,
                Course.BIN));

        _docents.clear();
        _lectureTypes.clear();
        _examTypes.clear();

        _docents.add(demoDocents.get(2));
        _lectureTypes.add(LectureType.Seminar);
        _examTypes.add(ExamType.WrittenExam);


        demoLectures.add(new Lecture("Mobile Applikationen",demoDocents.get(2),_docents,"english",
                4,_lectureTypes,5,_examTypes,6,Course.BIN));
    }
}
