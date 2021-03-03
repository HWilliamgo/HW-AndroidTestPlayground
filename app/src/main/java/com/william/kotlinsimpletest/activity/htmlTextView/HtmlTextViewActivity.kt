package com.william.kotlinsimpletest.activity.htmlTextView

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.LogUtils
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_html_text_view.*


class HtmlTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_text_view)

        val wv=WebView(this)
        fl_html_parent.addView(wv, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        val content="<h2>\n" +
                "    <p style=\"word-break:break-all\">\"</p>\n" +
                "    <p style=\"word-break:break-all\">\n" +
                "        守望相助，共同战“疫”，小学教师资格笔试直播班现调整为免费公益直播！停工停课不停学，况且教师资格笔试考试只剩不到40天了，希望广大学员能够做到备考与防护一样，不报一丝侥幸，认真努力踏实学习，尽自己最大努力一次全过，奋斗吧！有志青年们！\n" +
                "    </p>\n" +
                "    <p style=\"word-break:break-all\">参与人群：所有教师资格考试学员</p>\n" +
                "    <p style=\"word-break:break-all\">直播回放：仅付费学员可以回看直播，所以，非付费学员一定要准时参加直播哦！</p>\n" +
                "    <p style=\"word-break:break-all\">直播安排表：</p>\n" +
                "    <p style=\"word-break:break-all\"><img style=\" width:100%;\" src alt><img style=\" width:100%;\" src alt><img\n" +
                "            style=\" width:100%;\" src alt><img style=\" width:100%;\"\n" +
                "            src=\"https://liveimages.videocc.net/uploaded/images/2020/02/fkdpjv4eeg.png\"></p>\n" +
                "    <p style=\"word-break:break-all\">讲师介绍：</p>\n" +
                "    <p style=\"word-break:break-all\"><img style=\" width:100%;\" src alt><img style=\" width:100%;\"\n" +
                "            src=\"https://liveimages.videocc.net/uploaded/images/2020/02/fkdpbseq3v.png\"></p>\n" +
                "    <p style=\"word-break:break-all\">还想享受更多品质好课，加入233考试训练营，多种套餐班级供你选择，只为助你高效通关！</p>\n" +
                "    <p style=\"word-break:break-all\"><img style=\" width:100%;\" src alt><img style=\" width:100%;\" src alt><img\n" +
                "            style=\" width:100%;\" src alt><img style=\" width:100%;\" src alt><img style=\" width:100%;\" src alt><img\n" +
                "            style=\" width:100%;\" src alt><img style=\" width:100%;\" src alt><img style=\" width:100%;\"\n" +
                "            src=\"https://liveimages.videocc.net/uploaded/images/2020/02/fkdpo9emw0.png\"></p>\n" +
                "    <p style=\"word-break:break-all\">\"</p>\n" +
                "</h2>\n" +
                "<p style=\"word-break:break-all\"><br></p>"
        val realContent="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "        <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n${content}</body>\n" +
                "</html>\n"
//        wv.loadData(content, "text/html; charset=UTF-8", null)
        Log.d("hwj",realContent)
        wv.loadData(realContent,"text/html; charset=UTF-8", null)
    }
}
