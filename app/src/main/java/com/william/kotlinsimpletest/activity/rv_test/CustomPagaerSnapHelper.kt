package com.william.kotlinsimpletest.activity.rv_test

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils

/**
 * date: 2019-12-03
 * author: hwj
 * description:
 */
class CustomPagaerSnapHelper : LinearSnapHelper() {
    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        val snapView = super.findSnapView(layoutManager)
        if (snapView == null || layoutManager == null) {
            return null
        }
        val pos = layoutManager.getPosition(snapView)
        LogUtils.d(pos)
        return if (((pos - 1) % 3 == 0)) {
            snapView
        } else {
            null
        }
    }
}