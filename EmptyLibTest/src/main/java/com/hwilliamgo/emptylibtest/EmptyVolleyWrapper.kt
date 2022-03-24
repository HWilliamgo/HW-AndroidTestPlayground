package com.hwilliamgo.emptylibtest

import android.content.Context
import com.android.volley.toolbox.Volley
import java.util.logging.Logger

/**
 * @date: 2021/11/16
 * @author: HWilliamgo
 * @description:
 */
object EmptyVolleyWrapper {
    fun request(context: Context) {
        try {
            val a:String
            val queue = Volley.newRequestQueue(context)
        } catch (e: Error) {
            e.printStackTrace()
        }
    }
}