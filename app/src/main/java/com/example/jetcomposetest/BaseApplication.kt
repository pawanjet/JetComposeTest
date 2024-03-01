package com.example.jetcomposetest

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BaseApplication: Application() {

    companion object{
        const val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }
}