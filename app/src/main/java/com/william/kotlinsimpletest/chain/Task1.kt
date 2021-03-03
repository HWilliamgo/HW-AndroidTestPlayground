package com.william.kotlinsimpletest.chain

import com.blankj.utilcode.util.LogUtils

/**
 * date: 2020/9/20
 * author: HWilliamgo
 * description:
 */
class Task1(isTask: Boolean) : BaseTask(isTask) {
    override fun doAction() {
        LogUtils.d("Task1已经被执行")
    }
}