package com.example.qpid_android.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun updateUi(job: (CoroutineScope) -> Unit) =
    CoroutineScope(Dispatchers.Main).launch {
        job(this)
    }