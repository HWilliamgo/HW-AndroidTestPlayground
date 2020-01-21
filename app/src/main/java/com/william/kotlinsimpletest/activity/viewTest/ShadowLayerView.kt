package com.william.kotlinsimpletest.activity.viewTest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.william.kotlinsimpletest.R

/**
 * date: 2020-01-11
 * author: hwj
 * description:
 */

class ShadowLayerView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {
    private val paint = Paint()
    private var dogBmp: Bitmap? = null

    init {
        initView()
    }

    private fun initView() {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.color = Color.BLACK
        paint.textSize = 25f
        paint.setShadowLayer(1f, 10f, 10f, Color.GRAY)
        dogBmp = BitmapFactory.decodeResource(resources, R.drawable.dog)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) {
            return
        }
        canvas.drawColor(Color.WHITE)
        canvas.drawText("HWilliamGo", 100f, 100f, paint)
        canvas.drawCircle(300f, 100f, 50f, paint)
        dogBmp?.let {
            canvas.drawBitmap(
                it,
                null,
                Rect(500, 50, 500 + it.width, 50 + it.height),
                paint
            )
        }

    }
}