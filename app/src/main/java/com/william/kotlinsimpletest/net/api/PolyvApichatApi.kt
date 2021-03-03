package com.william.kotlinsimpletest.net.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * date: 2020-03-07
 * author: hwj
 * description:
 */


interface PolyvApichatApi {

    companion object {
        const val url = "https://apichat.polyv.net/"
    }

    /**
     * 请求心跳的接口
     * @param uid
     * @return
     */
    @GET("front/heartbeat")
    fun requestHeartbeat(@Query("uid") uid: String?): Observable<ResponseBody>
}