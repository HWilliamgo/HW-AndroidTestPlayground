package com.william.kotlinsimpletest.activity.lockScreen

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R

/**
 * date: 2019/10/31 0031
 * @author hwj
 * description 描述一下方法的作用
 */
class LockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowFullScreen()
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
            addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        }

        setContentView(R.layout.activity_lock_screen)
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        setWindowFullScreen()
    }

    private fun setWindowFullScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        } else {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.statusBarColor = 0
//        }
    }
}