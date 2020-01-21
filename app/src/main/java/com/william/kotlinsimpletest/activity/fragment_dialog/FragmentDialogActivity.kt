package com.william.kotlinsimpletest.activity.fragment_dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.william.kotlinsimpletest.R

class FragmentDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dialog)

        val dialog=CustomFragmentDialog()
        dialog.show(supportFragmentManager,"dialog")
        dialog.dismiss()
    }
}
