package com.william.kotlinsimpletest.activity.htmlTextView

import android.text.Html
import com.blankj.utilcode.util.Utils
import com.william.kotlinsimpletest.R
import java.nio.charset.Charset


/**
 * date: 2019-11-18
 * @author hwj
 * description 描述一下方法的作用
 */
object HtmlTexGetter {
    fun getHtmlText(): CharSequence {
        var text = ""
        try {
            val `in` = Utils.getApp().getResources().openRawResource(R.raw.html)
            //获取文件的字节数
            val lenght = `in`.available()
            //创建byte数组
            val buffer = ByteArray(lenght)
            //将文件中的数据读到byte数组中
            `in`.read(buffer)
            text=String(buffer, Charset.forName("utf-8"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Html.fromHtml(text)
    }
}