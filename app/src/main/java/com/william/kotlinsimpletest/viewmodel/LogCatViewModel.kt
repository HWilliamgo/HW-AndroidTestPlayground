package com.william.kotlinsimpletest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

/**
 * @date: 2021/10/13
 * @author: HWilliamgo
 * @description:
 */
class LogCatViewModel : ViewModel() {
    fun logCatOutput() = liveData(Dispatchers.IO) {
        Runtime.getRuntime().exec("logcat -c")
        Runtime.getRuntime().exec("logcat")
            .inputStream
            .bufferedReader()
            .useLines { lines ->
                lines.forEach { line ->
                    emit(line)
                }
            }

    }
}