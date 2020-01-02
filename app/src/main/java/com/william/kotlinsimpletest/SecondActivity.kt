package com.william.kotlinsimpletest

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.LogUtils
import com.william.fastpermisssion.FastPermission
import com.william.fastpermisssion.OnPermissionCallback
import java.util.*


class SecondActivity : AppCompatActivity() {
    private var meidaPlayer: MediaPlayer? = null
    private var stateBuilder: PlaybackStateCompat.Builder? = null
    private var mediaSession: MediaSessionCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val permissionList = arrayListOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        )
        FastPermission.getInstance().start(this, permissionList, object : OnPermissionCallback {
            override fun onAllGranted() {
//                Intent(this@SecondActivity, MusicService::class.java).apply {
//                    action = MusicService.CMD_START
//                    startService(this)
//                }

                meidaPlayer = MediaPlayer.create(
                    this@SecondActivity,
                    Uri.parse("android.resource://" + packageName + "/" + R.raw.music)
                )
                meidaPlayer?.apply {
                    setOnPreparedListener {
                        LogUtils.d("onPrepared")
                    }
                    setOnErrorListener { mp, what, extra ->
                        LogUtils.d("what=$what, extra=$extra")
                        true
                    }
                    start()
//                    initSession()
//                    showNotification()
//                    Intent(this@SecondActivity, MediaPlayerNotificationManager::class.java).apply {
//                        action = MediaPlayerNotificationManager.ACTION_PLAY
//                        startService(this)
//                    }
                    MediaPlayerNotificationManager.getInstance().sendAction(
                        MediaPlayerNotificationManager.ACTION_PLAY)
                }
            }

            override fun onDenied(deniedPermissions: ArrayList<String>?) {
            }

            override fun onDeniedForever(deniedForeverP: ArrayList<String>?) {
            }

            override fun onGranted(grantedPermissions: ArrayList<String>?) {

            }
        })
    }

    private fun initSession() {
        mediaSession = MediaSessionCompat(this, "hwj123")
        //指明支持的按键信息类型
        mediaSession?.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
        //监听的事件（播放，暂停，上一曲，下一曲）
        stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PLAY_PAUSE
                        or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            )
        mediaSession?.setPlaybackState(stateBuilder?.build())
        //设置监听回调
        mediaSession?.setCallback(object : MediaSessionCompat.Callback() {
            override fun onPlay() {
                super.onPlay()
                LogUtils.d("onPlay")
            }

            override fun onPause() {
                super.onPause()
                LogUtils.d("onPause")
            }

            override fun onCommand(command: String?, extras: Bundle?, cb: ResultReceiver?) {
                super.onCommand(command, extras, cb)
                LogUtils.d("onCommand")
            }
        })
        //必须设置为true，这样才能开始接收各种信息
        mediaSession?.isActive = true

        mediaSession?.apply {
            val state =
                stateBuilder?.setState(PlaybackStateCompat.STATE_PAUSED, 50, 1f)?.build()
            setPlaybackState(state)
            setMetadata(MediaMetadataCompat.Builder().run {
                putString(MediaMetadataCompat.METADATA_KEY_TITLE, "无名的歌")
                putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "无名")
                putString(MediaMetadataCompat.METADATA_KEY_ALBUM, "无名的专辑")
                putLong(MediaMetadataCompat.METADATA_KEY_DURATION, 30000L)
                build()
            })
        }
    }

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
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(1)
                    .setMediaSession(mediaSession?.sessionToken)
            )
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
}
