package com.mobapp.garyjulius.mylectures.Notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mobapp.garyjulius.mylectures.MainActivity;
import com.mobapp.garyjulius.mylectures.Model.DataBaseSingleton;
import com.mobapp.garyjulius.mylectures.Model.Event;
import com.mobapp.garyjulius.mylectures.Model.Lecture;
import com.mobapp.garyjulius.mylectures.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Stefan on 02-07-15.
 */
public class NotificationBroadCastReceiver extends BroadcastReceiver {
    public final static int REQUEST_CODE = 1;
    public static boolean registered = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        DataBaseSingleton.getInstance().loadDataBase(context);

        for (Event e : DataBaseSingleton.getInstance().get_eventList()) {
            DateTime beginTime = e.getBeginDateTime();
            DateTime now = DateTime.now();

            if (beginTime.isAfter(now) && beginTime.isBefore(now.plusMinutes(16))) {
                int lecture = e.get_lecture();
                Lecture lec = DataBaseSingleton.getInstance().getLectureFromId(lecture);
                String lecname = lec != null ? lec.get_title() : "";
                DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");

                Intent startIntent = new Intent(context, MainActivity.class);
                startIntent.putExtra(context.getString(R.string.pref_eventid), e.get_id());

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, startIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                Notification.Builder mBuilder = new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_menu_recent_history)
                        .setContentTitle(lecname)
                        .setContentText(context.getString(R.string.notification_room) + e.get_room() + context.getString(R.string.notification_start) + fmt.print(e.getBeginDateTime()) + context.getString(R.string.notification_end) + fmt.print(e.getEndDateTime()))
                        .setContentIntent(pendingIntent);
                Notification n = mBuilder.build();

                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(e.get_id(), n);
            }
        }
    }

    public static void registerAlarm(Context context) {
        if (registered)
            return;

        Intent i = new Intent(context, NotificationBroadCastReceiver.class);

        PendingIntent sender = PendingIntent.getBroadcast(context, NotificationBroadCastReceiver.REQUEST_CODE, i, 0);

        long firstTime = System.currentTimeMillis();
        int minutesInMillis = 15 * 60 * 1000;
        firstTime = firstTime - (firstTime % minutesInMillis) + minutesInMillis;

        // Schedule the alarm!
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, minutesInMillis, sender);
        registered = true;
    }
}
