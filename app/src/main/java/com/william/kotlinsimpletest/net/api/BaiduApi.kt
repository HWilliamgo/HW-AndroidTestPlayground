package com.william.kotlinsimpletest.net.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * date: 2020-03-07
 * author: hwj
 * description:
 */
interface BaiduApi {
    companion object {
        const val url = "https://www.baidu.com"
    }

    /**
     * RxJava + Retrofit
     */
    @GET("/")
    fun get(): Observable<ResponseBody>

    /**
     * Coroutine + Retrofit
     */
    @GET("/")
    suspend fun getKt(): ResponseBody
}