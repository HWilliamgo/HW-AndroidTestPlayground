package com.william.kotlinsimpletest.activity.glide

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_image_load.*

class ImageLoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_load)

        val with = Glide.with(this)

        Glide.with(this)
            .load("")
            .into(glide_iv_test)

        val handler = Handler()

        val runnable={
            LogUtils.d("hello")
        }

        btn_start_post.setOnClickListener {
            handler.removeMessages(66)
            handler.postDelayed(runnable, 66, 5 * 1000)
        }

        btn_move_msg.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
        }


    }
}