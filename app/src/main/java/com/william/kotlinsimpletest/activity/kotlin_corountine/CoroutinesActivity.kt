package com.william.kotlinsimpletest.activity.kotlin_corountine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.stealthcopter.networktools.Ping
import com.stealthcopter.networktools.ping.PingResult
import com.stealthcopter.networktools.ping.PingStats
import com.william.kotlinsimpletest.R
import com.william.kotlinsimpletest.net.APIManager
import kotlinx.coroutines.*
import okhttp3.CacheControl
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

class CoroutinesActivity : AppCompatActivity() {

    private val imageUrl =
        "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1572765187&di=7d88da3f7bdff5449f7425f37421c8f3&src=http://5b0988e595225.cdn.sohucs.com/images/20181227/2c9b7af729734366b49acb10db184f89.jpeg"

    private val userName = "HWilliamgo"
    private val mainScope = MainScope()
    private val ioScope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

//        mainScope.launch {
//            val cacheControl = CacheControl.Builder()
//                .maxAge(60, TimeUnit.SECONDS)
//                .build()
//            val requst = Request.Builder()
//                .url("https://juejin.im/android")
////                .cacheControl(cacheControl)
//                .get()
//                .build()
//            val response = withContext(Dispatchers.IO) {
//                APIManager.getOkHttpClient().newCall(requst).execute()
//            }
////            withContext(Dispatchers.IO) {
////                val responseString = response.body()?.string()
////                LogUtils.d(responseString)
////            }
//            val cacheResponse = response.cacheResponse
//            val networkResponse = response.networkResponse
//
//            cacheResponse?.also {
//                withContext(Dispatchers.IO) {
//                    LogUtils.d(it)
//                }
//            }
//            networkResponse?.also {
//                withContext(Dispatchers.IO) {
//                    LogUtils.d(it)
//                }
//            }
//        }
        Ping.onAddress("oss-live-2.videocc.net").setTimes(0).setDelayMillis(1000).doPing(object :Ping.PingListener{
            override fun onResult(pingResult: PingResult?) {
                LogUtils.d(pingResult.toString())
            }

            override fun onFinished(pingStats: PingStats?) {
            }

            override fun onError(e: Exception?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
