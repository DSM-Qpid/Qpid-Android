package com.example.qpid_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QpidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}