package com.william.kotlinsimpletest.net

import com.alibaba.sdk.android.httpdns.HttpDns
import com.blankj.utilcode.util.Utils


/**
 * @date: 2021/9/22
 * @author: HWilliamgo
 * @description:
 */
object HttpDnsManager {
    private val httpDns by lazy {
        HttpDns.getService(Utils.getApp(), "", "")
    }

    fun getIpByHostAsync(host: String): String? {
        return httpDns.getIpByHostAsync(host)
    }
}