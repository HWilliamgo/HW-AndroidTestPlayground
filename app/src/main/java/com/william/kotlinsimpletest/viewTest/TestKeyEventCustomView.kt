package com.william.kotlinsimpletest.viewTest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils

/**
 * date: 2019/11/2 0002
 * @author hwj
 * description 描述一下方法的作用
 */
class TestKeyEventCustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {

    private var w: Float = 0f
    private var h: Float = 0f

    private var lastFocusdView: View? = null

    init {
        initView()
    }

    private fun initView() {
        isFocusable = true
        isFocusableInTouchMode = true

        lastFocusdView = (context as Activity).currentFocus

        requestFocus()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        this.w = w.toFloat()
        this.h = h.toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, w, h, Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        })
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("onTouchEvent=${event?.action}")
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        LogUtils.d("onKeyDown, keyEvent=${event?.action}")
        return true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        LogUtils.d("onKeyUp , keyEvent=${event?.action}")
        return true
    }

}