package com.example.coroutineflow

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: android.app.Application() {
    override fun onCreate() {
        super.onCreate()
    }
}