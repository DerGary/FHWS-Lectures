package com.mobapp.garyjulius.mylectures.AddPage;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.Model.LectureType;
import com.mobapp.garyjulius.mylectures.R;
import com.mobapp.garyjulius.mylectures.RestAsyncTasks.PostAsyncTask;
import com.mobapp.garyjulius.mylectures.ViewPager.ViewPagerFragment;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddPageFragment extends Fragment {

    private ArrayList<String> _lectureTypes = new ArrayList<>();
    private int _checkedLectureType = -1;
    private int _checkedLectureTypeTemp = -1;

    private ArrayList<String> _docents = new ArrayList<>();
    private boolean[] _checkedDocents;
    private boolean[] _checkedDocentsTemp;

    private ArrayList<String> _lectures = new ArrayList<>();
    private int _checkedLecture = -1;
    private int _checkedLectureTemp = -1;

    private String _room;

    private int _endYearTemp, _endMonthTemp, _endDayTemp, _endYear = -1, _endMonth = -1, _endDay = -1, _endHour = -1, _endMinute = -1;
    private int _startYearTemp, _startMonthTemp, _startDayTemp, _startYear = -1, _startMonth = -1, _startDay = -1, _startHour = -1, _startMinute = -1;

    private View _rootLayout;

    private Event _ev;
    private PostAsyncTask<Event> _sendEvent;


    public AddPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _rootLayout = inflater.inflate(R.layout.fragment_add_page, container, false);


        for (LectureType t : LectureType.values()) {
            _lectureTypes.add(t.toString());
        }
        for (Docent d : DataBaseSingleton.getInstance().get_docentList()) {
            _docents.add(d.get_name());
        }
        _checkedDocentsTemp = new boolean[_docents.size()];
        _checkedDocents = new boolean[_docents.size()];

        for (Lecture l : DataBaseSingleton.getInstance().get_lectureList()) {
            _lectures.add(l.get_title());
        }

        LinearLayout layout = (LinearLayout) _rootLayout.findViewById(R.id.eventTypeLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLectureTypeClicked();
            }
        });
        layout = (LinearLayout) _rootLayout.findViewById(R.id.eventDocentLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDocentClicked();
            }
        });
        layout = (LinearLayout) _rootLayout.findViewById(R.id.eventStartLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClicked();
            }
        });
        layout = (LinearLayout) _rootLayout.findViewById(R.id.eventEndLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEndClicked();
            }
        });
        layout = (LinearLayout) _rootLayout.findViewById(R.id.eventRoomLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlaceClicked();
            }
        });
        layout = (LinearLayout) _rootLayout.findViewById(R.id.eventLectureLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLectureClicked();
            }
        });
        Button b = (Button) _rootLayout.findViewById(R.id.eventSend);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEvent();
            }
        });
        return _rootLayout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Add Event");
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(false);
    }

    private void onLectureClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(_lectures.toArray(new String[_lectures.size()]), _checkedLecture, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _checkedLectureTemp = which;
            }
        });
        builder.setTitle("Choose Lecture");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(_checkedLectureTemp >= 0) {
                    _checkedLecture = _checkedLectureTemp;
                    TextView text = (TextView) _rootLayout.findViewById(R.id.eventLectureText);
                    text.setText(_lectures.get(_checkedLecture));
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void onLectureTypeClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(_lectureTypes.toArray(new String[_lectureTypes.size()]), _checkedLectureType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _checkedLectureTypeTemp = which;
            }
        });
        builder.setTitle("Choose Lecture Type");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(_checkedLectureTypeTemp >= 0) {
                    _checkedLectureType = _checkedLectureTypeTemp;
                    TextView text = (TextView) _rootLayout.findViewById(R.id.eventTypeText);
                    text.setText(_lectureTypes.get(_checkedLectureType));
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void onDocentClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        _checkedDocentsTemp = _checkedDocents.clone();
        builder.setMultiChoiceItems(_docents.toArray(new String[_docents.size()]), _checkedDocentsTemp, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                _checkedDocentsTemp[which] = isChecked;
            }
        });
        builder.setTitle("Choose Docent");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _checkedDocents = _checkedDocentsTemp.clone();
                String s = "";
                for (int i = 0; i < _checkedDocents.length; i++) {
                    if (_checkedDocents[i]) {
                        s += _docents.get(i) + "\r\n";
                    }
                }
                if (s.length() > 3) {
                    s = s.substring(0, s.length() - 2);
                    TextView text = (TextView) _rootLayout.findViewById(R.id.eventDocentContent);
                    text.setText(s);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void onStartClicked() {
        showDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                _startYearTemp = year;
                _startMonthTemp = monthOfYear;
                _startDayTemp = dayOfMonth;
                showTimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        _startHour = hourOfDay;
                        _startMinute = minute;
                        _startYear = _startYearTemp;
                        _startMonth = _startMonthTemp;
                        _startDay = _startDayTemp;
                        DateTime endTime = new DateTime(_startYear, _startMonth + 1, _startDay, _startHour, _startMinute);
                        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm - dd.MM.yyyy");

                        TextView text = (TextView) _rootLayout.findViewById(R.id.eventStartContent);
                        text.setText(fmt.print(endTime));
                    }
                });
            }
        });
    }

    private void onEndClicked() {
        showDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                _endYearTemp = year;
                _endMonthTemp = monthOfYear;
                _endDayTemp = dayOfMonth;
                showTimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        _endHour = hourOfDay;
                        _endMinute = minute;
                        _endYear = _endYearTemp;
                        _endMonth = _endMonthTemp;
                        _endDay = _endDayTemp;
                        DateTime endTime = new DateTime(_endYear, _endMonth + 1, _endDay, _endHour, _endMinute);
                        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm - dd.MM.yyyy");

                        TextView text = (TextView) _rootLayout.findViewById(R.id.eventEndContent);
                        text.setText(fmt.print(endTime));
                    }
                });
            }
        });
    }

    private EditText _edit;

    private void onPlaceClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_edittext, null);
        _edit = (EditText) layout.findViewById(R.id.dialogEditText);
        _edit.setText(_room);
        builder.setView(layout);
        builder.setTitle("Choose Room");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _room = _edit.getText().toString();
                if(_room.length() > 0) {
                    TextView text = (TextView) _rootLayout.findViewById(R.id.eventRoomText);
                    text.setText(_room);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void showDatePicker(DatePickerDialog.OnDateSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
        dialog.show();
    }

    private void showTimePicker(TimePickerDialog.OnTimeSetListener listener) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
        dialog.show();
    }

    private void sendEvent() {
        ArrayList<Integer> docentIds = new ArrayList<>();
        ArrayList<Docent> docents = DataBaseSingleton.getInstance().get_docentList();
        for (int i = 0; i < _checkedDocents.length; i++) {
            if (_checkedDocents[i]) {
                docentIds.add(docents.get(i).get_id());
            }
        }
        if (_checkedLectureType >= 0
                && docentIds.size() > 0
                && _room.length() > 0
                && _startDay >= 0
                && _startHour >= 0
                && _startMinute >= 0
                && _startMonth >= 0
                && _startYear >= 0
                && _endDay >= 0
                && _endHour >= 0
                && _endMinute >= 0
                && _endMonth >= 0
                && _endYear >= 0) {
            if (_sendEvent == null) {
                final Fragment f = this;
                _sendEvent = new PostAsyncTask<Event>(getActivity()) {
                    @Override
                    protected void onPostExecute(Object result) {
                        super.onPostExecute(result);
                        if(result instanceof Event) {
                            DataBaseSingleton.getInstance().get_eventList().add((Event) result);
                            DataBaseSingleton.getInstance().saveDataBase(getActivity());
                            _fragment.setData(getActivity().getBaseContext(), DataBaseSingleton.getInstance().get_eventList());
                            getActivity().onBackPressed();
                        }
                    }
                };
            }
            int lectureID = DataBaseSingleton.getInstance().get_lectureList().get(_checkedLecture).get_id();
            _ev = new Event(-1, lectureID, docentIds, new DateTime(_startYear, _startMonth + 1, _startDay, _startHour, _startMinute), new DateTime(_endYear, _endMonth + 1, _endDay, _endHour, _endMinute), LectureType.valueOf(_lectureTypes.get(_checkedLectureType)), _room);
            _sendEvent.execute(_ev);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("All fields must be filled");
            builder.setTitle("Error");
            builder.setPositiveButton("OK", null);
            builder.create().show();
        }
    }

    private ViewPagerFragment _fragment;
    public void setCallBack(ViewPagerFragment viewPagerFragment) {
        _fragment = viewPagerFragment;
    }
}
