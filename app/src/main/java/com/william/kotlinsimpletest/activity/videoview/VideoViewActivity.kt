package com.william.kotlinsimpletest.activity.videoview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_video_view.*

class VideoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)

        LogUtils.d("onCreate")

        video_view.setVideoPath("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8")
        video_view.setOnPreparedListener {
            LogUtils.d("VideoViewActivity.onPrepared")
        }
        video_view.setOnErrorListener { mp, what, extra ->
            LogUtils.d("VideoViewActivity.onError ${what}, $extra")
            true
        }
        video_view.setOnInfoListener { mp, what, extra ->
            LogUtils.d("$what, $extra")
            true
        }
        video_view.start()
    }
}