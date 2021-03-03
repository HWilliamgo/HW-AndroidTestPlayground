package com.william.kotlinsimpletest.net

import com.blankj.utilcode.util.Utils
import com.william.kotlinsimpletest.net.api.BaiduApi
import com.william.kotlinsimpletest.net.api.GithubApi
import com.william.kotlinsimpletest.net.api.PolyvApichatApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * date: 2020-03-07
 * author: hwj
 * description:
 */
object APIManager {
    private var okHttpClient: OkHttpClient? = null


    fun getOkHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder()
                .cache(Cache(File(Utils.getApp().cacheDir, "okHttpCache"), 10 * 1024 * 1024))
                .build()
        }
        return okHttpClient!!
    }

    fun getPolyvChatApi(): PolyvApichatApi {
        return createApi(PolyvApichatApi::class.java, PolyvApichatApi.url)
    }

    fun getBaiduApi(): BaiduApi {
        return createApi(BaiduApi::class.java, BaiduApi.url)
    }

    fun getGithubApi(): GithubApi {
        return createApi(GithubApi::class.java, GithubApi.url)
    }

    private fun <T> createApi(clazz: Class<T>, baseUrl: String): T {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //配置使用Gson解析响应数据
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //配置支持RxJava
            .build()
        return retrofit.create(clazz)
    }
}