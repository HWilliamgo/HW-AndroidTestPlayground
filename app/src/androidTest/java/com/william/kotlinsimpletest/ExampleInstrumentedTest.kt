package com.william.kotlinsimpletest

import androidx.test.runner.AndroidJUnit4
import com.william.kotlinsimpletest.chain.Task1
import com.william.kotlinsimpletest.chain.Task2
import com.william.kotlinsimpletest.chain.Task3
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val task1 = Task1(false)
        val task2 = Task2(false)
        val task3 = Task3(true)
        task1.addNext(task2)
        task2.addNext(task3)

        task1.action()
    }
}
