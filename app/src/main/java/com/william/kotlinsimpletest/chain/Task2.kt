package com.william.kotlinsimpletest.chain

import com.blankj.utilcode.util.LogUtils

/**
 * date: 2020/9/20
 * author: HWilliamgo
 * description:
 */
class Task2(isTask: Boolean) : BaseTask(isTask) {

    override fun doAction() {
        LogUtils.d("Task2已经被执行")
    }
}