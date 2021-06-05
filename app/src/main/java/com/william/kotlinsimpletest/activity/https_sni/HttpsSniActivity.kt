package com.william.kotlinsimpletest.activity.https_sni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_https_sni.*
import kotlinx.coroutines.launch

class HttpsSniActivity : AppCompatActivity() {
    private val url = "https://m.taobao.com/?sprefer=sypc00"
    private val ip = "14.29.40.234"//这个ip地址是通过ping m.taobao.com得来的

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_https_sni)


    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_test_none_sni -> {
                lifecycleScope.launch {
                    try {
                        HttpsSniUseCase.testNoneSni(url, ip)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            R.id.btn_test_sni -> {
                lifecycleScope.launch {
                    val result: String
                    try {
                        result = HttpsSniUseCase.testSni(url, ip)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        return@launch
                    }
//                    tv_test_sni.text = result
                    wb_test_sni.loadData(result, "text/html", "UTF-8")
                    wb_test_sni.loadUrl("https://www.baidu.com")
                }
            }
        }
    }
}