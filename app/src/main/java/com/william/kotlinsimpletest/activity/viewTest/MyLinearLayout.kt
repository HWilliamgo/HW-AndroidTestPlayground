package com.william.kotlinsimpletest.activity.viewTest

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

/**
 * date: 2020-01-11
 * author: hwj
 * description:
 */
class MyLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    init {
        initView()
    }

    private fun initView() {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthFromSpec = MeasureSpec.getSize(widthMeasureSpec)
        val heightFromSpec = MeasureSpec.getSize(heightMeasureSpec)

        //定义h和w用于记录自己的宽和高
        var h = 0
        var w = 0

        //让每个子View测量自己的宽高，并累加到自己身上，即累加到h和w上。
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            //调用measureChild测量子View的宽高，这样就能通过view.measuredWidth来拿到子view设置的宽和高了。
            //方便后续：1. 累加自己的宽高。2. onLayout中为每个View自己设置在父View这里允许展示的宽和高。
            measureChild(view, widthMeasureSpec, heightMeasureSpec)
            var childWidth = view.measuredWidth
            var childHeight = view.measuredHeight

            val marginLP = view.layoutParams as? MarginLayoutParams
            marginLP?.let {
                childWidth += it.leftMargin + it.rightMargin
                childHeight += it.topMargin + it.bottomMargin
            }


            h += childHeight
            w = max(childWidth, w)
        }

        //设置自己的宽高
        setMeasuredDimension(
            if (widthMode == MeasureSpec.EXACTLY) widthFromSpec else w,
            if (heightMode == MeasureSpec.EXACTLY) heightFromSpec else h
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var top = 0
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            child.layout(0, top, childWidth, top + childHeight)
            top += childHeight
        }
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT)
    }
}