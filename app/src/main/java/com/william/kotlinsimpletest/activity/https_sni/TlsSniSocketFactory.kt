package com.william.kotlinsimpletest.activity.https_sni

import android.net.SSLCertificateSocketFactory
import android.os.Build
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import okhttp3.internal.closeQuietly
import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

/**
 * date: 2021/6/5
 * author: HWilliamgo
 * description:
 */
class TlsSniSocketFactory(private val conn: HttpsURLConnection) : SSLSocketFactory() {
    private val hostNameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()

    override fun createSocket(host: String?, port: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(
        host: String?,
        port: Int,
        localHost: InetAddress?,
        localPort: Int
    ): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(host: InetAddress?, port: Int): Socket {
        TODO("Not yet implemented")
    }

    override fun createSocket(
        address: InetAddress?,
        port: Int,
        localAddress: InetAddress?,
        localPort: Int
    ): Socket {
        TODO("Not yet implemented")
    }

    //TLS Layer

    override fun getDefaultCipherSuites(): Array<String> {
        return emptyArray()
    }

    override fun createSocket(
        plainSocet: Socket?,
        host: String?,
        port: Int,
        autoClose: Boolean
    ): Socket {
        var peerHost = conn.getRequestProperty("Host")
        if (peerHost == null) {
            peerHost = host
        }
        LogUtils.d("customized createSocket. host: $peerHost")
        val address = plainSocet?.inetAddress
        if (autoClose) {
            plainSocet?.closeQuietly()
        }
        val sslSocketFactory =
            SSLCertificateSocketFactory.getDefault()
        val ssl = sslSocketFactory.createSocket(address, port) as SSLSocket

        // enable TLSv1.1/1.2 if available
        ssl.enabledProtocols = ssl.supportedProtocols

        // set up SNI before the handshake
        try {
            val setHostnameMethod = ssl.javaClass.getMethod("setHostname", String::class.java)
            setHostnameMethod.invoke(ssl, peerHost)
        } catch (e: Exception) {
            e.printStackTrace()
            LogUtils.w("SNI not useable")
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            LogUtils.d("Setting SNI hostname")
//            sslSocketFactory.setHostname(ssl, peerHost)
//        } else {
//            LogUtils.d("No documented SNI support on Android < 4.2, trying with reflection")
//            try {
//                val setHostnameMethod = ssl.javaClass.getMethod("setHostname", String::class.java)
//                setHostnameMethod.invoke(ssl, peerHost)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                LogUtils.w("SNI not useable")
//            }
//        }

        //verify hostname and certificat
        val session = ssl.session
        if (!hostNameVerifier.verify(peerHost, session)) {
            throw SSLPeerUnverifiedException("Cannot verify hostname: $peerHost")
        }
        LogUtils.i("Established ${session.protocol} connection with ${session.peerHost} using ${session.cipherSuite}")
        return ssl
    }

    override fun getSupportedCipherSuites(): Array<String> {
        TODO("Not yet implemented")
    }
}