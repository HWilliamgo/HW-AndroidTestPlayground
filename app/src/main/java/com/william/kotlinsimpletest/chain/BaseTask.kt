package com.william.kotlinsimpletest.chain

/**
 * date: 2020/9/20
 * author: HWilliamgo
 * description:
 */
abstract class BaseTask(private val isTask: Boolean) {
    private var nextTask: BaseTask? = null

    fun addNext(nextTask: BaseTask) {
        this.nextTask = nextTask;
    }

    abstract fun doAction()

    fun action() {
        if (isTask) {
            doAction()
        } else {
            nextTask?.action()
        }
    }
}