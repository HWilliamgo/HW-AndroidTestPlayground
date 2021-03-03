package com.william.kotlinsimpletest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R
import com.william.kotlinsimpletest.chain.Task1
import com.william.kotlinsimpletest.chain.Task2
import com.william.kotlinsimpletest.chain.Task3

class ChainModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chain_mode)

        val task1 = Task1(false)
        val task2 = Task2(false)
        val task3 = Task3(true)
        task1.addNext(task2)
        task2.addNext(task3)

        task1.action()
    }
}