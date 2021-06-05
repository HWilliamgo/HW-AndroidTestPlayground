package com.william.kotlinsimpletest.activity.https_sni

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSession

/**
 * date: 2021/6/4
 * author: HWilliamgo
 * description:
 */
object HttpsSniUseCase {

    /**
     * 测试非SNI方案
     * 这种情况下测试，server都是返回了302。真正的地址要从response header的的Location中取出来，做一个跳转。
     */
    suspend fun testNoneSni(path: String, ip: String) = withContext(Dispatchers.IO) {
        val newPath = replaceUrlWithIp(path, ip)
        val httpURLConnection = URL(newPath).openConnection() as HttpsURLConnection
        httpURLConnection.setRequestProperty("Host", getHost(path))
        httpURLConnection.setHostnameVerifier { hostname: String, session: SSLSession ->
            LogUtils.d("testNoneSni() called with: hostname = $hostname, session = $session")
            //默认的这个verify方法返回的是false，不知道为什么
            val verifyResult =
                HttpsURLConnection.getDefaultHostnameVerifier().verify(getHost(path), session)
            LogUtils.d("verifyResult=$verifyResult")
            true
        }
        httpURLConnection.connect()
        val ips = httpURLConnection.inputStream
        val resultSB = StringBuilder()
        ips.reader().readLines().forEach {
            resultSB.append(it)
        }
        LogUtils.d(resultSB.toString())
    }

    /**
     * 测试SNI方案
     */
    suspend fun testSni(path: String, ip: String) :String{
        return recursiveRequest(path, ip)
    }

    private suspend fun recursiveRequest(path: String, ip: String): String {
        return withContext(Dispatchers.IO) {
            val newPath = replaceUrlWithIp(path, ip)
            val conn: URLConnection
            val code: Int
            if (path.startsWith("https")) {
                conn = URL(newPath).openConnection() as HttpsURLConnection
                conn.apply {
                    setRequestProperty("Host", getHost(path))
                    connectTimeout = 30000
                    readTimeout = 30000
                    instanceFollowRedirects = false
                    sslSocketFactory = TlsSniSocketFactory(this)
                    setHostnameVerifier { hostname: String, session: SSLSession ->
                        val host = getRequestProperty("Host")
                        HttpsURLConnection.getDefaultHostnameVerifier().verify(host, session)
                    }
                }
                code = conn.responseCode
            } else {
                conn = URL(newPath).openConnection() as HttpURLConnection
                conn.apply {
                    setRequestProperty("Host", getHost(path))
                    connectTimeout = 30000
                    readTimeout = 30000
                }
                code = conn.responseCode
            }

            if (needRedirect(code)) {
                var location: String? = conn.getHeaderField("Location")
                if (location == null) {
                    location = conn.getHeaderField("location")
                }
                location!!
                if (!location.startsWith("http://") && !location.startsWith("https://")) {
                    val originalUrl = URL(path)
                    location = originalUrl.protocol + "://" + originalUrl.host + location
                }
                recursiveRequest(location, ip)
            } else {
                val ips = conn.inputStream
                val resultSB = StringBuilder()
                ips.reader().readLines().forEach {
                    resultSB.append(it)
                }
                resultSB.toString()
            }
        }
    }

    private fun replaceUrlWithIp(path: String, ip: String): String {
        val url = URL(path)
        val host = url.host
        val newPath = path.replaceFirst(host, ip)
        LogUtils.d(newPath)
        return newPath
    }

    private fun getHost(path: String): String {
        return URL(path).host
    }

    private fun needRedirect(code: Int): Boolean {
        return code >= 300 && code < 400
    }
}