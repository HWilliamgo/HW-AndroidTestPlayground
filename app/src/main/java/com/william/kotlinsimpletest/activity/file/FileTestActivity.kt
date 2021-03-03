package com.william.kotlinsimpletest.activity.file

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R

class FileTestActivity : AppCompatActivity() {
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
    }

}