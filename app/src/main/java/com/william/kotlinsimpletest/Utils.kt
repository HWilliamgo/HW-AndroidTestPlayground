package com.william.kotlinsimpletest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private fun doIt() {
    CoroutineScope(Dispatchers.Main).launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {

        }
    }
}

suspend fun getImage(url: String) = withContext(Dispatchers.IO) {

}

suspend fun suspendingPrint() {
}


