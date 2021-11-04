package com.william.kotlinsimpletest.activity.viewTest

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.william.kotlinsimpletest.R

class ViewTestActivity : AppCompatActivity() {
    private val mFlTop by lazy { findViewById<FrameLayout>(R.id.fl_top) }
    private val mFlBottom by lazy { findViewById<FrameLayout>(R.id.fl_bottom) }
    private val mBtnChangeParent by lazy { findViewById<Button>(R.id.btn_change_parent) }
    private val myFrameLayout by lazy { findViewById<FrameLayout>(R.id.my_frame_layout) }
    private val btnChangeConstraintParam by lazy { findViewById<Button>(R.id.btn_change_constraint_param) }
    private val vConstraintChild by lazy { findViewById<View>(R.id.v_constraint_child) }


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

        var tag = true
        btnChangeConstraintParam.setOnClickListener {
            val lp: ConstraintLayout.LayoutParams =
                vConstraintChild.layoutParams as ConstraintLayout.LayoutParams
            val oldOne = lp.dimensionRatio
            LogUtils.d("old constraint dimension ratio=$oldOne")
            if (tag) {
                lp.dimensionRatio = "W,16:9"
            } else {
                lp.dimensionRatio = "W,9:16"
            }
            tag = !tag
            vConstraintChild.requestLayout()
        }
        ScreenUtils.setLandscape(this);
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.d(newConfig.orientation)
    }
}
