package com.william.kotlinsimpletest.activity.viewTest

import android.graphics.Color
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_view_test.*

class ViewTestActivity : AppCompatActivity() {
    private val mFlTop by lazy { findViewById<FrameLayout>(R.id.fl_top) }
    private val mFlBottom by lazy { findViewById<FrameLayout>(R.id.fl_bottom) }
    private val mBtnChangeParent by lazy { findViewById<Button>(R.id.btn_change_parent) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test)

        val myChangeParentView = MyChangeParentView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.GREEN)
        }

        mBtnChangeParent.setOnClickListener {

            if (myChangeParentView.parent != mFlTop) {
                if (myChangeParentView.parent != null) {
                    (myChangeParentView.parent as ViewGroup).removeView(myChangeParentView)
                }
                mFlTop.addView(myChangeParentView)

            } else {
                if (myChangeParentView.parent != null) {
                    (myChangeParentView.parent as ViewGroup).removeView(myChangeParentView)
                }
                mFlBottom.addView(myChangeParentView)
            }
        }
    }
}
