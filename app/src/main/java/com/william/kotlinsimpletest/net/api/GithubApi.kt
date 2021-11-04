package com.william.kotlinsimpletest.net.api

import com.william.kotlinsimpletest.net.model.GithubUserReposVO
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * date: 2020/7/18
 * author: hwj
 * description:
 */
interface GithubApi {
    companion object {
        const val url = "https://api.github.com/"
    }

    /**
     * 只用Retrofit
     */
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<GithubUserReposVO>>


    /**
     * Kt协程 + Retrofit
     */
    @GET("users/{user}/repos")
    suspend fun listReposKt(
        @Path("user") user: String,
        @Query("uid") uid: String
    ): List<GithubUserReposVO>

    /**
     * RxJava + Retrofit
     */
    @GET("users/{user}/repos")
    fun listReposRx(@Path("user") user: String): Single<List<GithubUserReposVO>>
}