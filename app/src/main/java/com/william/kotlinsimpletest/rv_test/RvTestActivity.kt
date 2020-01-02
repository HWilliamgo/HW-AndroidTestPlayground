package com.william.kotlinsimpletest.rv_test

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_rv_test.*

class RvTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_test)


        btn_start.setOnClickListener {
            val anim = TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
            )
            anim.duration = 5 * 1000
            btn.startAnimation(anim)
        }
    }
}
