package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Place;
import com.mobapp.garyjulius.mylectures.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;


public class EventDetailFragment extends Fragment implements LocationListener, OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private static final String TAG = "EventDetailFragment";
    private final LatLng FHWSSHL = new LatLng(49.777694, 9.963250);
    private final LatLng FHWSMUENZ = new LatLng(49.787590, 9.932718);


    TextView eventStartContent;
    TextView eventEndContent;
    TextView eventPlaceContent;
    TextView eventLectureContent;
    TextView eventTypeContent;
    boolean positionSet = false;

    private DataBaseSingleton dataBase;


    private Event actualEvent;
    private LocationManager locationManager;
    protected MapFragment mapFragment;
    protected GoogleMap theMap;

    public void EventDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBase = DataBaseSingleton.getInstance();
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_event_detail,container,false);
        eventStartContent = (TextView)rootView.findViewById(R.id.eventStartContent);
        eventEndContent = (TextView) rootView.findViewById(R.id.eventEndContent);
        eventPlaceContent = (TextView)rootView.findViewById(R.id.eventPlaceContent);
        eventLectureContent = (TextView)rootView.findViewById(R.id.eventLectureContent);
        eventTypeContent = (TextView)rootView.findViewById(R.id.eventTypeContent);

        setData();

        LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.EventDocentLayout);

        for(int i : actualEvent.get_docent())
        {
            TextView text = (TextView) getActivity().getLayoutInflater().inflate(R.layout.text_view, null);
            final Docent docent = DataBaseSingleton.getInstance().getDocentFromID(i);
            text.setText(docent.get_name());
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocentDetailFragment docentDetailFragment = new DocentDetailFragment();
                    docentDetailFragment.set_actualDocent(docent);
                    changeFragment(docentDetailFragment);
                }
            });

            layout.addView(text);
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.row);

        for(int i = 0;i < actualEvent.get_docent().size(); i++)
        {
            listAdapter.add(dataBase.getDocentFromID(actualEvent.get_docent().get(i)).get_name());
        }

        eventLectureContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LectureDetailFragment lectureDetailFragment = new LectureDetailFragment();
                lectureDetailFragment.setLecture(dataBase.getLectureFromId(actualEvent.get_lecture()));
                changeFragment(lectureDetailFragment);
            }
        });

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(provider, 0, 0, this);

        LocationManager service = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if ( enabled == false ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Add the buttons
            builder.setPositiveButton(getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.dialog_no), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    locationManager.removeUpdates(EventDetailFragment.this);
                }
            });
// Set other dialog properties
            builder.setMessage(getResources().getString(R.string.location_off_info))
                    .setTitle(getResources().getString(R.string.dialog_info));

// Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();

            }

        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(this);
        Log.d(TAG, getResources().getString(R.string.onDestroy_called));
    }


    @Override
    public void onStart() {
        super.onStart();

        this.mapFragment = MapFragment.newInstance();
        getFragmentManager( ).beginTransaction()
                .replace(R.id.container, this.mapFragment)
                .commit();
        mapFragment.getMapAsync(this); //get map asynchron
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.events_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(false);
    }

    private void setData()
    {
        DateTime beginTime = actualEvent.getBeginDateTime();
        DateTime endTime = actualEvent.getEndDateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm - dd.MM.yyyy");
        eventStartContent.setText(fmt.print(beginTime));
        eventEndContent.setText(fmt.print(endTime));
        eventPlaceContent.setText(actualEvent.get_room());
        eventTypeContent.setText(actualEvent.get_type().toString());
        eventLectureContent.setText(dataBase.getLectureFromId(actualEvent.get_lecture()).get_title());
    }


    public Event getActualEvent() {
        return actualEvent;
    }

    public void setActualEvent(Event actualEvent) {
        this.actualEvent = actualEvent;
    }

    public void changeFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().setCustomAnimations(
                R.animator.slide_in_from_right, R.animator.slide_out_to_left, R.animator.slide_in_from_left, R.animator.slide_out_to_right
        ).replace(R.id.main_layout, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(!positionSet) {
            final Marker mark2 = this.theMap.addMarker(new MarkerOptions() //set marker with actual position
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .title(getResources().getString(R.string.position)));
                    positionSet = true;
                    locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        theMap = googleMap;
        theMap.setOnMapLongClickListener(this);
        if(dataBase.getLectureFromId(actualEvent.get_lecture()).get_place() == Place.Muenzstrasse) //Set Markers for event place
        {
            final Marker mark1 = theMap.addMarker( new MarkerOptions( )
                    .position(FHWSMUENZ)
                    .title(getResources().getString(R.string.location_muenz)));
            CameraUpdateFactory.newLatLng(FHWSMUENZ);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(FHWSMUENZ).zoom(16).bearing(270).tilt(30).build();
            theMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else if (dataBase.getLectureFromId(actualEvent.get_lecture()).get_place() == Place.SHL)
        {
            CameraUpdateFactory.newLatLng(FHWSSHL);
            final Marker mark1 = theMap.addMarker(new MarkerOptions()
                    .position(FHWSSHL)
                    .title(getResources().getString(R.string.location_shl)));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(FHWSSHL).zoom(16).bearing(270).tilt(30).build();
            theMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        try {
            Geocoder coder = new Geocoder(getActivity());
            List<Address> a = coder.getFromLocation(latLng.latitude,latLng.longitude, 10);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("" + a.get(0).getAddressLine(0) + "\n" + a.get(0).getAddressLine(1))
                    .setTitle(getResources().getString(R.string.geocoder_title));

            AlertDialog dialog = builder.create();
            dialog.show();
            } catch (Exception e) {
            Log.e("LM", "Geocoder failed", e);
            }
    }
}
