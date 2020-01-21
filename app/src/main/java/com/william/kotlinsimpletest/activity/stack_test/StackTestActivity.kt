package com.william.kotlinsimpletest.activity.stack_test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_stack_test.*

class StackTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_test)

        btn_stack_test_go_stack_test2.setOnClickListener {
            startActivity(Intent(this,StackTest2Activity::class.java))
        }
    }
}
