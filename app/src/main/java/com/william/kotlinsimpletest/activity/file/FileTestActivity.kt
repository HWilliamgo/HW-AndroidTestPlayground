package com.william.kotlinsimpletest.activity.file

import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R
import com.william.kotlinsimpletest.viewmodel.LogCatViewModel
import java.util.logging.Level
import java.util.logging.Logger

class FileTestActivity : AppCompatActivity() {
    private val logCatViewModel by lazy { LogCatViewModel() }

    private val tvFileTest: TextView by lazy { findViewById(R.id.tv_file_test) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_test)


        //内部存储
        val filesDir = filesDir
        val cacheDir = cacheDir
        LogUtils.d("filesDir=${filesDir.absolutePath}")
        LogUtils.d("cacheDir=${cacheDir.absolutePath}")

        //外部存储

        //外部存储 - 公开文件
        val publicExternalDir = Environment.getExternalStorageDirectory()
        LogUtils.d("publicExternalDir=${publicExternalDir.absolutePath}")

        //外部存储 - 私有文件
        val externalPictureDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        LogUtils.d("externalPictureDir=${externalPictureDir?.absolutePath}")

        val log = Logger.getLogger("HWilliamgo")
        log.level = Level.INFO
        log.info("aaa")
        log.info("bbb")
        log.info("fine")

        logCatViewModel.logCatOutput().observe(this) { logMessage ->
            tvFileTest.append(logMessage)
        }
    }

}