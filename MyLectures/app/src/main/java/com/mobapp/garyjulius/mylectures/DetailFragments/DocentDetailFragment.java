package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Helper.DownloadPictureTask;
import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.R;

import java.net.MalformedURLException;
import java.net.URL;


public class DocentDetailFragment extends Fragment {
    private TextView _docentNameContent;
    private TextView _docentTelephoneContent;
    private TextView _docentMailContent;
    private TextView _docentPlaceContent;
    private TextView _docentConsultaitonContent;
    private TextView _docentWelearnContent;
    private ImageView _docentPictureView;

    private Docent _actualDocent;

    public void LectureDetailFragment() {
        //Required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_docent_detail, container, false);

        _docentNameContent = (TextView) rootView.findViewById(R.id.docentNameContent);
        _docentTelephoneContent = (TextView) rootView.findViewById(R.id.docentTelContent);
        _docentMailContent = (TextView) rootView.findViewById(R.id.docentMailContent);
        _docentPlaceContent = (TextView) rootView.findViewById(R.id.docentPlaceContent);
        _docentConsultaitonContent = (TextView) rootView.findViewById(R.id.docentConsultationContent);
        _docentWelearnContent = (TextView) rootView.findViewById(R.id.docentWelearnContent);
        _docentPictureView = (ImageView) rootView.findViewById(R.id.docentPictureView);

        Bitmap placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.profilepictureplaceholder);
        _docentPictureView.setImageBitmap(placeholder);
        _docentPictureView.setAdjustViewBounds(true);
        _docentPictureView.setMinimumHeight(150);
        _docentPictureView.setMinimumWidth(placeholder.getWidth() / 150);

        setData();

        DownloadPictureTask downloadPictureTask = new DownloadPictureTask();
        try {
            _docentPictureView.setTag(new URL(_actualDocent.get_picture()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        downloadPictureTask.execute(_docentPictureView);

        LinearLayout telLayout = (LinearLayout)rootView.findViewById(R.id.docentTelLayout);
        telLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getResources().getString(R.string.tel) + _actualDocent.get_phoneNumber()));
                startActivity(intent);
            }
        });

        LinearLayout mailLayout = (LinearLayout)rootView.findViewById(R.id.docentMailLayout);
        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(getResources().getString(R.string.email_message_type));
                intent.putExtra(Intent.EXTRA_EMAIL, _actualDocent.get_email());
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);
            }
        });

        LinearLayout welearnLayout = (LinearLayout)rootView.findViewById(R.id.docentWelearnLayout);
        welearnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_actualDocent.get_linkWeLearn()));
                startActivity(browserIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.docent_list_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_add).setVisible(false);
    }

    private void setData() {
        _docentNameContent.setText(_actualDocent.get_name());
        _docentTelephoneContent.setText(_actualDocent.get_phoneNumber());
        _docentMailContent.setText(_actualDocent.get_email());
        _docentPlaceContent.setText(_actualDocent.get_location() + ", " + _actualDocent.get_room());
        _docentConsultaitonContent.setText((_actualDocent.get_consultationHour()));
    }

    public void set_actualDocent(Docent _actualDocent) {
        this._actualDocent = _actualDocent;
    }
}
