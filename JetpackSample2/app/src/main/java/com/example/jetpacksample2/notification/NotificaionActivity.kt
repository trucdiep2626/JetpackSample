package com.example.jetpacksample2.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import com.example.jetpacksample2.R
import com.example.jetpacksample2.databinding.ActivityNotificaionBinding


class NotificaionActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificaionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaion)
        binding = ActivityNotificaionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSendNoti.setOnClickListener {
            sendNotification()
        }
    }

    fun sendNotification() {

        //regular activity
        // Create an Intent for the activity you want to start
        val resultIntent = Intent(this, DetailActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        //special activity
        val notifyIntent = Intent(this, DetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.kit_logo);

        var uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var mBuilder: Notification = NotificationCompat.Builder(this, MyApplication().CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_heart)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)
            .setSound(uri)
            .setStyle(
//             NotificationCompat.BigTextStyle()
//                 .bigText("Much longer text that cannot fit one line..........................................................................................")
//
                NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null)
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        var notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, mBuilder)
    }
}