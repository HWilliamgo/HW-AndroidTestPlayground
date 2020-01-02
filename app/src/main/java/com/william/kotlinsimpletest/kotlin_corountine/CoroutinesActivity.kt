package com.william.kotlinsimpletest.kotlin_corountine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.william.kotlinsimpletest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class CoroutinesActivity : AppCompatActivity() {

    private val imageUrl =
        "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1572765187&di=7d88da3f7bdff5449f7425f37421c8f3&src=http://5b0988e595225.cdn.sohucs.com/images/20181227/2c9b7af729734366b49acb10db184f89.jpeg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        CoroutineScope(Dispatchers.Main).launch(Dispatchers.Main) {
            val bitmap = withContext(Dispatchers.IO) {
                getBitmap()
            }
            val bitmap2 = clippingBitmap(bitmap)
            val bitmap3 = clippingBitmapTo9(bitmap)
            
        }
    }

    private fun getBitmap(): Bitmap {
        val inputStream = OkHttpClient()
            .newCall(Request.Builder().url(imageUrl).build())
            .execute()
            .body
            ?.byteStream()
        return BitmapFactory.decodeStream(inputStream)
    }

    private suspend fun clippingBitmap(bitmap: Bitmap): Bitmap {
        return withContext(Dispatchers.IO) {
            bitmap
        }
    }

    private suspend fun clippingBitmapTo9(bitmap: Bitmap): Bitmap {
        return withContext(Dispatchers.IO) {
            bitmap
        }
    }
}
