package com.william.kotlinsimpletest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hwilliamgo.emptylibtest.EmptyVolleyWrapper
import com.william.kotlinsimpletest.R

class EmptyModuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_module)
        EmptyVolleyWrapper.request(this)
    }
}