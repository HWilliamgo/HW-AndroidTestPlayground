package com.william.kotlinsimpletest.activity.braodcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R

class ConnectivityTestActivity : AppCompatActivity() {
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connectivity_test)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                    if (activeNetworkInfo==null){
                        LogUtils.d("断开连接")
                        return@apply
                    }
                    activeNetworkInfo.apply {
                        LogUtils.d("isConnected=$isConnected, isConnectedOrConnecting=$isConnectedOrConnecting")
                    }
                }
            }
        }
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
