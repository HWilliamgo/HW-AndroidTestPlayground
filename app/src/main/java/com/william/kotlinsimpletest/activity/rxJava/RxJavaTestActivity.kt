package com.william.kotlinsimpletest.activity.rxJava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * date: 2020-01-21
 * author: hwj
 * description:
 */
class RxJavaTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_test)

        Observable.timer(3000, TimeUnit.MILLISECONDS)
            .subscribe {
                LogUtils.d("3秒时间到")
            }
        val observable1 = Observable.create<Int> {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {

            }
            it.onNext(1)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
            .doOnNext {
                LogUtils.d("完成")
            }
        val observable2 = Observable.create<String> {
            Thread.sleep(3000)
            it.onNext("2")
            it.onError(Throwable("t"))
            it.onComplete()
        }.subscribeOn(Schedulers.io())
            .doOnNext {
                LogUtils.d("完成")
            }
        Observable.zip(
            observable1,
            observable2,
            BiFunction<Int, String, Any> { t1, t2 ->
                LogUtils.d("$t1 , $t2")
            })
            .subscribe({}, {
                LogUtils.d(it)
            })

        Observable.just(1)
            .subscribe({
                val a = 10;
                val b = 0;
                a / b
            }, {
                it.printStackTrace()
            })
    }
}