package com.example.app

import android.app.Application
import com.example.app.di.AppContainer
import com.example.app.di.DefaultAppContainer

class MainApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
