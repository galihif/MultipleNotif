package com.giftech.multiplenotif;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.giftech.multiplenotif.notification.Datasource;
import com.giftech.multiplenotif.notification.HariBesar;
import com.giftech.multiplenotif.notification.ReminderBroadcast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivityJava extends AppCompatActivity {

    private ArrayList<HariBesar> listHariBesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        listHariBesar = new Datasource().loadListHari();
        createNotificationChannel();
        listHariBesar.forEach((hariBesar -> {
            setNotification(hariBesar, listHariBesar.indexOf(hariBesar));
        }));
    }

    private void createNotificationChannel(){
        String name = "Hari Besar Reminder";
        String description = "Reminder Hari Besar";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel("notifyHariBesar",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private void setNotification(HariBesar hariBesar, int code){
        Intent intent = new Intent(this, ReminderBroadcast.class);
        intent.putExtra("date", hariBesar.getDate());
        intent.putExtra("monthName",hariBesar.getMonthName());
        intent.putExtra("name", hariBesar.getName());
        intent.putExtra("image", hariBesar.getImage());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, hariBesar.getMonth()-1, hariBesar.getDate(), 17, 36);
        if (calendar.getTimeInMillis() >= System.currentTimeMillis()){
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
}