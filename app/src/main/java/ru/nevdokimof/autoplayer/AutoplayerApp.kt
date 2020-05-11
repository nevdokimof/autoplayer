package ru.nevdokimof.autoplayer

import android.app.Application
import android.os.Build

class AutoplayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Utils.createNotificationChannels(applicationContext)
        }
    }
}