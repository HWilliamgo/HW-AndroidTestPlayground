package com.william.kotlinsimpletest.activity.viewTest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.LogUtils

/**
 * date: 2021/5/17
 * author: HWilliamgo
 * description:
 */
class MyChangeParentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        LogUtils.d("width=$width, height=$height")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        LogUtils.d(
            "onLayout() called with: changed = $changed, left = $left, top = $top, right = $right, bottom = $bottom"
        )
    }
}