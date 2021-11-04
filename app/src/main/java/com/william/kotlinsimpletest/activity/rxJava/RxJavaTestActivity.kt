package com.william.kotlinsimpletest.activity.rxJava

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.jakewharton.rxbinding2.view.RxView
import com.william.kotlinsimpletest.R
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * date: 2020-01-21
 * author: hwj
 * description:
 */
class RxJavaTestActivity : AppCompatActivity() {

    private var toggle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_test)

//        Observable.timer(3000, TimeUnit.MILLISECONDS)
//            .subscribe {
//                LogUtils.d("3秒时间到")
//            }
//        val observable1 = Observable.create<Int> {
//            try {
//                Thread.sleep(5000)
//            } catch (e: InterruptedException) {
//
//            }
//            it.onNext(1)
//            it.onComplete()
//        }.subscribeOn(Schedulers.io())
//            .doOnNext {
//                LogUtils.d("完成")
//            }
//        val observable2 = Observable.create<String> {
//            Thread.sleep(3000)
//            it.onNext("2")
//            it.onError(Throwable("t"))
//            it.onComplete()
//        }.subscribeOn(Schedulers.io())
//            .doOnNext {
//                LogUtils.d("完成")
//            }
//        Observable.zip(
//            observable1,
//            observable2,
//            BiFunction<Int, String, Any> { t1, t2 ->
//                LogUtils.d("$t1 , $t2")
//            })
//            .subscribe({}, {
//                LogUtils.d(it)
//            })
//
//        Observable.just(1)
//            .subscribe({
//                val a = 10;
//                val b = 0;
//                a / b
//            }, {
//                it.printStackTrace()
//            })
        val d = Observable
            .intervalRange(0, 3, 0, 1000, TimeUnit.MILLISECONDS)
            .flatMap { time ->
                return@flatMap Observable.create<Boolean> {
                    if (time == 2L && !toggle) {
                        LogUtils.d("超时")
                    } else {
                        if (toggle) {
                            it.onNext(true)
                        } else {
                            LogUtils.d("等待$time")
                        }
                    }

                }
            }.flatMap {
                LogUtils.d(it)
                Observable.create<String> {
                    it.onNext("下一步")
                }
            }
            .subscribe {
                LogUtils.d(it)
            }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn -> {
                toggle = true
                LogUtils.d("press toggle=true")
            }
        }
    }
}