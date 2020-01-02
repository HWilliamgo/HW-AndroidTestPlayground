package com.william.kotlinsimpletest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.activity_third.*

const val ACTION_HAPPY = "action_happy"
const val ACTION_SAD = "action_sad"

class ThirdActivity : AppCompatActivity() {
    var receiver: MyReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val initReceiver = {
            receiver = MyReceiver()
            val intentFilter = IntentFilter()
            intentFilter.addAction(ACTION_HAPPY)
            intentFilter.addAction(ACTION_SAD)
            registerReceiver(receiver, intentFilter)
        }

        initReceiver()

        btn_third_send_broadcast.setOnClickListener {
            Intent().apply {
                action = ACTION_HAPPY
                sendBroadcast(this)
            }
        }
    }

    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            LogUtils.d(intent.toString())
        }
    }

    // <editor-fold defaultstate="collapsed" desc="显示notification">
    private fun showNotification() {
        val intent = Intent(this, ThirdActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, createNotificationChannel())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("ContentTitle")
            .setContentText("Content Text")
            .setContentIntent(pendingIntent)
//            .setStyle(
//                androidx.media.app.NotificationCompat.MediaStyle()
//                    .setShowActionsInCompactView(1)
//            )
            .build()

        val notificationId = 1

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
            notify(notificationId, notification)
        }
    }

    private fun createNotificationChannel(): String {
        val channelId = "123"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "频道名字"
            val descriptionText = "频道描述"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        return channelId
    }
    // </editor-fold>
}