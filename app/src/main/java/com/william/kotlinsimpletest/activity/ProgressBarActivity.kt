package com.william.kotlinsimpletest.activity

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R

class ProgressBarActivity : AppCompatActivity() {
    companion object {
        const val COUNT = 10
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var btnStart: Button
    private val handler: Handler = Handler()
    private var curPos = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        progressBar = findViewById(R.id.pb)
        btnStart = findViewById(R.id.btn_start_progress)

        btnStart.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            curPos = 0
            startProgress()
        }
    }

    private fun startProgress() {
        if (curPos == COUNT) {
            return
        }
        curPos++
        handler.postDelayed({
            progressBar.progress = curPos * 100 / 10
            startProgress()
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}