package com.example.jetcomposetest

import android.app.Application
import android.util.Log

class BaseApplication: Application {

    companion object{
        TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }
}