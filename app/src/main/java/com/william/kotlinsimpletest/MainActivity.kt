package com.william.kotlinsimpletest

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.william.kotlinsimpletest.permission.OnPermissionCallback
import com.william.kotlinsimpletest.permission.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionList = arrayListOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        PermissionManager.getInstance()
            .start(this,permissionList,object :OnPermissionCallback{
                override fun onAllGranted() {
                }

                override fun onGranted(grantedPermissions: ArrayList<String>?) {
                }

                override fun onDenied(deniedPermissions: ArrayList<String>?) {
                }

                override fun onDeniedForever(deniedForeverP: ArrayList<String>?) {

                }
            })


        btn_start.setOnClickListener {
            Thread.sleep(10 * 1000)
        }

        btn_show_toast.setOnClickListener {
            Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show()
        }

        Glide.with(this)
            .load("http://n.sinaimg.cn/sinacn/w640h550/20180121/1c69-fyqtwzv3303697.jpg")
            .into(iv_test_image)
    }


}