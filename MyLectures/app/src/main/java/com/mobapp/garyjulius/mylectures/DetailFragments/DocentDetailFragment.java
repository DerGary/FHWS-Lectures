package com.mobapp.garyjulius.mylectures.DetailFragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobapp.garyjulius.mylectures.Helper.DownloadPictureTask;
import com.mobapp.garyjulius.mylectures.Model.Docent;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;

import org.w3c.dom.Text;


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
        docentPictureView.setTag(actualDocent.get_picture());
        downloadPictureTask.execute(docentPictureView);

        docentTelephoneContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + actualDocent.get_phoneNumber()));
                startActivity(intent);
            }
        });

        docentMailContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //checkTelephonyStatus(); -> later
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"" + actualDocent.get_email()});
                //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                //intent.putExtra(Intent.EXTRA_TEXT, message);
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);            }
        });

        docentWelearnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + actualDocent.get_linkWeLearn()));
                startActivity(browserIntent);
            }
        });

        return rootView;
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
