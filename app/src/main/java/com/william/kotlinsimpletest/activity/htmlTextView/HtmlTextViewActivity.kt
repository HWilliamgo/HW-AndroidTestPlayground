package com.william.kotlinsimpletest.activity.htmlTextView

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_html_text_view.*


class HtmlTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_html_text_view)

        AgentWeb.with(this)
            .setAgentWebParent(fl_html_parent, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go("file:///android_asset/web.html")
    }
}
