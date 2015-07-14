package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Helper.DownloadPictureTask;
import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;


public class DocentDetailFragment extends Fragment {


    TextView docentNameContent;
    TextView docentTelephoneContent;
    TextView docentMailContent;
    TextView docentPlaceContent;
    TextView docentConsultaitonContent;
    TextView docentWelearnContent;
    ImageView docentPictureView;

    Docent actualDocent;

    public void LectureDetailFragment()
    {
        //Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View rootView = inflater.inflate(R.layout.fragment_docent_detail,container,false);

        docentNameContent = (TextView)rootView.findViewById(R.id.docentNameContent);
        docentTelephoneContent = (TextView)rootView.findViewById(R.id.docentTelContent);
        docentMailContent = (TextView)rootView.findViewById(R.id.docentMailContent);
        docentPlaceContent = (TextView)rootView.findViewById(R.id.docentPlaceContent);
        docentConsultaitonContent = (TextView)rootView.findViewById(R.id.docentConsultationContent);
        docentWelearnContent = (TextView)rootView.findViewById(R.id.docentWelearnContent);
        docentPictureView = (ImageView)rootView.findViewById(R.id.docentPictureView);
        Bitmap placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.profilepictureplaceholder);
        docentPictureView.setImageBitmap(placeholder);
        docentPictureView.setAdjustViewBounds(true);
        docentPictureView.setMinimumHeight(150);
        docentPictureView.setMinimumWidth(placeholder.getWidth() / 150);

        setData();

        DownloadPictureTask downloadPictureTask = new DownloadPictureTask();
        try {
            docentPictureView.setTag(new URL(actualDocent.get_picture()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        downloadPictureTask.execute(docentPictureView);

        docentTelephoneContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getResources().getString(R.string.tel) + actualDocent.get_phoneNumber()));
                startActivity(intent);
            }
        });

        docentMailContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checkTelephonyStatus(); -> later
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(getResources().getString(R.string.email_message_type));
                intent.putExtra(Intent.EXTRA_EMAIL, actualDocent.get_email().toString());
                //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                //intent.putExtra(Intent.EXTRA_TEXT, message);
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);
            }
        });

        docentWelearnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actualDocent.get_linkWeLearn().toString()));
                startActivity(browserIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.docent_list_title));
        ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_docents).setVisible(false);
        ((MainActivity) getActivity()).getMenu().findItem(R.id.action_events).setVisible(false);
    }

    /*private void checkTelephonyStatus()
    {
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        return tm != null && tm.getSimState()==TelephonyManager.SIM_STATE_READY;
    }*/


    private void setData()
    {
        docentNameContent.setText(actualDocent.get_name());
        docentTelephoneContent.setText(actualDocent.get_phoneNumber());
        docentMailContent.setText(actualDocent.get_email());
        docentPlaceContent.setText(actualDocent.get_location() + ", " + actualDocent.get_room());
        docentConsultaitonContent.setText((actualDocent.get_consultationHour()));
        //docentWelearnContent.setText(actualDocent.get_linkWeLearn());
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setActualDocent(Docent actualDocent)
    {
        this.actualDocent = actualDocent;
    }


}
