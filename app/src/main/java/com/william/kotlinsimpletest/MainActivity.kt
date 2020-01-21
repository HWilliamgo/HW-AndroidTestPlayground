package com.william.kotlinsimpletest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.activity.braodcast.ConnectivityTestActivity
import com.william.kotlinsimpletest.activity.htmlTextView.HtmlTextViewActivity
import com.william.kotlinsimpletest.activity.kotlin_corountine.CoroutinesActivity
import com.william.kotlinsimpletest.activity.lockScreen.FiredLockScreenActivity
import com.william.kotlinsimpletest.activity.rv_test.RvTestActivity
import com.william.kotlinsimpletest.activity.stack_test.StackTestActivity
import com.william.kotlinsimpletest.activity.viewTest.ViewTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_jump_to_second_activity.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                startActivity(this)
            }
        }
        btn_jump_to_third_activity.setOnClickListener {
            Intent(this@MainActivity, ThirdActivity::class.java).apply {
                startActivity(this)
            }
        }
        btn_jump_to_fired_lock_screen_activity.setOnClickListener {
            startActivity(Intent(this, FiredLockScreenActivity::class.java))
        }
        btn_jump_to_view_test_Activity.setOnClickListener {
            startActivity(Intent(this, ViewTestActivity::class.java))
        }
        btn_jump_to_coroutine_activity.setOnClickListener {
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }
        btn_jump_to_stack_test_activity.setOnClickListener {
            startActivity(Intent(this, StackTestActivity::class.java))
        }
        btn_jump_to_connectivity_test_activity.setOnClickListener {
            startActivity(Intent(this, ConnectivityTestActivity::class.java))
        }
        btn_jump_to_html_text_activity.setOnClickListener {
            startActivity(Intent(this, HtmlTextViewActivity::class.java))
        }
        btn_jump_to_fragment_dialog_activity.setOnClickListener {

        }

        btn_jump_to_rv_test_Activity.setOnClickListener {
            startActivity(Intent(this,RvTestActivity::class.java))
        }
    }

}