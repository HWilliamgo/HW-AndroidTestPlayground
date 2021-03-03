package com.william.kotlinsimpletest.activity.rv_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.william.kotlinsimpletest.R
import kotlinx.android.synthetic.main.activity_rv_test.*

class RvTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_test)

        val rvAdapter = RvAdapter()
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)
    }
}
