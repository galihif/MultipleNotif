package com.giftech.multiplenotif

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.giftech.multiplenotif.kuis.KuisActivity
import com.giftech.multiplenotif.notification.Datasource
import com.giftech.multiplenotif.notification.HariBesar
import com.giftech.multiplenotif.notification.ReminderBroadcast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var listHariBesar: ArrayList<HariBesar>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        listHariBesar = Datasource().loadListHari()

        listHariBesar.forEach {
            val hariBesar = it
            setNotification(hariBesar, listHariBesar.indexOf(it))
        }

        btn_kuis.setOnClickListener {
            startActivity(Intent(this, KuisActivity::class.java))
        }
    }

    private fun createNotificationChannel() {
        val name:CharSequence = "Task Reminder"
        val description = "Reminder for task"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel("notifyHariBesar", name, importance)
        channel.description = description

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun setNotification(hariBesar: HariBesar, code:Int) {
        val intent = Intent(this, ReminderBroadcast::class.java)
        intent.putExtra("date", hariBesar.date.toString())
        intent.putExtra("monthName", hariBesar.monthName)
        intent.putExtra("name", hariBesar.name)
        intent.putExtra("image",hariBesar.image)
        val pendingIntent = PendingIntent.getBroadcast(this,code, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(2021,hariBesar.month-1, hariBesar.date,17,34)
        if (calendar.timeInMillis >= System.currentTimeMillis()){
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }

    }
}