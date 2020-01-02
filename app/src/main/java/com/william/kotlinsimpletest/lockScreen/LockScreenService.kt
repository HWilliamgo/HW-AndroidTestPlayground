package com.william.kotlinsimpletest.lockScreen

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 * date: 2019/10/31 0031
 * @author hwj
 * description 描述一下方法的作用
 */
class LockScreenService : Service() {

    private lateinit var receiver: LockScreenBroadcastRecevier

    // <editor-fold defaultstate="collapsed" desc="bind ">
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
// </editor-fold>

    override fun onCreate() {
        super.onCreate()
        receiver = LockScreenBroadcastRecevier()
        receiver.apply {
            registerReceiver(this, IntentFilter().apply {
                addAction(Intent.ACTION_SCREEN_OFF)
                addAction(Intent.ACTION_USER_PRESENT)
                addAction(Intent.ACTION_SCREEN_ON)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    inner class LockScreenBroadcastRecevier : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            LogUtils.d(intent.toString())
            val action = intent?.action
            if (action == Intent.ACTION_SCREEN_OFF) {
                Intent(Utils.getApp(), LockScreenActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                    Utils.getApp().startActivity(this)
                }
            }
        }
    }

}