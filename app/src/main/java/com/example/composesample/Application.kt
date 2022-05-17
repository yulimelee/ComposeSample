package com.example.composesample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {
    companion object {
        private val TAG = Application::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
    }
}