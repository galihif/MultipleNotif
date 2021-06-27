package com.giftech.multiplenotif.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.giftech.multiplenotif.R

class ReminderBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {


        val date = intent?.getIntExtra("date", 0)
        val monthName = intent?.getStringExtra("monthName")
        val name = intent?.getStringExtra("name")
        val image = intent?.getIntExtra("image", 0)

        var bitmapDrawable = BitmapFactory.decodeResource(context?.resources, image!!)

        val builder = NotificationCompat.Builder(context!!, "notifyHariBesar")
                .setContentTitle("${date.toString()} $monthName")
                .setContentText("Memperingati $name")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(bitmapDrawable)
                .setStyle(
                        NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmapDrawable))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(200, builder.build())
    }
}
