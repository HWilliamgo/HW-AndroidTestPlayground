package com.william.kotlinsimpletest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.william.kotlinsimpletest.activity.EmptyModuleActivity
import com.william.kotlinsimpletest.activity.ProgressBarActivity
import com.william.kotlinsimpletest.activity.braodcast.ConnectivityTestActivity
import com.william.kotlinsimpletest.activity.file.FileTestActivity
import com.william.kotlinsimpletest.activity.fragment_dialog.FragmentDialogActivity
import com.william.kotlinsimpletest.activity.glide.ImageLoadActivity
import com.william.kotlinsimpletest.activity.htmlTextView.HtmlTextViewActivity
import com.william.kotlinsimpletest.activity.https_sni.HttpsSniActivity
import com.william.kotlinsimpletest.activity.jniTest.JniTestActivity
import com.william.kotlinsimpletest.activity.kotlin_corountine.CoroutinesActivity
import com.william.kotlinsimpletest.activity.lockScreen.FiredLockScreenActivity
import com.william.kotlinsimpletest.activity.plv_test.MultiCameraTestActivity
import com.william.kotlinsimpletest.activity.rv_test.RvTestActivity
import com.william.kotlinsimpletest.activity.rxJava.RxJavaTestActivity
import com.william.kotlinsimpletest.activity.stack_test.StackTestActivity
import com.william.kotlinsimpletest.activity.videoview.VideoViewActivity
import com.william.kotlinsimpletest.activity.viewTest.ViewTestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show_toast.setOnClickListener {
            ToastUtils.showShort("你好")
        }

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
            startActivity(Intent(this, FragmentDialogActivity::class.java))
        }

        btn_jump_to_rv_test_Activity.setOnClickListener {
            startActivity(Intent(this, RvTestActivity::class.java))
        }

        btn_jump_to_rx_java_test.setOnClickListener {
            startActivity(Intent(this, RxJavaTestActivity::class.java))
        }
        btn_jump_to_video_view.setOnClickListener {
            startActivity(
                Intent(this, VideoViewActivity::class.java)
            )
        }
        btn_jump_to_image_load.setOnClickListener {
            startActivity(
                Intent(this, ImageLoadActivity::class.java)
            )
        }
        btn_jump_to_file_test.setOnClickListener {
            startActivity(
                Intent(this, FileTestActivity::class.java)
            )
        }
        btn_jump_to_progress_bar.setOnClickListener {
            startActivity(
                Intent(this, ProgressBarActivity::class.java)
            )
        }
        btn_jump_to_jniTest.setOnClickListener {
            startActivity(
                Intent(this, JniTestActivity::class.java)
            )
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_jump_to_https_sni -> {
                startActivity(Intent(this, HttpsSniActivity::class.java))
            }
            R.id.btn_jumpt_to_plv_test -> {
                startActivity(Intent(this, MultiCameraTestActivity::class.java))
            }
            R.id.btn_jump_to_empty_module -> {
                startActivity(Intent(this, EmptyModuleActivity::class.java))
            }
        }
    }

}