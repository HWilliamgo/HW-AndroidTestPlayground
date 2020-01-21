package com.william.kotlinsimpletest.activity.viewTest

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_view_test.*

class ViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test)


        val llParent = ll_vertical

        val transition = LayoutTransition().apply {
//            val animatorShow = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f)
//            val animatorGone = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f)
//            setAnimator(LayoutTransition.APPEARING, animatorShow)
//            setAnimator(LayoutTransition.DISAPPEARING, animatorGone)
        }
//        llParent.layoutTransition = transition


        btn_add_view.setOnClickListener {
            llParent.addView(
                Button(this@ViewTestActivity), 0,
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }
        btn_remove_view.setOnClickListener {
            llParent.removeViewAt(0)
        }

    }
}
