package com.william.kotlinsimpletest

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.blankj.utilcode.util.LogUtils

/**
 * date: 2019/10/28 0028
 * @author hwj
 * description 描述一下方法的作用
 */
class MusicService : Service() {
    companion object {
        const val CMD_START = "start"
        const val CMD_STOP = "stop"
    }

    private var mediaSessionCompat: MediaSessionCompat? = null
    private var mediaPlayer: MediaPlayer? = null


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initMediaPlayer()
        initSession()
    }

    private fun initMediaPlayer() {

    }

    private fun initSession() {
        val mediaSession = MediaSessionCompat(this, "hwj123")
        //指明支持的按键信息类型
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
        //监听的事件（播放，暂停，上一曲，下一曲）
        val stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PLAY_PAUSE
                        or PlaybackStateCompat.ACTION_SKIP_TO_NEXT or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            )
        mediaSession.setPlaybackState(stateBuilder.build())
        //设置监听回调
        mediaSession.setCallback(object : MediaSessionCompat.Callback() {
            override fun onPlay() {
                super.onPlay()
            }

            override fun onPause() {
                super.onPause()
            }
        })
        //必须设置为true，这样才能开始接收各种信息
        mediaSession.isActive = true
        mediaSessionCompat = mediaSession
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.apply {
            when (action) {
                CMD_START -> {
                    handleStart()
                }
                CMD_STOP -> {
                    handleStop()
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun handleStart() {
        mediaSessionCompat?.apply {
            val state =
                PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1f)
                    .build()
            setPlaybackState(state)
            setMetadata(MediaMetadataCompat.Builder().run {
                putString(MediaMetadataCompat.METADATA_KEY_TITLE, "无名的歌")
                putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "无名")
                putString(MediaMetadataCompat.METADATA_KEY_ALBUM, "无名的专辑")
                putLong(MediaMetadataCompat.METADATA_KEY_DURATION, 30L)
                build()
            })
        }
    }

    private fun handleStop() {
        mediaSessionCompat?.apply {
            val state =
                PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_STOPPED, 0, 1f)
                    .build()
            setPlaybackState(state)
        }
    }
}