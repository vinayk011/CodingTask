package com.android.codingtask

import android.app.Application
import androidx.multidex.MultiDexApplication

class App:MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        init(this)
    }

    companion object {
        lateinit var application: Application

        fun init(app: Application) {
            application = app
        }
    }
}