package com.william.kotlinsimpletest.activity.jniTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hwilliam.jnilearncmake.NDKTools
import com.william.kotlinsimpletest.R
import java.io.Reader

class JniTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni_test)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_jni_test -> {
                NDKTools.testHappyLib()
            }
        }
    }
}