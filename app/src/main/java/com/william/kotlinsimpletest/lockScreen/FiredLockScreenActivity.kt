package com.william.kotlinsimpletest.lockScreen

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.fastpermisssion.FastPermission
import com.william.fastpermisssion.OnPermissionCallback
import com.william.kotlinsimpletest.R
import java.util.*

/**
 * date: 2019/10/31 0031
 * @author hwj
 * description 开启锁屏Activity的入口
 */
class FiredLockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fired_lock_screen)

        startService(Intent(this, LockScreenService::class.java))

        val permissionList = arrayListOf(Manifest.permission.DISABLE_KEYGUARD)
        FastPermission.getInstance().start(this, permissionList, object : OnPermissionCallback {
            override fun onAllGranted() {
                LogUtils.d("onAllGranted")
            }

            override fun onDenied(deniedPermissions: ArrayList<String>?) {
                LogUtils.d("onDenied")
            }

            override fun onDeniedForever(deniedForeverP: ArrayList<String>?) {
                LogUtils.d("onDeniedForever")
            }

            override fun onGranted(grantedPermissions: ArrayList<String>?) {
                LogUtils.d("onGranted")
            }
        })
    }
}