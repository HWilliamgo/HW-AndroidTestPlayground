package com.william.kotlinsimpletest.activity.viewTest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

/**
 * date: 2020/8/13
 * author: HWilliamgo
 * description:
 */
class MyFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        initView()
    }

    private fun initView() {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d(event)
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d(ev)
        return true
    }

    override fun onViewRemoved(child: View?) {
        super.onViewRemoved(child)
        Throwable().printStackTrace()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Throwable().printStackTrace()
        LogUtils.d(parent)
    }
}